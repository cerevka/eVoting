package evoting.counter.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CounterElectionEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    @ManyToOne(cascade= CascadeType.PERSIST)
    private CounterElection election;
    @ManyToMany(mappedBy="votedInEvents", cascade= CascadeType.PERSIST)
    private Collection<CounterCandidate> candidates;
    @OneToMany(mappedBy="electionEvent", cascade= CascadeType.PERSIST)
    private Collection<CounterVotesCount> votesCounts;

    public Collection<CounterCandidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(Collection<CounterCandidate> candidates) {
        this.candidates = candidates;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public CounterElection getElection() {
        return election;
    }

    public void setElection(CounterElection election) {
        this.election = election;
    }

    public Collection<CounterVotesCount> getVotesCounts() {
        return votesCounts;
    }

    public void setVotesCounts(Collection<CounterVotesCount> votesCounts) {
        this.votesCounts = votesCounts;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CounterElectionEvent)) {
            return false;
        }
        CounterElectionEvent other = (CounterElectionEvent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ElectionEvent[id=" + id + "]";
    }
}
