/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author pk
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({DTO.VotesDTOTest.class,DTO.ElectionEventResultDTOTest.class,DTO.CandidateDTOTest.class,DTO.VotingCardDTOTest.class,DTO.VoteDTOTest.class})
public class DTOSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}