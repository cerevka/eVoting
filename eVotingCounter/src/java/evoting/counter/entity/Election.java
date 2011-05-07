package evoting.counter.entity;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author mz
 */
@Entity
public class Election implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    //private byte[] privateKey;
    @OneToMany(mappedBy="election", cascade= CascadeType.PERSIST)
    private Collection<ElectionEvent> electionEvents;
    
    public Collection<ElectionEvent> getElectionEvents() {
        return electionEvents;
    }

    public void setElectionEvents(Collection<ElectionEvent> electionEvents) {
        this.electionEvents = electionEvents;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
/*
    public byte[] getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }
*/
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Election)) {
            return false;
        }
        Election other = (Election) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Election[id=" + id + "]";
    }
}
