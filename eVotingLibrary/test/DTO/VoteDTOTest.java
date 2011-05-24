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
public class VoteDTOTest {

    private String[] votedCandidates = {"a", "b"};
    private VoteDTO instance = new VoteDTO(votedCandidates);

    public VoteDTOTest() {
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
     * Test of getVotedCandidates method, of class VoteDTO.
     */
    @Test
    public void testGetVotedCandidates() {
        System.out.println("getVotedCandidates");
        String[] expResult = {"a", "b"};
        instance.setVotedCandidates(expResult);
        String[] result = instance.getVotedCandidates();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVotedCandidates method, of class VoteDTO.
     */
    @Test
    public void testSetVotedCandidates() {
        System.out.println("setVotedCandidates");
        VoteDTO instance2 = new VoteDTO();
        String[] expResult = votedCandidates;
        instance2.setVotedCandidates(votedCandidates);
        String[] result = instance2.getVotedCandidates();
        assertEquals(expResult, result);
    }
}
