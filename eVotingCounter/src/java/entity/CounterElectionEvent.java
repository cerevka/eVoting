package entity;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author mz
 */
@Entity
public class CounterElectionEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    @ManyToOne
    private CounterElection election;
    @ManyToMany(mappedBy="votedInEvents")
    private Collection<CounterCandidate> candidates;
    @OneToMany(mappedBy="electionEvent")
    private Collection<VotesCount> votesCounts;

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

    public Collection<VotesCount> getVotesCounts() {
        return votesCounts;
    }

    public void setVotesCounts(Collection<VotesCount> votesCounts) {
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
