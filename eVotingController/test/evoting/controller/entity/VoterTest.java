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
public class VoterTest {

    public VoterTest() {
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
     * Test of getElectionEvents method, of class Voter.
     */
    @Test
    public void testGetElectionEvents() {
        System.out.println("getElectionEvents");
        Voter instance = new Voter();
        Collection expResult = new ArrayList<ElectionEvent>();
        ElectionEvent a = new ElectionEvent();
        expResult.add(a);
        instance.setElectionEvents(expResult);
        Collection result = instance.getElectionEvents();
        assertEquals(expResult, result);
    }

    /**
     * Test of setElectionEvents method, of class Voter.
     */
    @Test
    public void testSetElectionEvents() {
       testGetElectionEvents();
    }

    /**
     * Test of getLogin method, of class Voter.
     */
    @Test
    public void testGetLogin() {
        System.out.println("getLogin");
        Voter instance = new Voter();
        String expResult = "login";
        instance.setLogin(expResult);
        String result = instance.getLogin();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLogin method, of class Voter.
     */
    @Test
    public void testSetLogin() {
       testGetLogin();
    }

    /**
     * Test of hashCode method, of class Voter.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Voter instance = new Voter();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Voter.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Voter object = new Voter();
        Voter instance = new Voter();
        boolean expResult = false;
        instance.setLogin("Spinmyhead");
        object.setLogin("rightround");
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Voter.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Voter instance = new Voter();
        instance.setLogin("login");
        String expResult = "entity.Voter[id=login]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}