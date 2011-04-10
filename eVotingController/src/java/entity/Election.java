/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author defiler
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Election.findAll", query = "SELECT e FROM Election e")})
public class Election implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @ManyToMany(mappedBy = "elections")
    private Collection<Commissioner> commissioners;
    @OneToMany
    private Collection<ElectionEvent> electionEvents;
    //private byte[] publicKey;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /*
    public byte[] getPublicKey() {
    return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
    this.publicKey = publicKey;
    }
     */

    public Collection<ElectionEvent> getElectionEvents() {
        return electionEvents;
    }

    public void setElectionEvents(Collection<ElectionEvent> electionEvents) {
        this.electionEvents = electionEvents;
    }

    public Collection<Commissioner> getCommissioners() {
        return commissioners;
    }

    public void setCommissioners(Collection<Commissioner> commissioners) {
        this.commissioners = commissioners;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Election)) {
            return false;
        }
        Election other = (Election) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Election[id=" + name + "]";
    }
}
