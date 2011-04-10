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
public class ValidatorVoter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String login;
    @OneToMany
    private Collection<ValidatorVote> votes;
    @ManyToMany
    private Collection<ValidatorElectionEvent> electionEvents;

    public Collection<ValidatorElectionEvent> getElectionEvents() {
        return electionEvents;
    }

    public void setElectionEvents(Collection<ValidatorElectionEvent> electionEvents) {
        this.electionEvents = electionEvents;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Collection<ValidatorVote> getVotes() {
        return votes;
    }

    public void setVotes(Collection<ValidatorVote> votes) {
        this.votes = votes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValidatorVoter)) {
            return false;
        }
        ValidatorVoter other = (ValidatorVoter) object;
        if ((this.login == null && other.login != null) || (login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityValidator.ValidatorVoter[id=" + login + "]";
    }

}
