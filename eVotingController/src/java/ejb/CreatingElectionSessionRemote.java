/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Commissioner;
import entity.Election;
import entity.ElectionEvent;
import entity.Person;
import entity.Voter;
import java.util.Collection;
import javax.ejb.Remote;
import pojos.ControllerException;

/**
 *
 * @author defiler
 */
@Remote
public interface CreatingElectionSessionRemote {

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

    public void deleteVoterFromEvent(entity.Voter voter, java.lang.Integer eventId) throws pojos.ControllerException;
}
