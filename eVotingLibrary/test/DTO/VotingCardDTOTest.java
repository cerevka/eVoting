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
public class VotingCardDTOTest {

    private String[] candidates = {"test1", "test2"};
    private String token = "test";
    private int electionEvent = 1;
    private VotingCardDTO instance = new VotingCardDTO(candidates, token, electionEvent);

    public VotingCardDTOTest() {
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
     * Test of getCandidates method, of class VotingCardDTO.
     */
    @Test
    public void testGetCandidates() {
        System.out.println("getCandidates");
        String[] expResult = candidates;
        String[] result = instance.getCandidates();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCandidates method, of class VotingCardDTO.
     */
    @Test
    public void testSetCandidates() {
        System.out.println("setCandidates");
        VotingCardDTO instance2 = new VotingCardDTO();
        instance2.setCandidates(candidates);
        String[] expResult = candidates;
        String[] result = instance2.getCandidates();
        assertEquals(expResult, result);
    }

    /**
     * Test of getElectionEvent method, of class VotingCardDTO.
     */
    @Test
    public void testGetElectionEvent() {
        System.out.println("getElectionEvent");
        int expResult = electionEvent;
        int result = instance.getElectionEvent();
        assertEquals(expResult, result);
    }

    /**
     * Test of setElectionEvent method, of class VotingCardDTO.
     */
    @Test
    public void testSetElectionEvent() {
        System.out.println("setElectionEvent");
        VotingCardDTO instance2 = new VotingCardDTO();
        instance2.setElectionEvent(electionEvent);
        int expResult = electionEvent;
        int result = instance2.getElectionEvent();
        assertEquals(expResult, result);
    }

    /**
     * Test of getToken method, of class VotingCardDTO.
     */
    @Test
    public void testGetToken() {
        System.out.println("getToken");
        String expResult = token;
        String result = instance.getToken();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLogin method, of class VotingCardDTO.
     */
    @Test
    public void testSetLogin() {
        System.out.println("setLogin");
        VotingCardDTO instance2 = new VotingCardDTO();
        instance2.setLogin(token);
        String expResult = token;
        String result = instance2.getToken();
        assertEquals(expResult, result);
    }
}
