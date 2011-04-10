/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojos.ControllerException;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

/**
 *
 * @author defiler
 */
@Stateless
public class NominatingSessionBean implements NominatingSessionRemote {

    @EJB
    private ValidatorSessionRemote validatorBean;
    @EJB
    private CounterRemote counterBean;
    @PersistenceContext
    private EntityManager em;
    private Boolean nominatingDone = false;

    /**
     * Sets the voter with given login to candidate to given event.
     * @param candidateLogin login of the voter who wants to become a candidate
     * @param electionEventId where the voter want to become a candidate.
     * @param programme
     */
    public void nominate(final String candidateLogin, final Integer electionEventId, final String programme) throws ControllerException {
        ElectionEvent event = em.find(ElectionEvent.class, electionEventId);
        if (event == null) {
            throw new ControllerException("Election event not found");
        }
        Candidate candidate = em.find(Candidate.class, candidateLogin);
        if (candidate == null) {
            candidate = new Candidate();
            candidate.setLogin(candidateLogin);
            candidate.setVotedInEvents(new ArrayList<ElectionEvent>());
            candidate.setProgrammes(new HashMap<ElectionEvent, Programme>());
        } else if (candidate.getVotedInEvents().contains(event)) {
            throw new ControllerException("User is already nominated.");
        }
        Programme programmeEntity = new Programme();
        programmeEntity.setText(programme);
        em.persist(programmeEntity);
        candidate.getProgrammes().put(event, programmeEntity);
        candidate.getVotedInEvents().add(event);
        em.persist(candidate);
        event.getCandidates().add(candidate);
        em.persist(event);
        try {
            counterBean.addCandidate(candidateLogin, electionEventId);
            validatorBean.addCandidate(candidateLogin, electionEventId);
        } catch (Exception ex) {
            em.remove(programmeEntity);
            em.remove(candidate);
            throw new ControllerException(ex.getMessage());
        }
    }

    /**
     *
     * @param login of the voter
     * @return returns list of election events which are in the nominating state
     */
    public List<ElectionEvent> getVoterElectionEvents(String login) throws ControllerException {
        Voter voter = em.find(Voter.class, login);
        if (voter == null) {
            throw new ControllerException("Voter not found");
        }
        Collection<ElectionEvent> events = voter.getElectionEvents();
        List<ElectionEvent> eventsOut = new ArrayList();
        for (ElectionEvent event : events) {
            if (event.getNominatingStarted()) {
                eventsOut.add(event);
            }
        }
        return eventsOut;
    }

    /**
     * Sets the given election event to nominating state.
     * @param eventId
     */
    public void startNominating(final Integer eventId) {
        ElectionEvent event = em.find(ElectionEvent.class, eventId);
        event.setNominatingStarted(true);
        em.persist(event);

    }

    /**
     * Ends the nominating state in the givent election event.
     * @param eventId
     */
    public void endNominating(final Integer eventId) {
        ElectionEvent event = em.find(ElectionEvent.class, eventId);
        event.setNominatingStarted(false);
        em.persist(event);
        this.nominatingDone = true;
    }

    /**
     *
     * @param eventId
     * @return candidates who are in the given election event.
     */
    public Collection<Candidate> getCandidates(final Integer eventId) {
        ElectionEvent event = em.find(ElectionEvent.class, eventId);
        Collection<Candidate> candidates = event.getCandidates();
        candidates.size();
        return candidates;
    }

    /**
     *
     * @param candidate candidate that you want to delete
     * @param eventId if voting event
     * @throws ControllerException if voter not found.
     */
    public void deleteCandidateFromEvent(Candidate candidate, Integer eventId) throws ControllerException {
        ElectionEvent event = em.find(ElectionEvent.class, eventId);
        Collection<Candidate> candidates = event.getCandidates();
        candidates.size();
        Candidate can = em.find(Candidate.class, candidate.getLogin());
        Collection<ElectionEvent> events = can.getVotedInEvents();
        events.size();
        events.remove(event);
        candidates.remove(can);

        try {
            validatorBean.deleteVoterFromEvent(candidate.getLogin(), eventId);
            counterBean.deleteCandidateFromEvent(candidate.getLogin(), eventId);
        } catch (Exception ex) {
            //TODO db synchronizovano?
            throw new ControllerException(ex.getMessage());
        }
    }

