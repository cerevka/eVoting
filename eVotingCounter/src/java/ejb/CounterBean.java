package ejb;

import DTO.*;
import entityCounter.CounterCandidate;
import entityCounter.CounterElection;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entityCounter.CounterElectionEvent;
import entityCounter.VotesCount;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import pojos.CounterException;

/**
 * Business logika modulu Counter (Evolby).
 * @author Martin Zahrandnicky
 */
@Stateless
public class CounterBean implements CounterRemote {

    @PersistenceContext
    private EntityManager em;
    private static final String KEY_PAIR_GEN_ALGORITHM = "DSA"; //also can be "RSA"
    private static final String SECURE_RANDOM_ALGORITHM = "SHA1PRNG";
    private static final String SECURE_RANDOM_PROVIDER = "SUN";

    /**
     * Returns election results for given election event via DTO.
     * @param electionEventId election event identifier
     * @return ElectionEventResultDTO with election event results
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ElectionEventResultDTO getElectionEventResult(final Integer electionEventId) throws CounterException {
        ElectionEventResultDTO result = new ElectionEventResultDTO();
        CounterElectionEvent electionEvent = em.find(CounterElectionEvent.class, electionEventId);
        if (electionEvent == null) {
            throw new CounterException("Election event not found.");
        }
        int arraySize = electionEvent.getVotesCounts().size();
     //  int arraySize = electionEvent.getCandidates().size();

       // System.out.println(electionEvent.getCandidates().size());
        System.out.println("Counter VotesCount arraysize: " + arraySize);
        System.out.println("Counter VoterCandicats " +electionEvent.getCandidates().size());
        String[] candidates = new String[arraySize];
        int[] votes = new int[arraySize];
        int i = 0;
        for (VotesCount vc : electionEvent.getVotesCounts()) {
            candidates[i] = vc.getCandidate().getCandidateLogin();
            votes[i] = vc.getCount();
            i++;
        }
        result.setElectionEvent(electionEventId);
        result.setCandidates(candidates);
        result.setVotes(votes);
        System.out.println("result.getCandidates length "+result.getCandidates().length);
        for (int j = 0; j < result.getCandidates().length; j++) {
                System.out.println("Results names for "+result.getCandidates()[j]);
        }
        return result;
    }

    /**
     * Creates new election (provided by Controller(Evolby)) and saves it to Counter module database.
     * Saves election private key.
     * Returns encoded election public key.
     * @param electionId election identifier
     * @param electionKey election private key
     * @throws Exception
     * @return encoded election public key
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public byte[] createNewElection(final Integer electionId)
            throws CounterException {
        try {
            //KeyPair pair = generateKeyPair(electionId);
            CounterElection election = new CounterElection();
            election.setId(electionId);
            //election.setPrivateKey(pair.getPrivate().getEncoded());
            em.persist(election);
            return null; // pair.getPublic().getEncoded();
        } catch (Exception e) {
            throw new CounterException(e.getMessage());
        }
    }

    /**
     * Creates election event and saves to Counter module database.
     * @param electionId
     * @param electionEventId
     * @throws Exception
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void createNewElectionEvent(final Integer electionId, final Integer electionEventId)
            throws CounterException {
        try {
            CounterElectionEvent electionEvent = new CounterElectionEvent();
            electionEvent.setId(electionEventId);
            CounterElection election = em.find(CounterElection.class, electionId);
            election.getElectionEvents().add(electionEvent);
            electionEvent.setElection(election);
            em.persist(electionEvent);
            em.persist(election);
        } catch (Exception e) {
            throw new CounterException(e.getMessage());
        }
    }

    /**
     * Creates a local candidate to the given election event.
     * @param candidateLogin login of the new candidate
     * @param electionEventId id of the given election event
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addCandidate(final String candidateLogin, final Integer electionEventId) throws CounterException {
        CounterElectionEvent electionEvent = em.find(CounterElectionEvent.class, electionEventId);
        if (electionEvent == null) {
            throw new CounterException("Election event not found.");
        }
        CounterCandidate candidate = em.find(CounterCandidate.class, candidateLogin);
        if (candidate == null) {
            candidate = new CounterCandidate();
            candidate.setCandidateLogin(candidateLogin);
            Collection<CounterElectionEvent> electionEvents = new ArrayList<CounterElectionEvent>();
            candidate.setVotedInEvents(electionEvents);
        }
        candidate.getVotedInEvents().add(electionEvent);
        em.persist(candidate);
        electionEvent.getCandidates().add(candidate);
        em.persist(electionEvent);
    }

    /**
     * Generates key pair using electionId as a seed for random number generator.
     * @param electionId seed for random number generator
     * @return key pair object (public, private)
     */
    private KeyPair generateKeyPair(final Integer electionId) {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(KEY_PAIR_GEN_ALGORITHM);
            SecureRandom random = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM, SECURE_RANDOM_PROVIDER);
            random.setSeed(electionId); //userSeed .. should be unique!
            keyGen.initialize(1024, random);
            KeyPair pair = keyGen.generateKeyPair();
            return pair;
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(CounterBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CounterBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Finishes and cleans the given election.
     * Destroys the private key of the given election.
     * @param electionId id of the given election
     * @todo implement
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void finishElection(Integer electionId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteCandidateFromEvent(String login, Integer eventId) {
        CounterElectionEvent event = em.find(CounterElectionEvent.class, eventId);
        Collection<CounterCandidate> candidates = event.getCandidates();
        candidates.size();
        CounterCandidate candidate = em.find(CounterCandidate.class, login);
        candidates.remove(candidate);
        Collection<CounterElectionEvent> events = candidate.getVotedInEvents();
        events.size();
        events.remove(event);
        candidates.remove(candidate);

    }
}
