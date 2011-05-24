/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.validator.entity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 *
 * @author pk
 */
public class ValidatorElectionEventTest {

    public ValidatorElectionEventTest() {
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
     * Test of getCandidates method, of class ValidatorElectionEvent.
     */
    @Test
    public void testGetCandidates() {
        System.out.println("getCandidates");
        ValidatorElectionEvent instance = new ValidatorElectionEvent();
        ValidatorCandidate a = new ValidatorCandidate();
        Collection<ValidatorCandidate> b = new ArrayList<ValidatorCandidate>();
        b.add(a);
        instance.setCandidates(b);
        Collection expResult = b;
        Collection result = instance.getCandidates();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCandidates method, of class ValidatorElectionEvent.
     */
    @Test
    public void testSetCandidates() {
        System.out.println("setCandidates");
        ValidatorElectionEvent instance = new ValidatorElectionEvent();
        ValidatorCandidate a = new ValidatorCandidate();
        Collection<ValidatorCandidate> b = new ArrayList<ValidatorCandidate>();
        b.add(a);
        instance.setCandidates(b);
        Collection expResult = b;
        Collection result = instance.getCandidates();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class ValidatorElectionEvent.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        ValidatorElectionEvent instance = new ValidatorElectionEvent();
        Integer expResult = 1;
        instance.setId(expResult);
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class ValidatorElectionEvent.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        ValidatorElectionEvent instance = new ValidatorElectionEvent();
        Integer expResult = 1;
        instance.setId(expResult);
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVoters method, of class ValidatorElectionEvent.
     */
    @Test
    public void testGetVoters() {
        System.out.println("getVoters");
        ValidatorElectionEvent instance = new ValidatorElectionEvent();
        ValidatorVoter a = new ValidatorVoter();
        Collection<ValidatorVoter> b = new ArrayList<ValidatorVoter>();
        b.add(a);
        instance.setVoters(b);
        Collection expResult = b;
        Collection result = instance.getVoters();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVoters method, of class ValidatorElectionEvent.
     */
    @Test
    public void testSetVoters() {
        System.out.println("setVoters");
        ValidatorElectionEvent instance = new ValidatorElectionEvent();
        ValidatorVoter a = new ValidatorVoter();
        Collection<ValidatorVoter> b = new ArrayList<ValidatorVoter>();
        b.add(a);
        instance.setVoters(b);
        Collection expResult = b;
        Collection result = instance.getVoters();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVotes method, of class ValidatorElectionEvent.
     */
    @Test
    public void testGetVotes() {
        System.out.println("getVotes");
        ValidatorElectionEvent instance = new ValidatorElectionEvent();
        ValidatorVote a = new ValidatorVote();
        Collection<ValidatorVote> b = new ArrayList<ValidatorVote>();
        b.add(a);
        instance.setVotes(b);
        Collection expResult = b;
        Collection result = instance.getVotes();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVotes method, of class ValidatorElectionEvent.
     */
    @Test
    public void testSetVotes() {
        System.out.println("setVotes");
        ValidatorElectionEvent instance = new ValidatorElectionEvent();
        ValidatorVote a = new ValidatorVote();
        Collection<ValidatorVote> b = new ArrayList<ValidatorVote>();
        b.add(a);
        instance.setVotes(b);
        Collection expResult = b;
        Collection result = instance.getVotes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVotingStarted method, of class ValidatorElectionEvent.
     */
    @Test
    public void testGetVotingStarted() {
        System.out.println("getVotingStarted");
        ValidatorElectionEvent instance = new ValidatorElectionEvent();
        Boolean expResult = false;
        instance.setVotingStarted(expResult);
        Boolean result = instance.getVotingStarted();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVotingStarted method, of class ValidatorElectionEvent.
     */
    @Test
    public void testSetVotingStarted() {
        System.out.println("setVotingStarted");
        ValidatorElectionEvent instance = new ValidatorElectionEvent();
        Boolean expResult = false;
        instance.setVotingStarted(expResult);
        Boolean result = instance.getVotingStarted();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ValidatorElectionEvent.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ValidatorElectionEvent instance = new ValidatorElectionEvent();
        instance.setId(1);
        int expResult = 0;
        int result = instance.hashCode();
        assertNotSame(expResult, result);
    }

    /**
     * Test of equals method, of class ValidatorElectionEvent.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        ValidatorElectionEvent instance = new ValidatorElectionEvent();
        ValidatorElectionEvent instance2 = new ValidatorElectionEvent();
        instance.setId(1);
        instance2.setId(1);
        boolean result = instance.equals(instance2);
        assertTrue(result);
    }

    /**
     * Test of toString method, of class ValidatorElectionEvent.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ValidatorElectionEvent instance = new ValidatorElectionEvent();
        instance.setId(1);
        String expResult = "entityValidator.ValidatorElectionEvent[id=1]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
