/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package evoting.controller.bean.stateless;

import evoting.controller.entity.ElectionEvent;
import evoting.controller.entity.ElectionResult;
import java.util.Collection;
import javax.ejb.Remote;
import evoting.controller.pojo.ControllerException;

/**
 *
 * @author defiler
 */
@Remote
public interface GeneratingResultsSessionRemote {

    void generateResult(final Integer eventId) throws ControllerException;

    void finishElectionEvent(final Integer eventId) throws ControllerException;

    void finishElection(final Integer electionId);

    Collection<ElectionEvent> getEndedEvents(final String login) throws ControllerException;

    Collection<ElectionResult> getElectionEventResults(final Integer eventId) throws ControllerException;

}
