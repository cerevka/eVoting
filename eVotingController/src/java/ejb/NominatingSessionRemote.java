/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.*;
import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;
import pojos.ControllerException;

/**
 *
 * @author defiler
 */
@Remote
public interface NominatingSessionRemote {

    void nominate(final String candidateLogin, final Integer electionEventId, final String programme) throws ControllerException;

    List<ElectionEvent> getVoterElectionEvents(String login) throws ControllerException;

    void startNominating(final Integer eventId);

    void endNominating(final Integer eventId);

    Collection<Candidate> getCandidates(final Integer eventId);

    public void deleteCandidateFromEvent(entity.Candidate candidate, java.lang.Integer eventId) throws pojos.ControllerException;

    public Boolean isStartedNominating(java.lang.Integer eventId);

    public void supportEndNominating(java.lang.Integer eventId, java.lang.String login);


    public java.util.Collection<entity.Commissioner> getComToEndNominating(java.lang.Integer eventId);

    public java.lang.Boolean isComToEndNominating(java.lang.Integer eventId, java.lang.String login);

    public entity.Election getElectionFromEvent(java.lang.Integer eventId);

    public java.lang.Boolean isMajority(java.lang.Integer eventId, java.lang.String collectionName);

    public java.lang.Boolean alertCommissioner(java.lang.Integer eventId, java.lang.String login, java.lang.Integer elecId, java.lang.String collectionName);

    
}
