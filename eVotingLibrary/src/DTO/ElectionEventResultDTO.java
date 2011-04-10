package DTO;

import java.io.Serializable;

/**
 * Defines the result of an election event as a Data Transfer Object.
 * @author defiler
 */
public class ElectionEventResultDTO implements Serializable {

    private int electionEvent;
    private String[] candidates; // id kandidatu
    private int[] votes; // pocet hlasu daneho kandidata (dany pocet hlasu a kandidat jsou na stejnem indexu)

    /**
     * Creates ElectionEventResultDTO from given electionEvent, code candidates and votes.
     * @param electionEvent {@code integer} with electonEvent ???id???
     * @param candidates array of {@code String}s containing candidates
     * @param votes
     */
    public ElectionEventResultDTO(int electionEvent, String[] candidates, int[] votes) {
        this.electionEvent = electionEvent;
        this.candidates = candidates;
        this.votes = votes;
    }

    public ElectionEventResultDTO() {
    }

    /**
     * Returns candidates for an election event.
     * @return Array of {@code String}s with candidates.
     */
    public String[] getCandidates() {
        return candidates;
    }

    /**
     * Sets candidates for an election event.
     * @param candidates array of {@code String}s with candidates
     */
    public void setCandidates(String[] candidates) {
        this.candidates = candidates;
    }

    /**
     * Returns ???id??? of an election event.
     * @return {@code Integer} with election event ???id???.
     */
    public int getElectionEvent() {
        return electionEvent;
    }

    /**
     * Sets ???id??? of an election event.
     * @param electionEvent {@code Integer} with election event id
     */
    public void setElectionEvent(int electionEvent) {
        this.electionEvent = electionEvent;
    }

    /**
     * Returns votes for an election event.
     * @return Array of {@code Integer}s with votes.
     */
    public int[] getVotes() {
        return votes;
    }

    /**
     * Sets votes for an election event.
     * @param votes Array of {@code Integer}s with votes
     */
    public void setVotes(int[] votes) {
        this.votes = votes;
    }
}
