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
public class CommissionerTest {

    public CommissionerTest() {
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
     * Test of getFirstName method, of class Commissioner.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        Commissioner instance = new Commissioner();
        instance.setFirstName("test");
        String expResult = "test";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFirstName method, of class Commissioner.
     */
    @Test
    public void testSetFirstName() {
       testGetFirstName();

    }

    /**
     * Test of getLastName method, of class Commissioner.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        Commissioner instance = new Commissioner();
        String expResult = "last";
        instance.setLastName(expResult);
        String result = instance.getLastName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLastName method, of class Commissioner.
     */
    @Test
    public void testSetLastName() {
        testGetLastName();
    }

    /**
     * Test of getLogin method, of class Commissioner.
     */
    @Test
    public void testGetLogin() {
        System.out.println("getLogin");
        Commissioner instance = new Commissioner();
        String expResult = "login";
        instance.setLogin(expResult);
        String result = instance.getLogin();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLogin method, of class Commissioner.
     */
    @Test
    public void testSetLogin() {
       testGetLogin();
    }

    /**
     * Test of getElections method, of class Commissioner.
     */
    @Test
    public void testGetElections() {
     testSetElections();
    }

    /**
     * Test of setElections method, of class Commissioner.
     */
    @Test
    public void testSetElections() {
        System.out.println("setElections");
        Collection<Election> elections = new ArrayList<Election>();
        Election mock = new Election();
        elections.add(mock);
        Commissioner instance = new Commissioner();
        instance.setElections(elections);
        assertEquals(instance.getElections(),elections);
    }

    /**
     * Test of hashCode method, of class Commissioner.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Commissioner instance = new Commissioner();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Commissioner.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Commissioner instance = new Commissioner();
        Commissioner instance2 = new Commissioner();
        instance.setLogin("genie");
        instance2.setLogin("genie");
        boolean expResult = true;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
        }

    /**
     * Test of toString method, of class Commissioner.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Commissioner instance = new Commissioner();
        instance.setLogin("login");
        String expResult = "entity.Commissioner[id=login]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}