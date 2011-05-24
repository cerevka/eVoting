/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.controller.entity;

import org.junit.Ignore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;
import java.util.Map;
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
public class CandidateTest {

    public CandidateTest() {
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
     * Test of getLogin method, of class Candidate.
     */
    @Test
    public void testGetLogin() {
        System.out.println("getLogin");
        Candidate instance = new Candidate();
        String expResult = "testik";
        instance.setLogin(expResult);
        String result = instance.getLogin();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLogin method, of class Candidate.
     */
    @Test
    public void testSetLogin() {
       testGetLogin();
    }

    /**
     * Test of getProgrammes method, of class Candidate.
     */
    @Test
    public void testGetProgrammes() {
        System.out.println("getProgrammes");
        Candidate instance = new Candidate();
        Map<ElectionEvent,Programme> test = new HashMap<ElectionEvent,Programme>();
        Programme program = new Programme();
        program.setText("bla");
        ElectionEvent eve = new ElectionEvent();
        test.put(eve, program);
        instance.setProgrammes(test);
        Map expResult = test;
        Map result = instance.getProgrammes();
        assertEquals(expResult, result);
    }

    /**
     * Test of setProgrammes method, of class Candidate.
     */
    @Test
    public void testSetProgrammes() {
       testGetProgrammes();
    }

    /**
     * Test of getVotedInEvents method, of class Candidate.
     */
    @Test
    public void testGetVotedInEvents() {
        System.out.println("getVotedInEvents");
        Candidate instance = new Candidate();
        ElectionEvent a =  new ElectionEvent();
        Collection<ElectionEvent> b = new ArrayList<ElectionEvent>();
        b.add(a);
        instance.setVotedInEvents(b);
        Collection expResult = b;
        Collection result = instance.getVotedInEvents();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVotedInEvents method, of class Candidate.
     */
    @Test
    public void testSetVotedInEvents() {
        testGetVotedInEvents();
    }

    /**
     * Test of hashCode method, of class Candidate.
     */
    @Test
    @Ignore
    public void testHashCode() {
        System.out.println("hashCode");
        Candidate instance = new Candidate();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Candidate.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Candidate instance = new Candidate();
        instance.setLogin("/b/");
        Candidate ecnatsni = new Candidate();
        ecnatsni.setLogin("/b/");
        assertTrue(instance.equals(ecnatsni));
    }

    /**
     * Test of toString method, of class Candidate.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Candidate instance = new Candidate();
        instance.setLogin("login");

        String expResult = "login";
        String result = instance.toString();
        assertEquals(expResult, result);
        }
}