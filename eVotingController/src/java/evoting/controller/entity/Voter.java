package evoting.controller.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "voter")
@NamedQueries({
    @NamedQuery(name = Voter.FIND_ALL, query = "SELECT v FROM Voter v"),
    @NamedQuery(name = Voter.FIND_BY_LOGIN, query = "SELECT v FROM Voter v WHERE v.login = :login")})
public class Voter implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Voter.findAll";

    public static final String FIND_BY_LOGIN = "Voter.findByLogin";

    @Id
    @Column(name = "login")
    private String login;

    @ManyToMany(cascade = CascadeType.PERSIST)
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
