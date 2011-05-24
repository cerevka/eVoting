/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.controller.entity;

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
 * @author Murko
 */
public class ElectionEventTest {

    public ElectionEventTest() {
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
     * Test of getElectionResults method, of class ElectionEvent.
     */
    @Test
    public void testGetElectionResults() {
        System.out.println("getElectionResults");
        ElectionEvent instance = new ElectionEvent();
        Collection expResult = new ArrayList<ElectionResult>();
        ElectionResult a = new ElectionResult();
        expResult.add(a);
        instance.setElectionResults(expResult);
        Collection result = instance.getElectionResults();
        assertEquals(expResult, result);
    }

    /**
     * Test of setElectionResults method, of class ElectionEvent.
     */
    @Test
    public void testSetElectionResults() {
        testGetElectionResults();
    }

    /**
     * Test of getName method, of class ElectionEvent.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        ElectionEvent instance = new ElectionEvent();
        String expResult = "test";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(expResult, result);

    }

    /**
     * Test of setName method, of class ElectionEvent.
     */
    @Test
    public void testSetName() {
        testGetName();
    }

    /**
     * Test of getId method, of class ElectionEvent.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        ElectionEvent instance = new ElectionEvent();
        Integer expResult = 123;
        instance.setId(expResult);
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class ElectionEvent.
     */
    @Test
    public void testSetId() {
        testGetId();
    }

    /**
     * Test of getCandidates method, of class ElectionEvent.
     */
    @Test
    public void testGetCandidates() {
        System.out.println("getCandidates");
        ElectionEvent instance = new ElectionEvent();
        Collection expResult = new ArrayList<Candidate>();
        Candidate a = new Candidate();
        expResult.add(a);
        instance.setCandidates(expResult);
        Collection result = instance.getCandidates();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCandidates method, of class ElectionEvent.
     */
    @Test
    public void testSetCandidates() {
        testGetCandidates();
    }

    /**
     * Test of getNominatingStarted method, of class ElectionEvent.
     */
    @Test
    public void testGetNominatingStarted() {
        System.out.println("getNominatingStarted");
        ElectionEvent instance = new ElectionEvent();
        instance.setNominatingStarted(false);
        Boolean expResult = false;
        Boolean result = instance.getNominatingStarted();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNominatingStarted method, of class ElectionEvent.
     */
    @Test
    public void testSetNominatingStarted() {
        testGetNominatingStarted();
    }

    /**
     * Test of getVotingStarted method, of class ElectionEvent.
     */
    @Test
    public void testGetVotingStarted() {
        System.out.println("getVotingStarted");
        ElectionEvent instance = new ElectionEvent();
        Boolean expResult = true;
        instance.setVotingStarted(expResult);
        Boolean result = instance.getVotingStarted();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVotingStarted method, of class ElectionEvent.
     */
    @Test
    public void testSetVotingStarted() {
        testGetCandidates();
    }

    /**
     * Test of getVoters method, of class ElectionEvent.
     */
    @Test
    public void testGetVoters() {
        System.out.println("getVoters");
        ElectionEvent instance = new ElectionEvent();

        Collection expResult = new ArrayList<Voter>();
        Voter v = new Voter();
        expResult.add(v);
        instance.setVoters(expResult);
        Collection result = instance.getVoters();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVoters method, of class ElectionEvent.
     */
    @Test
    public void testSetVoters() {
        testGetVoters();
    }

    /**
     * Test of getInfo method, of class ElectionEvent.
     */
    @Test
    public void testGetInfo() {
        System.out.println("getInfo");
        ElectionEvent instance = new ElectionEvent();
        String expResult = "asd";
        instance.setInfo("asd");
        String result = instance.getInfo();
        assertEquals(expResult, result);
    }

    /**
     * Test of setInfo method, of class ElectionEvent.
     */
    @Test
    public void testSetInfo() {
        testGetInfo();
    }

    /**
     * Test of getFinished method, of class ElectionEvent.
     */
    @Test
    public void testGetFinished() {
        System.out.println("getFinished");
        ElectionEvent instance = new ElectionEvent();
        Boolean expResult = true;
        instance.setFinished(expResult);
        Boolean result = instance.getFinished();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFinished method, of class ElectionEvent.
     */
    @Test
    public void testSetFinished() {
        testGetFinished();
    }

    /**
     * Test of hashCode method, of class ElectionEvent.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ElectionEvent instance = new ElectionEvent();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        }

    /**
     * Test of equals method, of class ElectionEvent.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        ElectionEvent object = new ElectionEvent();
        ElectionEvent instance = new ElectionEvent();
        object.setName("asd");
        instance.setName("asd");
        instance.setId(123);
        object.setId(123);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class ElectionEvent.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ElectionEvent instance = new ElectionEvent();
        instance.setId(12);
        String expResult = "entity.ElectionEvent[id=12]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}