
package ejb;

import DTO.VoteDTO;
import DTO.VotesDTO;
import DTO.VotingCardDTO;
import entityValidator.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojos.ValidatorException;

/**
 * Business logic of Validator modul (Evolby).
 * @author Stanislav Vrabec
 */
@Stateless
public class ValidatorSessionBean implements ValidatorSessionRemote {

    @Resource(name = "jms/validatedVotes")
    private Queue validatedVotes;
    @Resource(name = "jms/validatedVotesFactory")
    private ConnectionFactory validatedVotesFactory;
    @PersistenceContext
    private EntityManager em;

    /**
     * Creates the new given election event.
     * @param eventId id of the given election event
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void createNewElectionEvent(final Integer eventId) {
        ValidatorElectionEvent electionEvent = new ValidatorElectionEvent();
        electionEvent.setId(eventId);
        electionEvent.setVotingStarted(Boolean.FALSE);
        em.persist(electionEvent);
    }

    /**
     * Adds the new given candidate to the given election event.
     * @param candidateLogin login of the given candidate
     * @param eventId id of the given election event
     * @throws ValidatorException if the given election event not found
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addCandidate(final String candidateLogin, final Integer eventId) throws ValidatorException {
        ValidatorElectionEvent electionEvent = em.find(ValidatorElectionEvent.class, eventId);
        if (electionEvent == null) {
            throw new ValidatorException("Election event not found");
        }
        ValidatorCandidate candidate = em.find(ValidatorCandidate.class, candidateLogin);
        if (candidate == null) {
            candidate = new ValidatorCandidate();
            candidate.setLogin(candidateLogin);
            Collection<ValidatorElectionEvent> electionEvents = new ArrayList<ValidatorElectionEvent>();
            candidate.setVotedInEvents(electionEvents);
        }
        candidate.getVotedInEvents().add(electionEvent);
        em.persist(candidate);
        electionEvent.getCandidates().add(candidate);
        em.persist(electionEvent);
    }

    /**
     * Adds the new given voter to the given election event.
     * @param voterLogin login of the given voter
     * @param eventId id of the given election event
     * @throws ValidatorException if the given election event not found
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addVoter(final String voterLogin, final Integer eventId) throws ValidatorException {
        ValidatorElectionEvent electionEvent = em.find(ValidatorElectionEvent.class, eventId);
        if (electionEvent == null) {
            throw new ValidatorException("Election event not found");
        }
        ValidatorVoter voter = em.find(ValidatorVoter.class, voterLogin);
        if (voter == null) {
            voter = new ValidatorVoter();
            voter.setLogin(voterLogin);
            Collection<ValidatorElectionEvent> electionEvents = new ArrayList<ValidatorElectionEvent>();
            voter.setElectionEvents(electionEvents);
        }
        voter.getElectionEvents().add(electionEvent);
        em.persist(voter);
        electionEvent.getVoters().add(voter);
        em.persist(electionEvent);
    }

    /**
     * Start voting in the local given election event.
     * @param eventId id of the given election event
     * @throws ValidatorException if election event not found
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void startVoting(Integer eventId) throws ValidatorException {
        ValidatorElectionEvent electionEvent = em.find(ValidatorElectionEvent.class, eventId);
        if (electionEvent == null) {
            throw new ValidatorException("Election event not found");
        }
        electionEvent.setVotingStarted(Boolean.TRUE);
        em.persist(electionEvent);
    }

    /**
     * Creates Vote entity according to given VotingCardDTO.
     * Sets all relationships between Vote and Voter, ElectionEvent, Candidate.
     * Validates VotingCardDTO.
     * @param votingCard A special data object which holds necessery data.
     * @throws ValidatorException If election event not started, illegal votingCard or Vote does not belong to given election event.
     * @todo change signature of voting (for instance to session id)
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void sendVote(final VotingCardDTO votingCard) throws ValidatorException {
        ValidatorElectionEvent electionEvent = em.find(ValidatorElectionEvent.class, votingCard.getElectionEvent());
        if (electionEvent == null) {
            throw new ValidatorException("ElectionEvent not found.");
        }
        if (!electionEvent.getVotingStarted()) {
            throw new ValidatorException("Election event not started voting.");
        }
        ValidatorVoter voter = em.find(ValidatorVoter.class, votingCard.getToken());
        if (voter == null) {
            throw new ValidatorException("Voter not found.");
        }
        Collection voters = electionEvent.getVoters();
        if (!voters.contains(voter)) {
            throw new ValidatorException("Voter is not in election event.");
        }
        ValidatorVote vote = new ValidatorVote();
        vote.setElectionEvent(electionEvent);
        vote.setRecievedDate(new Date(System.nanoTime()));
        Collection<ValidatorCandidate> eventCandidates = electionEvent.getCandidates();
        Collection<ValidatorCandidate> candidates = new HashSet();
        ValidatorCandidate candidate;
        for (int i = 0; i < votingCard.getCandidates().length; i++) {
            candidate = em.find(ValidatorCandidate.class, votingCard.getCandidates()[i]);
            if (candidate == null) {
                throw new ValidatorException("Candidate " + votingCard.getCandidates()[i] + " not found.");
            }
            if (!eventCandidates.contains(candidate)) {
                throw new ValidatorException("Candidate " + votingCard.getCandidates()[i] + " is not in election event.");
            }
            candidates.add(candidate);
        }
        vote.setVotedCandidates(candidates);
        em.persist(vote);
        Collection<ValidatorVote> votes = voter.getVotes();
        /* deleting previous vote if exists */
        for (ValidatorVote v : votes) {
            if (v.getElectionEvent().equals(electionEvent)) {
                voter.getVotes().remove(v);
                electionEvent.getVotes().remove(v);
                em.remove(v);
                em.persist(electionEvent);
                break;
            }
        }
        voter.getVotes().add(vote);
        em.persist(voter);
        electionEvent.getVotes().add(vote);
        em.persist(electionEvent);
    }

    /**
     * Ends voting in the local given election event.
     * @param eventId id of the given election event
     * @throws ValidatorException if election event not found
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void endVoting(Integer eventId) throws ValidatorException {
        ValidatorElectionEvent electionEvent = em.find(ValidatorElectionEvent.class, eventId);
        if (electionEvent == null) {
            throw new ValidatorException("Election event not found");
        }
        electionEvent.setVotingStarted(Boolean.FALSE);
        em.persist(electionEvent);
    }

    /**
     * Notifies validator modul that the given election event has been ended.
     * Validator transforms all Votes in the given election event into VotesDTO.
     * Sends created VotesDTO into JMS queue.
     * @param electionEventId
     * @throws ValidatorException if ... //TODO description
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void endElectionEvent(final Integer electionEventId) throws ValidatorException {
        ValidatorElectionEvent electionEvent = em.find(ValidatorElectionEvent.class, electionEventId);
        VotesDTO votes = new VotesDTO();
        votes.setElectionEventID(electionEventId);
        VoteDTO[] voteDTOArray = new VoteDTO[electionEvent.getVotes().size()];
        String[] votedCandidates;
        int i = 0;
        for (ValidatorVote v : electionEvent.getVotes()) {
            votedCandidates = new String[v.getVotedCandidates().size()];
            int k = 0;
            for (ValidatorCandidate candidate : v.getVotedCandidates()) {
                votedCandidates[k] = candidate.getLogin();
                k++;
            }
            VoteDTO voteDTO = new VoteDTO();
            voteDTO.setVotedCandidates(votedCandidates);
            voteDTOArray[i] = voteDTO;
            i++;
        }
        votes.setVotes(voteDTOArray);
        try {
            sendJMSMessageToValidatedVotes(votes);
        } catch (JMSException ex) {
            throw new ValidatorException(ex.getMessage());
        }
    }

    private Message createJMSMessageForjmsValidatedVotes(Session session, Serializable messageData) throws JMSException {
        ObjectMessage om = session.createObjectMessage();
        om.setObject(messageData);
        return om;
    }

    private void sendJMSMessageToValidatedVotes(Serializable messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = validatedVotesFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(validatedVotes);
            messageProducer.send(createJMSMessageForjmsValidatedVotes(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteVoterFromEvent(String login, Integer eventId) {
        ValidatorElectionEvent event = em.find(ValidatorElectionEvent.class, eventId);
        Collection<ValidatorVoter> voters = event.getVoters();
        voters.size();
        ValidatorVoter voter = em.find(ValidatorVoter.class, login);
        voters.remove(voter);
        Collection<ValidatorElectionEvent> events = voter.getElectionEvents();
        events.size();
        events.remove(event);
        voters.remove(voter);

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteCandidateFromEvent(String login, Integer eventId) {
        ValidatorElectionEvent event = em.find(ValidatorElectionEvent.class, eventId);
        Collection<ValidatorCandidate> candidates = event.getCandidates();
        candidates.size();
        ValidatorCandidate candidate = em.find(ValidatorCandidate.class, login);
        candidates.remove(candidate);
        Collection<ValidatorElectionEvent> events = candidate.getVotedInEvents();
        events.size();

        candidates.remove(candidate);
        events.remove(event);
    }
}
