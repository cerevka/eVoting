/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author defiler
 */
@Entity
@Table(name="electionResult")
public class ElectionResult implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @OneToOne(cascade= CascadeType.PERSIST)
    @JoinColumn(name="candidate")
    private Candidate candidate;
    @Column(name="votes")
    private Integer votes;
    @ManyToOne(cascade= CascadeType.PERSIST)
    private ElectionEvent electionEvent;
    //elected integer = 1 YES, 2 NO BECAUSE OF LACK OF SPACE, 3 NO BECAUSE OF INSUFFICENT votes
    private int elected;

    public int getElected() {
        return elected;
    }

    public void setElected(int elected) {
        this.elected = elected;
    }


    public ElectionEvent getElectionEvent() {
        return electionEvent;
    }

    public void setElectionEvent(ElectionEvent electionEvent) {
        this.electionEvent = electionEvent;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ElectionResult)) {
            return false;
        }
        ElectionResult other = (ElectionResult) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ElectionResult[id=" + id + "]";
    }

    public int getSVotes() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int getTVotes() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
