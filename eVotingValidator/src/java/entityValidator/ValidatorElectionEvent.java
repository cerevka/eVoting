/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityValidator;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author defiler
 */
@Entity
public class ValidatorElectionEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    @ManyToMany(mappedBy="votedInEvents")
    private Collection<ValidatorCandidate> candidates;
    @ManyToMany(mappedBy="electionEvents")
    private Collection<ValidatorVoter> voters;
    @OneToMany(mappedBy="electionEvent")
    private Collection<ValidatorVote> votes;
    private Boolean votingStarted;

    public Collection<ValidatorCandidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(Collection<ValidatorCandidate> candidates) {
        this.candidates = candidates;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collection<ValidatorVoter> getVoters() {
        return voters;
    }

    public void setVoters(Collection<ValidatorVoter> voters) {
        this.voters = voters;
    }

    public Collection<ValidatorVote> getVotes() {
        return votes;
    }

    public void setVotes(Collection<ValidatorVote> votes) {
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
        if (!(object instanceof ValidatorElectionEvent)) {
            return false;
        }
        ValidatorElectionEvent other = (ValidatorElectionEvent) object;
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
