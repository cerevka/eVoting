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
public class UserGroupTest {

    public UserGroupTest() {
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
     * Test of getLogin method, of class UserGroup.
     */
    @Test
    public void testGetLogin() {
        System.out.println("getLogin");
        UserGroup instance = new UserGroup();
        String expResult = "murko";
        instance.setLogin(expResult);
        String result = instance.getLogin();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLogin method, of class UserGroup.
     */
    @Test
    public void testSetLogin() {
        testGetLogin();
    }

    /**
     * Test of getUserGroup method, of class UserGroup.
     */
    @Test
    public void testGetUserGroup() {
        System.out.println("getUserGroup");
        UserGroup instance = new UserGroup();
        String expResult = "aaa";
        instance.setUserGroup("aaa");
        String result = instance.getUserGroup();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserGroup method, of class UserGroup.
     */
    @Test
    public void testSetUserGroup() {
       testGetUserGroup();
    }

    /**
     * Test of hashCode method, of class UserGroup.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        UserGroup instance = new UserGroup();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of equals method, of class UserGroup.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        UserGroup object = new UserGroup();
        UserGroup instance = new UserGroup();
        object.setLogin("TNT");
        instance.setLogin("moneytalks");
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class UserGroup.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        UserGroup instance = new UserGroup();
        instance.setLogin("login");
        String expResult = "entity.UserGroup[id=login]";
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }

}