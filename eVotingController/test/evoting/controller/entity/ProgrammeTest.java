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
public class ProgrammeTest {

    public ProgrammeTest() {
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
     * Test of getId method, of class Programme.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Programme instance = new Programme();
        Integer expResult = 123;
        instance.setId(123);
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Programme.
     */
    @Test
    public void testSetId() {
        testGetId();
    }

    /**
     * Test of getText method, of class Programme.
     */
    @Test
    public void testGetText() {
        System.out.println("getText");
        Programme instance = new Programme();
        String expResult = "aaa";
        instance.setText("aaa");
        String result = instance.getText();
        assertEquals(expResult, result);
    }

    /**
     * Test of setText method, of class Programme.
     */
    @Test
    public void testSetText() {
        testGetText();
    }

    /**
     * Test of hashCode method, of class Programme.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Programme instance = new Programme();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Programme.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Programme object = new Programme();
        Programme instance = new Programme();
        boolean expResult = true;
        object.setId(258);
        instance.setId(258);
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Programme.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Programme instance = new Programme();
        instance.setId(258);
        String expResult = "entity.Program[id=258]";
        String result = instance.toString();
        assertEquals(expResult, result);

    }

}