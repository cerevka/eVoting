package DTO;

import java.io.Serializable;

/**
 * Defines Vote as a Data Transfer Object
 * @author defiler
 */
public class VoteDTO implements Serializable {

    private String[] votedCandidates;

    /**
     * Creates a new VoteDTO for given voted candidates.
     * @param votedCandidates array of  {@code String}s with voted candidates
     */
    public VoteDTO(String[] votedCandidates) {
        this.votedCandidates = votedCandidates;
    }

    public VoteDTO() {
    }

    /**
     * Returns voted candidates.
     * @return Array of {@code String}s with voted candidates.
     */
    public String[] getVotedCandidates() {
        return votedCandidates;
    }

    /**
     * Sets voted candidates.
     * @param votedCandidates array of {@code String}s with voted candidates.
     */
    public void setVotedCandidates(String[] votedCandidates) {
        this.votedCandidates = votedCandidates;
    }
}
