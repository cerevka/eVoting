package DTO;

import java.io.Serializable;

/**
 * Defines Votes as an Data Transfer Object
 * @author defiler
 */
public class VotesDTO implements Serializable {

    private int electionEventID;
    private VoteDTO[] votes;

    /**
     * Created a new VotesDTO object with a given election event id and votes.
     * @param electionEventID {@code Integer} with election even id
     * @param votes Array of {@code VoteDTO}s
     */
    public VotesDTO(int electionEventID, VoteDTO[] votes) {
        this.electionEventID = electionEventID;
        this.votes = votes;
    }

    public VotesDTO() {
    }

    /**
     * Returns votes.
     * @return Array of {@code VoteDTO}s with votes.
     */
    public VoteDTO[] getVotes() {
        return votes;
    }

    /**
     * Sets votes.
     * @param votes array of {@code VoteDTO}s with votes.
     */
    public void setVotes(VoteDTO[] votes) {
        this.votes = votes;
    }

    /**
     * Returns election event id.
     * @return {@code Integer} with election event id.
     */
    public int getElectionEventID() {
        return electionEventID;
    }

    /**
     * Sets election event id.
     * @param electionEventID {@code Integer} with election event id.
     */
    public void setElectionEventID(int electionEventID) {
        this.electionEventID = electionEventID;
    }
}
