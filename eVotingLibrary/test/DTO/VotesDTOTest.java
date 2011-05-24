/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pk
 */
public class VotesDTOTest {

    private int electionEventID = 1;
    private VoteDTO a = new VoteDTO();
    private VoteDTO b = new VoteDTO();
    private VoteDTO[] votes = {a, b};
    private VotesDTO instance = new VotesDTO(electionEventID, votes);

    public VotesDTOTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getVotes method, of class VotesDTO.
     */
    @Test
    public void testGetVotes() {
        System.out.println("getVotes");
        VoteDTO[] expResult = votes;
        VoteDTO[] result = instance.getVotes();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVotes method, of class VotesDTO.
     */
    @Test
    public void testSetVotes() {
        System.out.println("setVotes");
        VotesDTO i2 = new VotesDTO();
        i2.setVotes(votes);
        VoteDTO[] expResult = votes;
        VoteDTO[] result = i2.getVotes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getElectionEventID method, of class VotesDTO.
     */
    @Test
    public void testGetElectionEventID() {
        System.out.println("getElectionEventID");
        int expResult = electionEventID;
        int result = instance.getElectionEventID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setElectionEventID method, of class VotesDTO.
     */
    @Test
    public void testSetElectionEventID() {
        System.out.println("setElectionEventID");
        VotesDTO instance2 = new VotesDTO();
        instance2.setElectionEventID(electionEventID);
        int expResult = electionEventID;
        int result = instance2.getElectionEventID();
        assertEquals(expResult, result);
    }
}
