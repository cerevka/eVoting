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
public class ElectionEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    @ManyToOne(cascade= CascadeType.PERSIST)
    private Election election;
    @ManyToMany(mappedBy="votedInEvents", cascade= CascadeType.PERSIST)
    private Collection<Candidate> candidates;
    @OneToMany(mappedBy="electionEvent", cascade= CascadeType.PERSIST)
    private Collection<VotesCount> votesCounts;

    public Collection<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(Collection<Candidate> candidates) {
        this.candidates = candidates;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
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
        if (!(object instanceof ElectionEvent)) {
            return false;
        }
        ElectionEvent other = (ElectionEvent) object;
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
