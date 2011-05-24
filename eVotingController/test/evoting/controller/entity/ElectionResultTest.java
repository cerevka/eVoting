/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package evoting.controller.entity;

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
public class ElectionResultTest {

    public ElectionResultTest() {
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
     * Test of getElectionEvent method, of class ElectionResult.
     */
    @Test
    public void testGetElectionEvent() {
        System.out.println("getElectionEvent");
        ElectionResult instance = new ElectionResult();
        ElectionEvent expResult = new ElectionEvent();
        instance.setElectionEvent(expResult);
        ElectionEvent result = instance.getElectionEvent();
        assertEquals(expResult, result);
    }

    /**
     * Test of setElectionEvent method, of class ElectionResult.
     */
    @Test
    public void testSetElectionEvent() {
       testGetElectionEvent();
    }

    /**
     * Test of getId method, of class ElectionResult.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        ElectionResult instance = new ElectionResult();
        Integer expResult = 123;
        instance.setId(123);
        Integer result = instance.getId();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setId method, of class ElectionResult.
     */
    @Test
    public void testSetId() {
       testGetId();
    }

    /**
     * Test of getCandidate method, of class ElectionResult.
     */
    @Test
    public void testGetCandidate() {
        System.out.println("getCandidate");
        ElectionResult instance = new ElectionResult();
        Candidate expResult = new Candidate();
        instance.setCandidate(expResult);
        Candidate result = instance.getCandidate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCandidate method, of class ElectionResult.
     */
    @Test
    public void testSetCandidate() {
       testGetCandidate();
    }

    /**
     * Test of getVotes method, of class ElectionResult.
     */
    @Test
    public void testGetVotes() {
        System.out.println("getVotes");
        ElectionResult instance = new ElectionResult();
        Integer expResult = 25;
        instance.setVotes(expResult);
        Integer result = instance.getVotes();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVotes method, of class ElectionResult.
     */
    @Test
    public void testSetVotes() {
        testGetVotes();
    }

    /**
     * Test of hashCode method, of class ElectionResult.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ElectionResult instance = new ElectionResult();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        }

    /**
     * Test of equals method, of class ElectionResult.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        ElectionResult object =   new ElectionResult();
        ElectionResult instance = new ElectionResult();
        object.setId(123);
        instance.setId(123);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class ElectionResult.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ElectionResult instance = new ElectionResult();
        instance.setId(123);
        String expResult = "entity.ElectionResult[id=123]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}