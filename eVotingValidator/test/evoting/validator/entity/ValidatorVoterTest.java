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
public class ValidatorVoterTest {

    public ValidatorVoterTest() {
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
     * Test of getElectionEvents method, of class ValidatorVoter.
     */
    @Test
    public void testGetElectionEvents() {
        System.out.println("getElectionEvents");
        ValidatorVoter instance = new ValidatorVoter();
        ValidatorElectionEvent a = new ValidatorElectionEvent();
        Collection<ValidatorElectionEvent> b = new ArrayList<ValidatorElectionEvent>();
        b.add(a);
        Collection expResult = b;
        instance.setElectionEvents(b);
        Collection result = instance.getElectionEvents();
        assertEquals(expResult, result);
    }

    /**
     * Test of setElectionEvents method, of class ValidatorVoter.
     */
    @Test
    public void testSetElectionEvents() {
        System.out.println("setElectionEvents");
        ValidatorVoter instance = new ValidatorVoter();
        ValidatorElectionEvent a = new ValidatorElectionEvent();
        Collection<ValidatorElectionEvent> b = new ArrayList<ValidatorElectionEvent>();
        b.add(a);
        Collection expResult = b;
        instance.setElectionEvents(b);
        Collection result = instance.getElectionEvents();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLogin method, of class ValidatorVoter.
     */
    @Test
    public void testGetLogin() {
        System.out.println("getLogin");
        ValidatorVoter instance = new ValidatorVoter();
        String expResult = "testlogin";
        instance.setLogin(expResult);
        String result = instance.getLogin();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLogin method, of class ValidatorVoter.
     */
    @Test
    public void testSetLogin() {
        System.out.println("setLogin");
        ValidatorVoter instance = new ValidatorVoter();
        String expResult = "testlogin";
        instance.setLogin(expResult);
        String result = instance.getLogin();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVotes method, of class ValidatorVoter.
     */
    @Test
    public void testGetVotes() {
        System.out.println("getVotes");
        ValidatorVoter instance = new ValidatorVoter();
        ValidatorVote a = new ValidatorVote();
        Collection<ValidatorVote> b = new ArrayList<ValidatorVote>();
        b.add(a);
        Collection expResult = b;
        instance.setVotes(b);
        Collection result = instance.getVotes();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVotes method, of class ValidatorVoter.
     */
    @Test
    public void testSetVotes() {
        System.out.println("setVotes");
        ValidatorVoter instance = new ValidatorVoter();
        ValidatorVote a = new ValidatorVote();
        Collection<ValidatorVote> b = new ArrayList<ValidatorVote>();
        b.add(a);
        Collection expResult = b;
        instance.setVotes(b);
        Collection result = instance.getVotes();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ValidatorVoter.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ValidatorVoter instance = new ValidatorVoter();
        instance.setLogin("test");
        int expResult = 0;
        int result = instance.hashCode();
        assertNotSame(expResult, result);
    }

    /**
     * Test of equals method, of class ValidatorVoter.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        ValidatorVoter instance = new ValidatorVoter();
        ValidatorVoter instance2 = new ValidatorVoter();
        instance.setLogin("test");
        instance2.setLogin("test");
        boolean result = instance.equals(instance2);
        assertTrue(result);
    }

    /**
     * Test of toString method, of class ValidatorVoter.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ValidatorVoter instance = new ValidatorVoter();
        instance.setLogin("test");
        String expResult = "entityValidator.ValidatorVoter[id=test]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
