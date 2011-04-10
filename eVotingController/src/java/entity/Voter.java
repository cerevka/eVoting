/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author defiler
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Voter.findAll", query = "SELECT v FROM Voter v"),
    @NamedQuery(name = "Voter.findByLogin", query = "SELECT v FROM Voter v WHERE v.login = :login")})
public class Voter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String login;
    @ManyToMany
    private Collection<ElectionEvent> electionEvents;

    public Collection<ElectionEvent> getElectionEvents() {
        return electionEvents;
    }

    public void setElectionEvents(Collection<ElectionEvent> electionEvents) {
        this.electionEvents = electionEvents;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Voter)) {
            return false;
        }
        Voter other = (Voter) object;
        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Voter[id=" + login + "]";
    }

}
