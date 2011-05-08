package evoting.controller.bean.stateless;

import evoting.controller.entity.*;
import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;
import evoting.controller.pojo.ControllerException;

@Remote
public interface NominatingSessionRemote {

    void nominate(final String candidateLogin, final Integer electionEventId, final String programme) throws ControllerException;

    List<ElectionEvent> getVoterElectionEvents(String login) throws ControllerException;

    void startNominating(final Integer eventId);

    void endNominating(final Integer eventId);

    Collection<Candidate> getCandidates(final Integer eventId);

    public void deleteCandidateFromEvent(evoting.controller.entity.Candidate candidate, java.lang.Integer eventId) throws evoting.controller.pojo.ControllerException;

    public Boolean isStartedNominating(java.lang.Integer eventId);

    public void supportEndNominating(java.lang.Integer eventId, java.lang.String login);


    public java.util.Collection<evoting.controller.entity.Commissioner> getComToEndNominating(java.lang.Integer eventId);

    public java.lang.Boolean isComToEndNominating(java.lang.Integer eventId, java.lang.String login);

    public evoting.controller.entity.Election getElectionFromEvent(java.lang.Integer eventId);

    public java.lang.Boolean isMajority(java.lang.Integer eventId, java.lang.String collectionName);

    public java.lang.Boolean alertCommissioner(java.lang.Integer eventId, java.lang.String login, java.lang.Integer elecId, java.lang.String collectionName);

    public void persist(java.lang.Object object);

    public void sendMail(java.lang.String recipient, java.lang.String text);

    
}
