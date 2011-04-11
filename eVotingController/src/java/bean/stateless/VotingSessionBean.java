/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.stateless;

import DTO.CandidateDTO;
import entity.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojo.ControllerException;
import pojo.ValidatorException;

import java.util.Iterator;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import javax.mail.Message;
import javax.mail.Session;

/**
 *
 * @author defiler
 */
@Stateless
public class VotingSessionBean implements VotingSessionRemote {

    @EJB
    private ValidatorSessionRemote validatorSessionBean;
    @EJB
    private GeneratingResultsSessionRemote generatingResultsSessionBean;
    @PersistenceContext
    private EntityManager em;
    

    /**
     * Method for the voteApplet
     * @param eventId Id of the given election event
     * @return filled field of CandidateDTO of the given election event for voteApplet
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public CandidateDTO[] getCandidates(final Integer eventId) throws ControllerException {
        ElectionEvent electionEvent = em.find(ElectionEvent.class, eventId);
        if (electionEvent == null) {
            throw new ControllerException("Election event not found.");
        }
        Collection<Candidate> candidates = electionEvent.getCandidates();
        CandidateDTO candidatesDTO[] = new CandidateDTO[candidates.size()];
        CandidateDTO c;
        int i = 0;
        for (Candidate candidate : candidates) {
            c = new CandidateDTO();
            c.setLogin(candidate.getLogin());
            //c.setProgramme(candidate.getProgrammes().get(electionEvent).getText());
            Person person = getPerson(candidate.getLogin());
            c.setFirstName(person.getFirstname());
            c.setLastName(person.getLastname());
            candidatesDTO[i] = c;
            i++;
        }
        return candidatesDTO;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private Person getPerson(String login) throws ControllerException {
        Person person = em.find(Person.class, login);
        if (person == null) {
            throw new ControllerException("Person not found.");
        }
        return person;
    }

    /**
     *
     * @param electionId Id of the given election
     * @return public key of the given election
     */
    /*   public byte[] getPublicKey(final Integer electionId) throws ControllerException {
    Election election = em.find(Election.class, electionId);
    if(election == null) {
    throw new ControllerException("Election not found.");
    }
    return election.getPublicKey();
    }

    /**
     *
     * @param login of the voter
     * @return list of election events which are in the voting state
     */
    public List<ElectionEvent> getVoterElectionEvents(final String login) throws ControllerException {
        Voter voter = em.find(Voter.class, login);
        if (voter == null) {
            throw new ControllerException("Voter not found.");
        }
        Collection<ElectionEvent> events = voter.getElectionEvents();
        List<ElectionEvent> eventsOut = new ArrayList<ElectionEvent>();
        for (ElectionEvent event : events) {
            if (event.getVotingStarted()) {
                eventsOut.add(event);
            }
        }
        return eventsOut;
    }

    /**
     * Sets the given election event to voting state.
     * @param eventId
     */
    public void startVoting(final Integer eventId) throws ControllerException {
        ElectionEvent event = em.find(ElectionEvent.class, eventId);
        if (event == null) {
            throw new ControllerException("Election event not found.");
        }
        event.setVotingStarted(true);
        em.persist(event);
        try {
            validatorSessionBean.startVoting(eventId);
        } catch (ValidatorException ex) {
            throw new ControllerException(ex.getMessage());
        }
    }

    /**
     * Ends the voting state in the givent election event.
     * Starts GeneratingResults process.
     * Notifies about it the validator modul.
     * @param eventId
     */
    public void endVoting(final Integer eventId) throws ControllerException {
        ElectionEvent event = em.find(ElectionEvent.class, eventId);
        if (event == null) {
            throw new ControllerException("Election event not found.");
        }
        event.setVotingStarted(false);
        em.persist(event);
        try {
            validatorSessionBean.endVoting(eventId);
        } catch (ValidatorException ex) {
            throw new ControllerException(ex.getMessage());
        }
        generatingResultsSessionBean.finishElectionEvent(eventId);
    }

    /**
     *
     *
     * @return all voters
     */
    public List<Voter> getAllVoters() {
        return (List<Voter>) em.createNamedQuery("Voter.findAll").getResultList();
    }

    /**
     *
     * @param eventId - id of voting event
     * @return true if voting already started
     */
    public Boolean isStartedVoting(Integer eventId) {
        ElectionEvent ee = em.find(ElectionEvent.class, eventId);
        if (ee.getVotingStarted()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public void supportStartVoting(Integer eventId, String login) {
        Commissioner com = em.find(Commissioner.class, login);
        ElectionEvent ee = em.find(ElectionEvent.class, eventId);

        com.getEventsToStartVoting().add(ee);
        ee.getComAgreeStartVoting().add(com);

        prepareMail(ee, com, "start");

        em.persist(com);
        em.persist(ee);
    }

    public void supportEndVoting(Integer eventId, String login) {
        Commissioner com = em.find(Commissioner.class, login);
        ElectionEvent ee = em.find(ElectionEvent.class, eventId);

        com.getEventsToEndVoting().add(ee);
        ee.getComAgreeEndVoting().add(com);
        prepareMail(ee, com, "end");
        em.persist(ee);
        em.persist(com);
    }

    public void prepareMail(ElectionEvent ee, Commissioner com, String action) {
        Integer eventId = ee.getId();
        int numCom = 0;
        if (action.equals("start")) {
            numCom = ee.getComAgreeStartVoting().size();
        } else {
            numCom = ee.getComAgreeEndVoting().size();
        }
        //if called by the first commissioner
        if (numCom == 1) {
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
                        + "your privileged action is required to " + action
                        + " voting for election event " + eventId + ".\n\n\n"
                        + "-----------------------" + "\n"
                        + "This message was sent by the E-volby system,\n"
                        + "please contact the system administrator if you think this should not have happend.";
                sendMail(recipient, text);
            }
        }
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
            //error
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

    public Collection<Commissioner> getComToStartVoting(Integer eventId) {
        ElectionEvent ee = em.find(ElectionEvent.class, eventId);
        Collection<Commissioner> ret = ee.getComAgreeStartVoting();
        ret.size();
        return ret;
    }

    public Collection<Commissioner> getComToEndVoting(Integer eventId) {
        ElectionEvent ee = em.find(ElectionEvent.class, eventId);
        Collection<Commissioner> ret = ee.getComAgreeEndVoting();
        ret.size();
        return ret;
    }

    public Boolean isComToStartVoting(Integer eventId, String login) {
        Collection<Commissioner> comCol = getComToStartVoting(eventId);
        Commissioner com = em.find(Commissioner.class, login);
        if (comCol.contains(com)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public Boolean isComToEndVoting(Integer eventId, String login) {
        Collection<Commissioner> comCol = getComToEndVoting(eventId);
        Commissioner com = em.find(Commissioner.class, login);
        if (comCol.contains(com)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
