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
public class ElectionEventResultDTOTest {

    private int electionEvent = 0;
    private String[] candidates = {"a", "b", "c"};
    private int[] votes = {1, 2, 3};
    private ElectionEventResultDTO instance = new ElectionEventResultDTO(electionEvent, candidates, votes);

    public ElectionEventResultDTOTest() {
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
     * Test of getCandidates method, of class ElectionEventResultDTO.
     */
    @Test
    public void testGetCandidates() {
        System.out.println("getCandidates");
        String[] expResult = candidates;
        String[] result = instance.getCandidates();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCandidates method, of class ElectionEventResultDTO.
     */
    @Test
    public void testSetCandidates() {
        System.out.println("setCandidates");
        ElectionEventResultDTO instance2 = new ElectionEventResultDTO();
        instance2.setCandidates(candidates);
        String[] expResult = candidates;
        String[] result = instance2.getCandidates();
        assertEquals(expResult, result);
    }

    /**
     * Test of getElectionEvent method, of class ElectionEventResultDTO.
     */
    @Test
    public void testGetElectionEvent() {
        System.out.println("getElectionEvent");
        int expResult = electionEvent;
        int result = instance.getElectionEvent();
        assertEquals(expResult, result);

    }

    /**
     * Test of setElectionEvent method, of class ElectionEventResultDTO.
     */
    @Test
    public void testSetElectionEvent() {
        System.out.println("setElectionEvent");
        ElectionEventResultDTO instance2 = new ElectionEventResultDTO();
        instance2.setElectionEvent(electionEvent);
        int expResult = electionEvent;
        int result = instance2.getElectionEvent();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVotes method, of class ElectionEventResultDTO.
     */
    @Test
    public void testGetVotes() {
        System.out.println("getVotes");
        int[] expResult = votes;
        int[] result = instance.getVotes();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVotes method, of class ElectionEventResultDTO.
     */
    @Test
    public void testSetVotes() {
        System.out.println("setVotes");
        ElectionEventResultDTO instance2 = new ElectionEventResultDTO();
        instance2.setVotes(votes);
        int[] expResult = votes;
        int[] result = instance2.getVotes();
        assertEquals(expResult, result);
    }
}
