/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package evoting.controller.bean.stateless;

/*import DTO.CandidateDTO;*/
import DTO.CandidateDTO;
import evoting.controller.entity.*;
import java.util.List;
import javax.ejb.Remote;
import evoting.controller.pojo.ControllerException;

/**
 *
 * @author defiler
 */
@Remote
public interface VotingSessionRemote {

    CandidateDTO[] getCandidates(final Integer eventId) throws ControllerException;

 /*   byte[] getPublicKey(final Integer electionId) throws ControllerException;*/

    List<ElectionEvent> getVoterElectionEvents(final String login) throws ControllerException;

    void startVoting(final Integer eventId) throws ControllerException;

    void endVoting(final Integer eventId) throws ControllerException;

    public Boolean isStartedVoting(java.lang.Integer eventId);

    public java.util.List<evoting.controller.entity.Voter> getAllVoters();

    public void supportStartVoting(java.lang.Integer eventId, java.lang.String login);

    public void supportEndVoting(java.lang.Integer eventId, java.lang.String login);

    public java.util.Collection<evoting.controller.entity.Commissioner> getComToEndVoting(java.lang.Integer eventId);

    public java.util.Collection<evoting.controller.entity.Commissioner> getComToStartVoting(java.lang.Integer eventId);

    public java.lang.Boolean isComToEndVoting(java.lang.Integer eventId, java.lang.String login);

    public java.lang.Boolean isComToStartVoting(java.lang.Integer eventId, java.lang.String login);
}
