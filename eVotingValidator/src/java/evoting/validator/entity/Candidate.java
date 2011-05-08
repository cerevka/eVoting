package evoting.validator.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Candidate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String login;
    @ManyToMany(cascade= CascadeType.PERSIST)
    private Collection<ElectionEvent> votedInEvents;

     //candidateRole rozlisuje role profesor/student
    private String candidateRole;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Collection<ElectionEvent> getVotedInEvents() {
        return votedInEvents;
    }

    public void setVotedInEvents(Collection<ElectionEvent> votedInEvents) {
        this.votedInEvents = votedInEvents;
    }

    public String getCandidateRole() {
        return candidateRole;
    }

    public void setCandidateRole(String candidateRole) {
        this.candidateRole = candidateRole;
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
        if (!(object instanceof Candidate)) {
            return false;
        }
        Candidate other = (Candidate) object;
        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityValidator.ValidatorCandidate[id=" + login + "]";
    }

}
