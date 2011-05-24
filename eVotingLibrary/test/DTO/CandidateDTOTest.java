/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

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
public class CandidateDTOTest {

    public CandidateDTOTest() {
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
     * Test of getFirstName method, of class CandidateDTO.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        String a = "test";
        CandidateDTO instance = new CandidateDTO();
        instance.setFirstName(a);
        String result = instance.getFirstName();
        String expResult = a;
        assertEquals(expResult, result);
    }

    /**
     * Test of setFirstName method, of class CandidateDTO.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String a = "test";
        CandidateDTO instance = new CandidateDTO();
        instance.setFirstName(a);
        String result = instance.getFirstName();
        String expResult = a;
        assertEquals(expResult, result);
    }

    /**
     * Test of getLastName method, of class CandidateDTO.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        CandidateDTO instance = new CandidateDTO();
        String a = "test";
        instance.setLastName(a);
        String result = instance.getLastName();
        String expResult = a;
        assertEquals(expResult, result);
    }

    /**
     * Test of setLastName method, of class CandidateDTO.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String a = "test";
        CandidateDTO instance = new CandidateDTO();
        instance.setLastName(a);
        String result = instance.getLastName();
        String expResult = a;
        assertEquals(expResult, result);
    }

    /**
     * Test of getLogin method, of class CandidateDTO.
     */
    @Test
    public void testGetLogin() {
        System.out.println("getLogin");
        String a = "test";
        CandidateDTO instance = new CandidateDTO();
        instance.setLogin(a);
        String result = instance.getLogin();
        String expResult = a;
        assertEquals(expResult, result);
    }

    /**
     * Test of setLogin method, of class CandidateDTO.
     */
    @Test
    public void testSetLogin() {
        System.out.println("setLogin");
        String a = "test";
        CandidateDTO instance = new CandidateDTO();
        instance.setLogin(a);
        String result = instance.getLogin();
        String expResult = a;
        assertEquals(expResult, result);
    }

    /**
     * Test of getProgramme method, of class CandidateDTO.
     */
    @Test
    public void testGetProgramme() {
        System.out.println("getProgramme");
        CandidateDTO instance = new CandidateDTO();
        String a = "test";
        instance.setProgramme(a);
        String result = instance.getProgramme();
        String expResult = a;
        assertEquals(expResult, result);
    }

    /**
     * Test of setProgramme method, of class CandidateDTO.
     */
    @Test
    public void testSetProgramme() {
        System.out.println("setProgramme");
        String a = "test";
        CandidateDTO instance = new CandidateDTO();
        instance.setProgramme(a);
        String result = instance.getProgramme();
        String expResult = a;
        assertEquals(expResult, result);
    }
}
