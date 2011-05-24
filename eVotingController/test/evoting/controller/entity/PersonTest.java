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
public class PersonTest {

    public PersonTest() {
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
     * Test of getLastname method, of class Person.
     */
    @Test
    public void testGetLastname() {
        System.out.println("getLastname");
        Person instance = new Person();
        String expResult = "asd";
        instance.setLastname(expResult);
        String result = instance.getLastname();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLastname method, of class Person.
     */
    @Test
    public void testSetLastname() {
        testGetLastname();
    }

    /**
     * Test of getFirstname method, of class Person.
     */
    @Test
    public void testGetFirstname() {
        System.out.println("getFirstname");
        Person instance = new Person();
        String expResult = "sss";
        instance.setFirstname(expResult);
        String result = instance.getFirstname();

        assertEquals(expResult, result);
    
    }

    /**
     * Test of setFirstname method, of class Person.
     */
    @Test
    public void testSetFirstname() {
       testGetFirstname();
    }

    /**
     * Test of getLogin method, of class Person.
     */
    @Test
    public void testGetLogin() {
        System.out.println("getLogin");
        Person instance = new Person();
        String expResult = "258";
        instance.setLogin(expResult);
        String result = instance.getLogin();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setLogin method, of class Person.
     */
    @Test
    public void testSetLogin() {
        testGetLogin();
    }

    /**
     * Test of getPersonGroup method, of class Person.
     */
    @Test
    public void testGetPersonGroup() {
        System.out.println("getPersonGroup");
        Person instance = new Person();
        String expResult = "admin";
        instance.setPersonGroup("admin");
        String result = instance.getPersonGroup();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPersonGroup method, of class Person.
     */
    @Test
    public void testSetPersonGroup() {
       testGetPersonGroup();
    }

    /**
     * Test of getPassword method, of class Person.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Person instance = new Person();
        String expResult = "nbusr123";
        instance.setPassword("nbusr123");
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class Person.
     */
    @Test
    public void testSetPassword() {
        testGetPassword();
    }

    /**
     * Test of hashCode method, of class Person.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Person instance = new Person();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Person.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Person object = new Person();
        Person instance = new Person();
        boolean expResult = true;
        instance.setLogin("Bravo");
        object.setLogin("Bravo");
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Person.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Person instance = new Person();
        instance.setFirstname("Alpha");
        instance.setLastname("Bravo");
        instance.setLogin("Charlie");

        String expResult = "Alpha Bravo (Charlie)";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}