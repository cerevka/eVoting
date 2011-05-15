package evoting.counter.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CounterVotesCount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private CounterElectionEvent electionEvent;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private CounterCandidate candidate;
    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public CounterElectionEvent getElectionEvent() {
        return electionEvent;
    }

    public void setElectionEvent(CounterElectionEvent electionEvent) {
        this.electionEvent = electionEvent;
    }

    public CounterCandidate getCandidate() {
        return candidate;
    }

    public void setCandidate(CounterCandidate candidate) {
        this.candidate = candidate;
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
        if (!(object instanceof CounterVotesCount)) {
            return false;
        }
        CounterVotesCount other = (CounterVotesCount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityCounter.VotesCount[id=" + id + "]";
    }
}
