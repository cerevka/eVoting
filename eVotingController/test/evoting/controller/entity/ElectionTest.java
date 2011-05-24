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
public class ElectionTest {

    public ElectionTest() {
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
     * Test of getId method, of class Election.
     */
    @Test
    public void testGetId() {
        testSetId();
    }

    /**
     * Test of setId method, of class Election.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Integer id = 111;
        Election instance = new Election();
        instance.setId(id);
        Integer i = instance.getId();
        assertTrue(id==i);
    }

    /**
     * Test of getName method, of class Election.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Election instance = new Election();
        String expResult = "meno";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Election.
     */
    @Test
    public void testSetName() {
       testGetName();
    }

    /**
     * Test of getElectionEvents method, of class Election.
     */
    @Test
    public void testGetElectionEvents() {
        System.out.println("getElectionEvents");
        Election instance = new Election();
        Collection expResult = new ArrayList<ElectionEvent>();
        ElectionEvent a = new ElectionEvent();
        expResult.add(a);
        instance.setElectionEvents(expResult);
        Collection result = instance.getElectionEvents();
        assertEquals(expResult, result);
    }

    /**
     * Test of setElectionEvents method, of class Election.
     */
    @Test
    public void testSetElectionEvents() {
        testGetElectionEvents();
    }

    /**
     * Test of getCommissioners method, of class Election.
     */
    @Test
    public void testGetCommissioners() {
        System.out.println("getCommissioners");
        Election instance = new Election();
        Collection expResult = new ArrayList<Commissioner>();
        Commissioner a = new Commissioner();
        expResult.add(a);
        instance.setCommissioners(expResult);
        Collection result = instance.getCommissioners();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCommissioners method, of class Election.
     */
    @Test
    public void testSetCommissioners() {
      testGetCommissioners();
    }

    /**
     * Test of getType method, of class Election.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Election instance = new Election();
        String expResult = "test";
        instance.setType(expResult);
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setType method, of class Election.
     */
    @Test
    public void testSetType() {
        testGetType();
    }

    /**
     * Test of hashCode method, of class Election.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Election instance = new Election();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Election.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Election instance = new Election();
        Election instance2 = new Election();
        instance.setId(123);
        instance2.setId(123);
        instance.setName("123");
        instance2.setName("123");
        boolean expResult = true;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Election.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Election instance = new Election();
        instance.setId(123);
        instance.setName("123");
        String expResult = "entity.Election[id=123]";
        String result = instance.toString();
        assertEquals(expResult, result);
        }
}