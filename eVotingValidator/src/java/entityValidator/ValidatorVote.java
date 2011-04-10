/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entityValidator;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author defiler
 */
@Entity
public class ValidatorVote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date recievedDate;
    @OneToMany
    Collection<ValidatorCandidate> votedCandidates;
    @ManyToOne
    private ValidatorElectionEvent electionEvent;

    public ValidatorElectionEvent getElectionEvent() {
        return electionEvent;
    }

    public void setElectionEvent(ValidatorElectionEvent electionEvent) {
        this.electionEvent = electionEvent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRecievedDate() {
        return recievedDate;
    }

    public void setRecievedDate(Date recievedDate) {
        this.recievedDate = recievedDate;
    }

    public Collection<ValidatorCandidate> getVotedCandidates() {
        return votedCandidates;
    }

    public void setVotedCandidates(Collection<ValidatorCandidate> votedCandidates) {
        this.votedCandidates = votedCandidates;
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
        if (!(object instanceof ValidatorVote)) {
            return false;
        }
        ValidatorVote other = (ValidatorVote) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityValidator.ValidatorVote[id=" + id + "]";
    }

}
