/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Local;
import pojos.CounterException;

/**
 *
 * @author mz
 */
@Local
public interface ValidatedVotesSessionLocal {

    void saveValidatedVotes(final DTO.VotesDTO votesDTO) throws CounterException;
    
}
