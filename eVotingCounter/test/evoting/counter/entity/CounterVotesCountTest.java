/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.counter.entity;

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
public class CounterVotesCountTest {

    public CounterVotesCountTest() {
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
     * Test of getId method, of class VotesCount.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        CounterVotesCount instance = new CounterVotesCount();
        Integer id = 1;
        instance.setId(id);
        Integer expResult = 1;
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class VotesCount.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        testGetId();
    }

    /**
     * Test of getCount method, of class VotesCount.
     */
    @Test
    public void testGetCount() {
        System.out.println("getCount");
        CounterVotesCount instance = new CounterVotesCount();
        Integer id = 1;
        instance.setCount(id);
        Integer expResult = 1;
        Integer result = instance.getCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCount method, of class VotesCount.
     */
    @Test
    public void testSetCount() {
        System.out.println("setCount");
        testGetCount();
    }

    /**
     * Test of getElectionEvent method, of class VotesCount.
     */
    @Test
    public void testGetElectionEvent() {
        System.out.println("getElectionEvent");
        CounterVotesCount instance = new CounterVotesCount();
        CounterElectionEvent a = new CounterElectionEvent();
        instance.setElectionEvent(a);
        CounterElectionEvent expResult = a;
        CounterElectionEvent result = instance.getElectionEvent();
        assertEquals(expResult, result);

    }

    /**
     * Test of setElectionEvent method, of class VotesCount.
     */
    @Test
    public void testSetElectionEvent() {
        System.out.println("setElectionEvent");
        testGetElectionEvent();
    }

    /**
     * Test of getCandidate method, of class VotesCount.
     */
    @Test
    public void testGetCandidate() {
        System.out.println("getCandidate");
        CounterCandidate candidate = new CounterCandidate();
        CounterVotesCount instance = new CounterVotesCount();
        instance.setCandidate(candidate);
        CounterCandidate expResult = candidate;
        CounterCandidate result = instance.getCandidate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCandidate method, of class VotesCount.
     */
    @Test
    public void testSetCandidate() {
        System.out.println("setCandidate");
        testGetCandidate();
    }

    /**
     * Test of hashCode method, of class VotesCount.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        CounterVotesCount instance = new CounterVotesCount();
        int expResult = 0;
        instance.setId(10);
        int result = instance.hashCode();
        assertNotSame(expResult, result);
    }

    /**
     * Test of equals method, of class VotesCount.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        CounterVotesCount instance = new CounterVotesCount();
        CounterVotesCount instance2 = new CounterVotesCount();
        instance.setId(1);
        instance2.setId(2);
        boolean expResult = false;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class VotesCount.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        CounterVotesCount instance = new CounterVotesCount();
        instance.setId(1);
        String expResult = "entityCounter.VotesCount[id=1]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
