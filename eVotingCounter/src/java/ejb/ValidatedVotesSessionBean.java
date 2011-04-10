/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import DTO.*;
import entityCounter.*;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojos.CounterException;

/**
 *
 * @author mz
 */
@Stateless
public class ValidatedVotesSessionBean implements ValidatedVotesSessionLocal
{
    @PersistenceContext
    private EntityManager em;

    private static final String KEY_PAIR_GEN_ALGORITHM = "DSA"; //also can be "RSA"

    /**
     * Saves data from DTO (posted to JMS queue from Validator(Evolby) module) into local entities (database).
     * @param votesDTO data transfer object with votes from election event
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW )
    public void saveValidatedVotes(final VotesDTO votesDTO) throws CounterException {
        CounterElectionEvent electionEvent = em.find(CounterElectionEvent.class, votesDTO.getElectionEventID());
        if(electionEvent == null) {
            throw new CounterException("Election event not found.");
        }
//        PrivateKey key = getPrivateKey(electionEvent.getId());
        VoteDTO[] votes = votesDTO.getVotes();
        for (int i = 0; i < votes.length; i++) {
            //prochazi pole votes[i] na kazdem i poscita hlasy kanditatu ()
            String[] candidates = votes[i].getVotedCandidates();
            for (int j = 0; j < candidates.length; j++) {
                //TODO rozsifrovani kandidata
                //String candidateLogin = decodeCandidate(key, candidates[j]);
                String candidateLogin = candidates[j];
                CounterCandidate candidate = null;
                for (CounterCandidate c : electionEvent.getCandidates()) {
                    if (c.getCandidateLogin().equals(candidateLogin)) {
                        candidate = c;
                        break;
                    }
                }
                if (candidate == null) {
                    throw new CounterException("Candidate not found.");
                }
                VotesCount votesCount = null;
                for(VotesCount vc : candidate.getVotesCount()) {
                    if(vc.getElectionEvent().equals(electionEvent)) {
                        votesCount = vc;
                    }
                }
                if(votesCount == null) {
                    votesCount = new VotesCount();
                    votesCount.setCount(0);
                    votesCount.setElectionEvent(electionEvent);
                    votesCount.setCandidate(candidate);
                    em.persist(votesCount);
                    electionEvent.getVotesCounts().add(votesCount);
                    em.persist(electionEvent);
                    candidate.getVotesCount().add(votesCount);
                    em.persist(candidate);
                }
                Integer count = votesCount.getCount();
                votesCount.setCount(count+1);
                em.persist(votesCount);
            }
        }
    }

    /**
     * Returns election private key for given election event.
     * @param electionEventId election event identifier
     * @return election private key
     */
/*    private PrivateKey getPrivateKey(Integer electionEventId) {
        byte[] privateKeyBytes = em.find(CounterElectionEvent.class, electionEventId).getElection().getPrivateKey();
        KeyFactory keyFactory = null;
        PrivateKey privateKey = null;
        try {
            keyFactory = KeyFactory.getInstance(KEY_PAIR_GEN_ALGORITHM);
            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            privateKey = keyFactory.generatePrivate(privateKeySpec);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CounterBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(CounterBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return privateKey;
    }
*/
    /**
     * Decodes candidates identifier using election private key.
     * @param key election private key
     * @param encodedCandidate encoded candidates identifier
     * @return decoded candidate identifier
     */
    private String decodeCandidate(PrivateKey key, String encodedCandidate) {
        String decoded = encodedCandidate;
        //TODO: --v2.0-- encode here, PREDPOKLAD ZE ZAKODOVANE CANDIDATOVO ID JE TYPU STRING
        return decoded;
    }
}
