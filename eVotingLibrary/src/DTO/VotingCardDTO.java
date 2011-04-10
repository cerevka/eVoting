package DTO;

import java.io.Serializable;

/**
 * Defines Voting card as a Data Transfer Object.
 * @author defiler
 */
public class VotingCardDTO implements Serializable {

    private String[] candidates;
    private String token;
    private int electionEvent;

    /**
     * Creates a new VotingCardDTO with given candidates, token, login and election event.
     * @param candidates array of {@code String}s with candidates
     * @param token {@code String} with token
     * @param login {@code String} with login
     * @param electionEvent {@code Integer} with election event ??id???
     */
    public VotingCardDTO(String[] candidates, String token, int electionEvent) {
        this.candidates = candidates;
        this.token = token;
        this.electionEvent = electionEvent;
    }

    public VotingCardDTO() {
    }

    /**
     * Returns candidates.
     * @return Array of {@code String}s with candidates.
     */
    public String[] getCandidates() {
        return candidates;
    }

    /**
     * Sets candidates.
     * @param candidates array of {@code String}s with candidates
     */
    public void setCandidates(String[] candidates) {
        this.candidates = candidates;
    }

    /**
     * Returns election event ???id???.
     * @return {@code Integer} with election event ??id???.
     */
    public int getElectionEvent() {
        return electionEvent;
    }

    /**
     * Sets election event ???id???
     * @param electionEvent {@code Integer} with election event ??id???
     */
    public void setElectionEvent(int electionEvent) {
        this.electionEvent = electionEvent;
    }

    /**
     * Returns token.
     * @return {@code String} with token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets token.
     * @param token {@code String} with token
     */
    public void setLogin(String token) {
        this.token = token;
    }
}
