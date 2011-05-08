package evoting.controller.bean.stateless;

import evoting.controller.entity.Commissioner;
import evoting.controller.entity.Election;
import evoting.controller.entity.ElectionEvent;
import evoting.controller.entity.Person;
import evoting.controller.entity.Voter;
import java.util.Collection;
import javax.ejb.Remote;
import evoting.controller.pojo.ControllerException;

@Remote
public interface ElectionSessionRemote {

    void createElection(final String electionName, final String electionType) throws ControllerException;

    Collection<Person> getAllPerson();

    Collection<Election> getCommissionerElection(final String commissionerLogin) throws ControllerException;

    Collection<ElectionEvent> getUnfinishedElectionEvents(final Integer electionId) throws ControllerException;

    ElectionEvent getElectionEvent(final Integer eventId) throws ControllerException;

    void addCommissioner(final Person person, final Integer electionId) throws ControllerException;

    Collection<Election> getAllElection();

    Election getElection(final Integer electionId) throws ControllerException;

    Collection<Commissioner> getElectionCommissioners(final Integer electionId) throws ControllerException;

    void addVoter(final String voterLogin,final Integer eventId) throws ControllerException;

    void changeEvent(final ElectionEvent electionEvent) throws ControllerException;

    void createElectionEvent(final Integer electionId,final String name, final String info) throws ControllerException;

    Collection<Voter> getEventVoters(final Integer eventId) throws ControllerException;

    public Collection<ElectionEvent> getEndedEvents(final String login) throws ControllerException;

    public void deleteVoterFromEvent(evoting.controller.entity.Voter voter, java.lang.Integer eventId) throws evoting.controller.pojo.ControllerException;
}
