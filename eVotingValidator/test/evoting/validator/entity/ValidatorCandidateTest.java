/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.validator.entity;

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
public class ValidatorCandidateTest {

    private ValidatorCandidate instance = new ValidatorCandidate();
    private String login = "test";
    ValidatorElectionEvent a = new ValidatorElectionEvent();
    Collection<ValidatorElectionEvent> votedInEvents = new ArrayList<ValidatorElectionEvent>();

    public ValidatorCandidateTest() {
        votedInEvents.add(a);
        instance.setLogin(login);
        instance.setVotedInEvents(votedInEvents);
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
     * Test of getLogin method, of class ValidatorCandidate.
     */
    @Test
    public void testGetLogin() {
        System.out.println("getLogin");
        String expResult = login;
        String result = instance.getLogin();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLogin method, of class ValidatorCandidate.
     */
    @Test
    public void testSetLogin() {
        System.out.println("setLogin");
        ValidatorCandidate instance2 = new ValidatorCandidate();
        instance2.setLogin(login);
        String expResult = login;
        String result = instance2.getLogin();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVotedInEvents method, of class ValidatorCandidate.
     */
    @Test
    public void testGetVotedInEvents() {
        System.out.println("getVotedInEvents");
        Collection expResult = votedInEvents;
        Collection result = instance.getVotedInEvents();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVotedInEvents method, of class ValidatorCandidate.
     */
    @Test
    public void testSetVotedInEvents() {
        System.out.println("setVotedInEvents");
        ValidatorCandidate instance2 = new ValidatorCandidate();
        instance2.setVotedInEvents(votedInEvents);
        Collection expResult = votedInEvents;
        Collection result = instance2.getVotedInEvents();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ValidatorCandidate.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = 0;
        int result = instance.hashCode();
        assertNotSame(expResult, result);
    }

    /**
     * Test of equals method, of class ValidatorCandidate.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        ValidatorCandidate instance2 = new ValidatorCandidate();
        instance2.setLogin(login);
        instance2.setLogin(login);
        boolean result = instance.equals(instance2);
        assertTrue(result);
    }

    /**
     * Test of toString method, of class ValidatorCandidate.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "entityValidator.ValidatorCandidate[id=test]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
