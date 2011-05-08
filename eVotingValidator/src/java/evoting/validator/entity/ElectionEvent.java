package evoting.validator.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class ElectionEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    @ManyToMany(mappedBy="votedInEvents", cascade= CascadeType.PERSIST)
    private Collection<Candidate> candidates;
    @ManyToMany(mappedBy="electionEvents", cascade= CascadeType.PERSIST)
    private Collection<Voter> voters;
    @OneToMany(mappedBy="electionEvent", cascade= CascadeType.PERSIST)
    private Collection<Vote> votes;
    private Boolean votingStarted;

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

    public Collection<Voter> getVoters() {
        return voters;
    }

    public void setVoters(Collection<Voter> voters) {
        this.voters = voters;
    }

    public Collection<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Collection<Vote> votes) {
        this.votes = votes;
    }

    public Boolean getVotingStarted() {
        return votingStarted;
    }

    public void setVotingStarted(Boolean votingStarted) {
        this.votingStarted = votingStarted;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
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
        return "entityValidator.ValidatorElectionEvent[id=" + id + "]";
    }

}
