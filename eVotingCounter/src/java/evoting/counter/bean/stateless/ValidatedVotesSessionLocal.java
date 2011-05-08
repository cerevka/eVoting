package evoting.counter.bean.stateless;

import javax.ejb.Local;
import evoting.counter.pojo.CounterException;

@Local
public interface ValidatedVotesSessionLocal {

    void saveValidatedVotes(final DTO.VotesDTO votesDTO) throws CounterException;
    
}
