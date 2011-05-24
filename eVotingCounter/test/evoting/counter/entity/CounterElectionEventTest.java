/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package evoting.counter.entity;

import java.util.ArrayList;
import java.util.Collection;
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
public class CounterElectionEventTest {

    public CounterElectionEventTest() {
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
     * Test of getCandidates method, of class CounterElectionEvent.
     */
    @Test
    public void testGetCandidates() {
        System.out.println("getCandidates");
        CounterCandidate a = new CounterCandidate();
        Collection<CounterCandidate> b = new ArrayList<CounterCandidate>();
        b.add(a);
        CounterElectionEvent instance = new CounterElectionEvent();
        instance.setCandidates(b);
        Collection expResult = b;
        Collection result = instance.getCandidates();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCandidates method, of class CounterElectionEvent.
     */
    @Test
    public void testSetCandidates() {
        System.out.println("setCandidates");
        testGetCandidates();
    }

    /**
     * Test of getId method, of class CounterElectionEvent.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        CounterElectionEvent instance = new CounterElectionEvent();
        Integer expResult = 10;
        instance.setId(10);
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class CounterElectionEvent.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        testGetId();
    }

    /**
     * Test of getElection method, of class CounterElectionEvent.
     */
    @Test
    public void testGetElection() {
        System.out.println("getElection");
        CounterElectionEvent instance = new CounterElectionEvent();
        CounterElection expResult = new CounterElection();
        instance.setElection(expResult);
        CounterElection result = instance.getElection();
        assertEquals(expResult, result);
    }

    /**
     * Test of setElection method, of class CounterElectionEvent.
     */
    @Test
    public void testSetElection() {
        System.out.println("setElection");
        testGetElection();
    }

    /**
     * Test of getVotesCounts method, of class CounterElectionEvent.
     */
    @Test
    public void testGetVotesCounts() {
        System.out.println("getVotesCounts");
        CounterElectionEvent instance = new CounterElectionEvent();
        CounterVotesCount a = new CounterVotesCount();
        Collection<CounterVotesCount> b = new ArrayList<CounterVotesCount>();
        b.add(a);
        instance.setVotesCounts(b);
        Collection expResult = b;
        Collection result = instance.getVotesCounts();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVotesCounts method, of class CounterElectionEvent.
     */
    @Test
    public void testSetVotesCounts() {
        System.out.println("setVotesCounts");
        testGetVotesCounts();
    }

    /**
     * Test of hashCode method, of class CounterElectionEvent.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        CounterElectionEvent instance = new CounterElectionEvent();
        int expResult = 0;
        instance.setId(10);
        int result = instance.hashCode();
        assertNotSame(expResult, result);
    }

    /**
     * Test of equals method, of class CounterElectionEvent.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        CounterElectionEvent instance = new CounterElectionEvent();
        CounterElectionEvent instance2 = new CounterElectionEvent();
        instance.setId(1);
        instance2.setId(2);
        boolean expResult = false;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class CounterElectionEvent.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        CounterElectionEvent instance = new CounterElectionEvent();
        instance.setId(1);
        String expResult = "entity.ElectionEvent[id=1]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}