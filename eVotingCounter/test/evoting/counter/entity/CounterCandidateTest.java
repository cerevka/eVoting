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
import java.util.*;
import java.util.ArrayList;

/**
 *
 * @author pk
 */
public class CounterCandidateTest {

    public CounterCandidateTest() {
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
     * Test of getCandidateLogin method, of class CounterCandidate.
     */
    @Test
    public void testGetCandidateLogin() {
        System.out.println("getCandidateLogin");
        CounterCandidate instance = new CounterCandidate();
        String expResult = "logintest";
        instance.setCandidateLogin(expResult);
        String result = instance.getCandidateLogin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setCandidateLogin method, of class CounterCandidate.
     */
    @Test
    public void testSetCandidateLogin() {
        testGetCandidateLogin();
    }

    /**
     * Test of getVotedInEvents method, of class CounterCandidate.
     */
    @Test
    public void testGetVotedInEvents() {
        System.out.println("getVotedInEvents");
        CounterCandidate instance = new CounterCandidate();
        CounterElectionEvent a = new CounterElectionEvent();
        Collection<CounterElectionEvent> b = new ArrayList<CounterElectionEvent>();
        b.add(a);
        instance.setVotedInEvents(b);
        Collection expResult = b;
        Collection result = instance.getVotedInEvents();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVotedInEvents method, of class CounterCandidate.
     */
    @Test
    public void testSetVotedInEvents() {
        testGetVotedInEvents();
    }

    /**
     * Test of getVotesCount method, of class CounterCandidate.
     */
    @Test
    public void testGetVotesCount() {
        System.out.println("getVotesCount");
        CounterCandidate instance = new CounterCandidate();
        CounterVotesCount a = new CounterVotesCount();
        Collection<CounterVotesCount> b = new ArrayList<CounterVotesCount>();
        b.add(a);
        instance.setVotesCount(b);
        Collection expResult = b;
        Collection result = instance.getVotesCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVotesCount method, of class CounterCandidate.
     */
    @Test
    public void testSetVotesCount() {
        testGetVotesCount();
    }

    /**
     * Test of hashCode method, of class CounterCandidate.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        CounterCandidate instance = new CounterCandidate();
        int expResult = 0;
        instance.setCandidateLogin("test");
        int result = instance.hashCode();
        assertNotSame(expResult, result);
    }

    /**
     * Test of equals method, of class CounterCandidate.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        CounterCandidate instance = new CounterCandidate();
        CounterCandidate instance2 = new CounterCandidate();
        instance.setCandidateLogin("test");
        instance2.setCandidateLogin("test");
        boolean result = instance.equals(instance2);
        assertTrue(result);

    }

    /**
     * Test of toString method, of class CounterCandidate.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        CounterCandidate instance = new CounterCandidate();
        instance.setCandidateLogin("test");
        String expResult = "entity.Candidate[id=test]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
