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
public class CounterElectionTest {

    public CounterElectionTest() {
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
     * Test of getElectionEvents method, of class CounterElection.
     */
    @Test
    public void testGetElectionEvents() {
        System.out.println("getElectionEvents");
        CounterElection instance = new CounterElection();
        CounterElectionEvent a = new CounterElectionEvent();
        Collection<CounterElectionEvent> b = new ArrayList<CounterElectionEvent>();
        b.add(a);
        instance.setElectionEvents(b);
        Collection expResult = b;
        Collection result = instance.getElectionEvents();
        assertEquals(expResult, result);
    }

    /**
     * Test of setElectionEvents method, of class CounterElection.
     */
    @Test
    public void testSetElectionEvents() {
        System.out.println("setElectionEvents");
        testGetElectionEvents();
    }

    /**
     * Test of getId method, of class CounterElection.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        CounterElection instance = new CounterElection();
        Integer id = 1;
        instance.setId(id);
        Integer expResult = 1;
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class CounterElection.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        testGetId();
    }

    /**
     * Test of hashCode method, of class CounterElection.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        CounterElection instance = new CounterElection();
        int expResult = 0;
        instance.setId(10);
        int result = instance.hashCode();
        assertNotSame(expResult, result);
    }

    /**
     * Test of equals method, of class CounterElection.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        CounterElection instance = new CounterElection();
        CounterElection instance2 = new CounterElection();
        instance.setId(1);
        instance2.setId(2);
        boolean expResult = false;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class CounterElection.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        CounterElection instance = new CounterElection();
        instance.setId(1);
        String expResult = "entity.Election[id=1]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
