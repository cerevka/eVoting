/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Tomas Cerevka
 */
public class ElectionTest extends SeleneseTestBase {

    @Before
    @Override
    public void setUp() throws Exception {
        selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://localhost:8080/"); 
        selenium.setSpeed("1000");
        selenium.start();
    }

    @Test
    public void testCreateElection() throws Exception {
        selenium.open("/evoting/");
        selenium.waitForPageToLoad("30000");
        selenium.type("j_username", "admin");
        selenium.type("j_password", "admin");
        selenium.click("//input[@value='login']");
        selenium.selectFrame("relative=up");        
        selenium.click("link=Click here to continue, administrator");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=Create election");
        selenium.waitForPageToLoad("30000");
        selenium.type("j_idt18:name", "volby1");
        selenium.click("j_idt18:type:0");
        selenium.click("j_idt18:j_idt24");
        selenium.click("link=volby1");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=add commissioner");
        selenium.waitForPageToLoad("30000");
        selenium.select("j_idt18:selectCom", "label=Petr Vomáčka (commissioner1)");
        selenium.click("j_idt18:j_idt23");
        selenium.click("link=Back to Election event");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=logout");
        selenium.waitForPageToLoad("30000");         
    }
    
    //@Test
    public void testAddCommissioner() throws Exception {
        selenium.open("/evoting/");
        selenium.waitForPageToLoad("30000");
        selenium.type("j_username", "admin");
        selenium.type("j_password", "admin");
        selenium.click("//input[@value='login']");
        selenium.selectFrame("relative=up");        
        selenium.click("link=Click here to continue, administrator");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=Create election");
        selenium.waitForPageToLoad("30000");
        selenium.type("j_idt18:name", "volby1");
        selenium.click("j_idt18:type:0");
        selenium.click("j_idt18:j_idt24");
        selenium.click("link=volby1");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=add commissioner");
        selenium.waitForPageToLoad("30000");
        selenium.select("j_idt18:selectCom", "label=Petr Vomáčka (commissioner1)");
        selenium.click("j_idt18:j_idt23");
        selenium.click("link=Back to Election event");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=logout");
        selenium.waitForPageToLoad("30000");         
    }

    @After
    @Override
    public void tearDown() throws Exception {
        selenium.stop();
    }
}
