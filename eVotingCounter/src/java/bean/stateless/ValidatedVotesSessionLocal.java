/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.stateless;

import javax.ejb.Local;
import pojo.CounterException;

/**
 *
 * @author mz
 */
@Local
public interface ValidatedVotesSessionLocal {

    void saveValidatedVotes(final DTO.VotesDTO votesDTO) throws CounterException;
    
}
