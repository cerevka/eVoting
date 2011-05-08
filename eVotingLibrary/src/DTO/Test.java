package DTO;

import java.io.Serializable;

public class Test implements Serializable {

    private String[] candidates;
    private String token;
    private int electionEvent;

    public Test() {
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

    public String sayHello() {
        return "Hello";
    }
}
