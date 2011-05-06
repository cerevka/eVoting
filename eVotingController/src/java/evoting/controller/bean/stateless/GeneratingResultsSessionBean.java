/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.controller.bean.stateless;

import evoting.counter.bean.stateless.CounterRemote;
import evoting.validator.bean.stateless.ValidatorSessionRemote;
import DTO.ElectionEventResultDTO;
import evoting.controller.entity.Candidate;
import evoting.controller.entity.ElectionEvent;
import evoting.controller.entity.ElectionResult;
import evoting.controller.entity.Voter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import evoting.controller.pojo.ControllerException;
import evoting.counter.pojo.CounterException;
import evoting.validator.pojo.ValidatorException;

/**
 *
 * @author defiler
 */
@Stateless
public class GeneratingResultsSessionBean implements GeneratingResultsSessionRemote {

    @EJB
    private ValidatorSessionRemote validatorSessionBean;
    @PersistenceContext
    private EntityManager em;
    @EJB
    private CounterRemote counterBean;

    /**
     * Finishes the given election event.
     * @param eventId id of the given election event
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void finishElectionEvent(final Integer eventId) throws ControllerException {
        ElectionEvent electionEvent = em.find(ElectionEvent.class, eventId);
        if (electionEvent == null) {
            throw new ControllerException("Election event not found.");
        }
        try {
            validatorSessionBean.endElectionEvent(eventId);
        } catch (ValidatorException ex) {
            throw new ControllerException(ex.getMessage());
        }
        electionEvent.setFinished(Boolean.TRUE);
        em.persist(electionEvent);
    }

    /**
     * Finishes the given election.
     * Notices the counter modul about it.
     * @param electionId id of the given election
     * @todo implement election finishing
     */
    public void finishElection(final Integer electionId) {
        //counterBean.finishElection(electionId);
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Generates result for the given election event.
     * @param eventId Id of the given election event
     * @throws ControllerException if election event not found
     */
    public void generateResult(final Integer eventId) throws ControllerException {
        int numberOfSenators = 1;
        System.out.println("GENERATING RESULTS");
        ElectionEvent event = em.find(ElectionEvent.class, eventId);
        if (event == null) {
            throw new ControllerException("Election event not found.");
        }
        try {
            //  List <Candidate> n = new ArrayList<Candidate>();
            //n.addAll(event.getCandidates());
            //System.out.println("The event has "+n.get(0).getLogin()+"as a candidate");
        } catch (Exception ex) {
            //System.out.println("I made a bo bo");
        }

        //@Todo:Artificial condition because of smallness of testing data
        int minVotes = (int) (event.getVoters().size() * 0.5) + 1;
        System.out.println("The minimum votes to succeed is " + minVotes);



        ElectionEventResultDTO resultDTO = null;
        try {
            resultDTO = counterBean.getElectionEventResult(eventId);
        } catch (CounterException ex) {
            throw new ControllerException(ex.getMessage());
        }
        ElectionResult result;
        Candidate candidate;
        int size = resultDTO.getCandidates().length;
        System.out.println("ResultDTO size: " + size);
        List<ElectionResult> ERListIN = new ArrayList<ElectionResult>();
        List<ElectionResult> ERListOut = new ArrayList<ElectionResult>();
        for (int i = 0; i < size; i++) {
            result = new ElectionResult();
            result.setElectionEvent(event);
            candidate = em.find(Candidate.class, resultDTO.getCandidates()[i]);
            if (candidate == null) {
                throw new ControllerException("Candidate not found.");
            }
            result.setCandidate(candidate);
            result.setVotes(resultDTO.getVotes()[i]);
            if (result.getVotes() >= minVotes) {
                result.setElected(2);
            } else {
                result.setElected(3);
            }
            em.persist(result);
            //    event.getElectionResults().add(result);
            ERListIN.add(result);
        }
        //SORT
        if (size > 0) {
            int pos = 0;
            int max = ERListIN.get(0).getVotes();
            //   ElectionResult tmp = ERListIN.get(pos);
            while (!ERListIN.isEmpty()) {
                pos = 0;
                max = ERListIN.get(pos).getVotes();
                for (int i = 0; i < ERListIN.size(); i++) {
                    if (max < ERListIN.get(i).getVotes()) {
                        max = ERListIN.get(i).getVotes();
                        pos = i;
                    }
                    //WILL NOT WORK UNTIL VOTE DIFFERENTIATION IS IMPLEMENTED

                 /*
                   UNCOMMENT AFTER VOTER ROLE IS IMPLEMENTED
                  

                  if (max == ERListIN.get(i).getVotes()) {
                        ElectionResult attacker = ERListIN.get(i);
                        ElectionResult defender = ERListIN.get(pos);
                        int voteA = (attacker.getCandidate().getCandidateRole().equals("STUDENT")) ? attacker.getSVotes() : attacker.getTVotes();
                        int voteD = (defender.getCandidate().getCandidateRole().equals("STUDENT")) ? defender.getSVotes() : defender.getTVotes();
                        if (voteA > voteD) {
                            max = attacker.getVotes();
                            pos = i;
                        }
                        if (voteA == voteD) {
                            int rand = (int) Math.round(Math.random() * 10);
                            if (rand > 5) {
                                max = attacker.getVotes();
                                pos = i;
                            }

                        }
                    }*/
                }
                ERListOut.add(ERListIN.get(pos));
                ERListIN.remove(pos);
            }
            for (int i = 0; i < ERListOut.size(); i++) {
                if (i == numberOfSenators) {
                    break;
                }
                if (ERListOut.get(i).getElected() != 3) {
                    ERListOut.get(i).setElected(1);
                }


            }

            event.getElectionResults().addAll(ERListOut);




        } else {
            //EndOfSORT
            event.getElectionResults().addAll(ERListIN);
        }
        em.persist(event);
    }

    /**
     *
     * @param login login of the given voter
     * @return ended election events of the given voter
     * @throws if voter not found
     */
    public Collection<ElectionEvent> getEndedEvents(final String login) throws ControllerException {
        Voter voter = em.find(Voter.class, login);
        if (voter == null) {
            throw new ControllerException("Voter not found.");
        }
        Collection<ElectionEvent> eventsOut = new ArrayList<ElectionEvent>();
        for (ElectionEvent event : voter.getElectionEvents()) {
            if (event.getFinished()) {
                eventsOut.add(event);
            }
        }
        return eventsOut;
    }

    /**
     *
     * @param eventId id of the given election event
     * @return ElectionResults of the given election event
     * @throws if election event not found
     */
    public Collection<ElectionResult> getElectionEventResults(final Integer eventId) throws ControllerException {
        ElectionEvent electionEvent = em.find(ElectionEvent.class, eventId);
        if (electionEvent == null) {
            throw new ControllerException("Election event not found.");
        }
        System.out.println("Fetched Election Event: " + electionEvent.getName());
        Collection<ElectionResult> electionResults = electionEvent.getElectionResults();
        List<ElectionResult> resList = new ArrayList<ElectionResult>();
        resList.addAll(electionResults);

        electionResults.size(); // hack
        return electionResults;
    }
}