    /**
     *
     * @param eventId id of voting event
     * @return true if nominating already started
     */
    public Boolean isStartedNominating(Integer eventId) {
        ElectionEvent ee = em.find(ElectionEvent.class, eventId);
        if (ee.getNominatingStarted()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * adds voter agreement with end of nominating
     * @param eventId id of event
     * @param login login of commissioner
     */
    public void supportEndNominating(Integer eventId, String login) {
        ElectionEvent ee = em.find(ElectionEvent.class, eventId);
        Commissioner com = em.find(Commissioner.class, login);

        ee.getComAgreeEndNominating().add(com);

        //if called by the first commissioner
        if (ee.getComAgreeEndNominating().size() == 1) {
            Election e = getElectionFromEvent(eventId);
            //get collection of other commissioners
            Collection<Commissioner> colCom = e.getCommissioners();
            colCom.remove(com);
            Iterator it = colCom.iterator();
            Commissioner next = null;
            //send mail to everyone
            while (it.hasNext()) {
                next = (Commissioner) it.next();
                String recipient = next.getLogin();
                String name = next.getFirstName() + " " + next.getLastName();
                String text = "Hello commissioner " + name + ",\n"
                        + "your privileged action is required to end the nominating for election event " + eventId + ".\n\n\n"
                        + "-----------------------" + "\n"
                        + "This message was sent by the E-volby system," + "\n"
                        + "please contact the system administrator if you think this should not have happend.";
                sendMail(recipient, text);
            }
        }

        com.getEventsToEndNominating().add(ee);
        em.persist(ee);
        em.persist(com);
    }
    @Resource(name = "mail/evolbyMailSession")
    private Session mailSession;

    /**
     * Sends an email message to inform commissioner about a privileged action
     * @param com The recipient of this message.
     * @param eventId Id of an event associated with this message.
     */
    public void sendMail(String recipient, String text) {

        recipient += "@fel.cvut.cz";
        System.out.println("Sending mail to:" + recipient);
        Message message = new MimeMessage(mailSession);
        String subject = "Evolby - Your privileged action is required";
        Date timeStamp = new Date();
        try {
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient, false));
            message.setSubject(subject);
            message.setText(text);
            message.setSentDate(timeStamp);
            //uncomment to actually send the mail
            //Transport.send(message);
        } catch (javax.mail.MessagingException me) {
        }
    }

    /**
     * @param eventId id of event
     * @return Collection of commissioners that agreed with end of
     * nomination in ElectionEvent with given ID
     */
    public Collection<Commissioner> getComToEndNominating(Integer eventId) {
        ElectionEvent ee = em.find(ElectionEvent.class, eventId);
        Collection<Commissioner> ret = ee.getComAgreeEndNominating();
        ret.size();
        return ret;
    }

    /**
     *
     * @param eventId id of event
     * @param login login of commissioner
     * @return true if commmissioner agreed with end of nominating
     */
    public Boolean isComToEndNominating(Integer eventId, String login) {
        Collection<Commissioner> comCol = getComToEndNominating(eventId);
        Commissioner com = em.find(Commissioner.class, login);
        if (comCol.contains(com)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     *
     * @param eventId id of event
     * @param login login of commissioner
     * @param elecId id of Election
     * @return true, if there is some important action for commissioner
     */
    public Boolean alertCommissioner(Integer eventId, String login, Integer elecId, String collectionName) {
        ElectionEvent ee = em.find(ElectionEvent.class, eventId);
        Commissioner com = em.find(Commissioner.class, login);
        Election e = em.find(Election.class, elecId);
        Collection coll;
        if (collectionName.equals("END_NOMINATING")) {
            coll = ee.getComAgreeEndNominating();
        } else if (collectionName.equals("START_VOTING")) {
            coll = ee.getComAgreeStartVoting();
        } else if (collectionName.equals("END_VOTING")) {
            coll = ee.getComAgreeEndVoting();
        } else {
            return Boolean.FALSE;
        }

        if (e.getCommissioners().contains(com) && !coll.contains(com) && !coll.isEmpty()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     *
     * @param eventId ID of ElectionEvent
     * @param collectionName name of collection in which I am looking for majority
     * values: END_NOMINATIG, START_VOTING, END_VOTING
     * @return true if there is majority count of commissioners in some issue
     */
    public Boolean isMajority(Integer eventId, String collectionName) {
        Integer collectionSize;
        ElectionEvent ee = em.find(ElectionEvent.class, eventId);
        Election e = getElectionFromEvent(eventId);

        if (collectionName.equals("END_NOMINATING")) {
            collectionSize = ee.getComAgreeEndNominating().size();
        } else if (collectionName.equals("START_VOTING")) {
            collectionSize = ee.getComAgreeStartVoting().size();
        } else if (collectionName.equals("END_VOTING")) {
            collectionSize = ee.getComAgreeEndVoting().size();
        } else {
            return Boolean.FALSE;
        }

        System.out.println("ahoj " + e.getCommissioners().size());
        System.out.println("ahoj " + collectionSize);
        if (((e.getCommissioners().size()) / 2) < collectionSize) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;

        }
    }

    /**
     *
     * @param eventId ID of ElectionEvent
     * @return determines election from given electionEventId
     */
    public Election getElectionFromEvent(Integer eventId) {
        ElectionEvent ee = em.find(ElectionEvent.class, eventId);
        Collection<Election> elCol = em.createNamedQuery("Election.findAll").getResultList();
        if (elCol.size() > 0) {
            Iterator it = elCol.iterator();
            Election ret = null;
            while (it.hasNext()) {
                ret = (Election) it.next();
                if (ret.getElectionEvents().contains(ee)) {
                    return ret;
                }
            }
        }

        return null;
    }
}
