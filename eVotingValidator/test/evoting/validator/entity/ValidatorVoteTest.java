/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.validator.entity;

import java.sql.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
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
public class ValidatorVoteTest {

    public ValidatorVoteTest() {
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
     * Test of getElectionEvent method, of class ValidatorVote.
     */
    @Test
    public void testGetElectionEvent() {
        System.out.println("getElectionEvent");
        ValidatorVote instance = new ValidatorVote();
        ValidatorElectionEvent a = new ValidatorElectionEvent();
        ValidatorElectionEvent expResult = a;
        instance.setElectionEvent(a);
        ValidatorElectionEvent result = instance.getElectionEvent();
        assertEquals(expResult, result);
    }

    /**
     * Test of setElectionEvent method, of class ValidatorVote.
     */
    @Test
    public void testSetElectionEvent() {
        System.out.println("setElectionEvent");
        ValidatorVote instance = new ValidatorVote();
        ValidatorElectionEvent a = new ValidatorElectionEvent();
        ValidatorElectionEvent expResult = a;
        instance.setElectionEvent(a);
        ValidatorElectionEvent result = instance.getElectionEvent();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class ValidatorVote.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        ValidatorVote instance = new ValidatorVote();
        Integer expResult = 1;
        instance.setId(expResult);
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class ValidatorVote.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        ValidatorVote instance = new ValidatorVote();
        Integer expResult = 1;
        instance.setId(expResult);
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRecievedDate method, of class ValidatorVote.
     */
    @Test
    public void testGetRecievedDate() {
        System.out.println("getRecievedDate");
        ValidatorVote instance = new ValidatorVote();
        Calendar calendar = new GregorianCalendar(2000, 1, 1);
        Date date = new java.sql.Date(calendar.getTimeInMillis());
        Date expResult = date;
        instance.setRecievedDate(date);
        Date result = instance.getRecievedDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRecievedDate method, of class ValidatorVote.
     */
    @Test
    public void testSetRecievedDate() {
        System.out.println("setRecievedDate");
        ValidatorVote instance = new ValidatorVote();
        Calendar calendar = new GregorianCalendar(2000, 1, 1);
        Date date = new java.sql.Date(calendar.getTimeInMillis());
        Date expResult = date;
        instance.setRecievedDate(date);
        Date result = instance.getRecievedDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVotedCandidates method, of class ValidatorVote.
     */
    @Test
    public void testGetVotedCandidates() {
        System.out.println("getVotedCandidates");
        ValidatorVote instance = new ValidatorVote();
        ValidatorCandidate a = new ValidatorCandidate();
        Collection<ValidatorCandidate> b = new ArrayList<ValidatorCandidate>();
        b.add(a);
        instance.setVotedCandidates(b);
        Collection expResult = b;
        Collection result = instance.getVotedCandidates();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVotedCandidates method, of class ValidatorVote.
     */
    @Test
    public void testSetVotedCandidates() {
        System.out.println("setVotedCandidates");
        ValidatorVote instance = new ValidatorVote();
        ValidatorCandidate a = new ValidatorCandidate();
        Collection<ValidatorCandidate> b = new ArrayList<ValidatorCandidate>();
        b.add(a);
        instance.setVotedCandidates(b);
        Collection expResult = b;
        Collection result = instance.getVotedCandidates();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ValidatorVote.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ValidatorVote instance = new ValidatorVote();
        instance.setId(1);
        int expResult = 0;
        int result = instance.hashCode();
        assertNotSame(expResult, result);
    }

    /**
     * Test of equals method, of class ValidatorVote.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        ValidatorVote instance = new ValidatorVote();
        ValidatorVote instance2 = new ValidatorVote();
        instance.setId(1);
        instance2.setId(1);
        boolean result = instance.equals(instance2);
        assertTrue(result);
    }

    /**
     * Test of toString method, of class ValidatorVote.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ValidatorVote instance = new ValidatorVote();
        instance.setId(1);
        String expResult = "entityValidator.ValidatorVote[id=1]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
