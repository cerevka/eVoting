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
import javax.persistence.OneToMany;

/**
 *
 * @author defiler
 */
@Entity
public class ElectionEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToMany(mappedBy = "votedInEvents")
    private Collection<Candidate> candidates;
    @ManyToMany(mappedBy = "electionEvents")
    private Collection<Voter> voters;
    @OneToMany(mappedBy = "electionEvent")
    private Collection<ElectionResult> electionResults;
    private String name;
    private String info;
    private Boolean votingStarted;
    private Boolean nominatingStarted;
    private Boolean finished;
    @ManyToMany(mappedBy = "eventsToEndNominating")
    private Collection<Commissioner> comAgreeEndNominating;
    @ManyToMany(mappedBy = "eventsToStartVoting")
    private Collection<Commissioner> comAgreeStartVoting;
    @ManyToMany(mappedBy = "eventsToEndVoting")
    private Collection<Commissioner> comAgreeEndVoting;

    public Collection<Commissioner> getComAgreeEndVoting() {
        return comAgreeEndVoting;
    }

    public void setComAgreeEndVoting(Collection<Commissioner> comAgreeEndVoting) {
        this.comAgreeEndVoting = comAgreeEndVoting;
    }

    public Collection<Commissioner> getComAgreeStartVoting() {
        return comAgreeStartVoting;
    }

    public void setComAgreeStartVoting(Collection<Commissioner> comAgreeStartVoting) {
        this.comAgreeStartVoting = comAgreeStartVoting;
    }

    public Collection<Commissioner> getComAgreeEndNominating() {
        return comAgreeEndNominating;
    }

    public void setComAgreeEndNominating(Collection<Commissioner> comAgreeEndNominating) {
        this.comAgreeEndNominating = comAgreeEndNominating;
    }

    public Collection<ElectionResult> getElectionResults() {
        return electionResults;
    }

    public void setElectionResults(Collection<ElectionResult> electionResults) {
        this.electionResults = electionResults;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collection<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(Collection<Candidate> candidates) {
        this.candidates = candidates;
    }

    public Boolean getNominatingStarted() {
        return nominatingStarted;
    }

    public void setNominatingStarted(Boolean nominatingStarted) {
        this.nominatingStarted = nominatingStarted;
    }

    public Boolean getVotingStarted() {
        return votingStarted;
    }

    public void setVotingStarted(Boolean votingStarted) {
        this.votingStarted = votingStarted;
    }

    public Collection<Voter> getVoters() {
        return voters;
    }

    public void setVoters(Collection<Voter> voters) {
        this.voters = voters;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ElectionEvent)) {
            return false;
        }
        ElectionEvent other = (ElectionEvent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ElectionEvent[id=" + id + "]";
    }
}
