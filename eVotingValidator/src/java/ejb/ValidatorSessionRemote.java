/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import DTO.VotingCardDTO;
import javax.ejb.Remote;
import pojos.ValidatorException;

/**
 *
 * @author defiler
 */
@Remote
public interface ValidatorSessionRemote {

    void createNewElectionEvent(final Integer eventId);

    void addCandidate(final String candidateLogin, final Integer electionEventId) throws ValidatorException;

    void addVoter(final String voterLogin, final Integer eventId) throws ValidatorException;
    
    void startVoting(final Integer eventId) throws ValidatorException;

    void sendVote(final VotingCardDTO votingCard) throws ValidatorException;

    void endVoting(final Integer eventId) throws ValidatorException;

    void endElectionEvent(final Integer eventId) throws ValidatorException;

    @javax.ejb.TransactionAttribute(value = javax.ejb.TransactionAttributeType.REQUIRES_NEW)
    public void deleteCandidateFromEvent(java.lang.String login, java.lang.Integer eventId);

    @javax.ejb.TransactionAttribute(value = javax.ejb.TransactionAttributeType.REQUIRES_NEW)
    public void deleteVoterFromEvent(java.lang.String login, java.lang.Integer eventId);
}
