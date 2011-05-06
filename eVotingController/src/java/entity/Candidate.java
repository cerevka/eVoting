/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import entity.Programme;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinTable;
import javax.persistence.Table;

/**
 *
 * @author defiler
 */
@Entity
@Table(name="candidate")
public class Candidate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="login")
    private String login;
    @OneToMany(cascade= CascadeType.PERSIST)
    @JoinTable(name="eventProgramme")
    private Map<ElectionEvent, Programme> programmes;
    @ManyToMany(cascade= CascadeType.PERSIST)
    private Collection<ElectionEvent> votedInEvents;
    //candidateRole rozlisuje role profesor/student
    @Column(name="candidateRole")
    private String candidateRole;
/**
 * returns the candidate login
 * @return
 */
    public String getLogin() {
        return login;
    }
/**
 * sets the candidate login
 * @param login
 */
    public void setLogin(String login) {
        this.login = login;
    }
/*
 * Returns a map of programs to the election events of this candidate
 */
    public Map<ElectionEvent, Programme> getProgrammes() {
        return programmes;
    }
/**
 * Sets a map of programmes for this candidate
 * @param programmes
 */
    public void setProgrammes(Map<ElectionEvent, Programme> programmes) {
        this.programmes = programmes;
    }
/**
 * Returns the events this candidate has voted in
 * @return
 */
    public Collection<ElectionEvent> getVotedInEvents() {
        return votedInEvents;
    }
/**
 * sets the events this candidate has voted in
 * @param votedInEvents
 */
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

        if (!(object instanceof Candidate)) {
            return false;
        }
        Candidate other = (Candidate) object;
        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }
/**
 * returns the login of the candicate
 * @return
 */
    @Override
    public String toString() {
        return login;
    }

}
