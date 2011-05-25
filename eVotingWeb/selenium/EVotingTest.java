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
public class EVotingTest extends SeleneseTestBase {

    @Before
    @Override
    public void setUp() throws Exception {
        selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://localhost:8080/");
        selenium.setSpeed("1000");
        selenium.start();
    }

    	@Test
	public void testEVoting() throws Exception {
		selenium.open("/evoting/");
		selenium.type("loginForm:login", "admin");
		selenium.type("loginForm:password", "admin");
		selenium.click("loginForm:submitLogin");
		selenium.click("link=Click here to continue, administrator");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Create election");
		selenium.waitForPageToLoad("30000");
		selenium.type("j_idt18:name", "Volby");
		selenium.click("j_idt18:type:0");
		selenium.click("j_idt18:j_idt24");
		selenium.click("link=Volby");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Add commissioner");
		selenium.waitForPageToLoad("30000");
		selenium.select("j_idt18:selectCom", "label=Petr Vomáčka (commissioner1)");
		selenium.click("j_idt18:j_idt23");
		selenium.click("link=Back");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=home");
		selenium.waitForPageToLoad("30000");
		selenium.type("loginForm:login", "commissioner1");
		selenium.type("loginForm:password", "commissioner1");
		selenium.click("loginForm:submitLogin");
		selenium.click("link=Click here to continue, commissioner");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=View elections");
		selenium.waitForPageToLoad("30000");
		selenium.click("//a[@onclick=\"mojarra.jsfcljs(document.getElementById('j_idt16:2:j_idt18'),{'j_idt16:2:j_idt18:j_idt19':'j_idt16:2:j_idt18:j_idt19','electionId':'1'},'');return false\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Create event");
		selenium.waitForPageToLoad("30000");
		selenium.type("j_idt16:j_idt18", "Udalost");
		selenium.type("j_idt16:j_idt20", "Popis udalosti.");
		selenium.click("j_idt16:j_idt22");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Udalost");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Add voter");
		selenium.waitForPageToLoad("30000");
		selenium.select("j_idt16:selectCom", "label=voter1");
		selenium.click("j_idt16:j_idt20");
		selenium.select("j_idt16:selectCom", "label=voter2");
		selenium.click("j_idt16:j_idt20");
		selenium.click("link=Back");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Start nomination");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=home");
		selenium.waitForPageToLoad("30000");
		selenium.type("loginForm:login", "voter1");
		selenium.type("loginForm:password", "voter1");
		selenium.click("loginForm:submitLogin");
		selenium.click("link=Click here to continue, voter");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Nominate");
		selenium.waitForPageToLoad("30000");
		selenium.select("j_idt20:j_idt22", "label=Volby - Udalost");
		selenium.click("j_idt20:j_idt27");
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=home");
		selenium.waitForPageToLoad("30000");
		selenium.type("loginForm:login", "voter2");
		selenium.type("loginForm:password", "voter2");
		selenium.click("loginForm:submitLogin");
		selenium.click("link=Click here to continue, voter");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Nominate");
		selenium.waitForPageToLoad("30000");
		selenium.select("j_idt20:j_idt22", "label=Volby - Udalost");
		selenium.click("j_idt20:j_idt27");
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=home");
		selenium.waitForPageToLoad("30000");
		selenium.type("loginForm:login", "commissioner1");
		selenium.type("loginForm:password", "commissioner1");
		selenium.click("loginForm:submitLogin");
		selenium.click("link=Click here to continue, commissioner");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=View elections");
		selenium.waitForPageToLoad("30000");
		selenium.click("//a[@onclick=\"mojarra.jsfcljs(document.getElementById('j_idt16:2:j_idt18'),{'j_idt16:2:j_idt18:j_idt19':'j_idt16:2:j_idt18:j_idt19','electionId':'1'},'');return false\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Udalost");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Stop nomination");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Start vote");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=home");
		selenium.waitForPageToLoad("30000");
		selenium.type("loginForm:login", "voter1");
		selenium.type("loginForm:password", "voter1");
		selenium.click("loginForm:submitLogin");
		selenium.click("link=Click here to continue, voter");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Vote");
		selenium.waitForPageToLoad("30000");
		selenium.select("j_idt20:j_idt22", "label=Volby - Udalost");
		selenium.click("j_idt20:j_idt25");
		selenium.click("j_idt20:j_idt21:0:j_idt24");
		selenium.click("j_idt20:j_idt35");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=home");
		selenium.waitForPageToLoad("30000");
		selenium.type("loginForm:login", "voter2");
		selenium.type("loginForm:password", "voter2");
		selenium.click("loginForm:submitLogin");
		selenium.click("link=Click here to continue, voter");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Vote");
		selenium.waitForPageToLoad("30000");
		selenium.select("j_idt20:j_idt22", "label=Volby - Udalost");
		selenium.click("j_idt20:j_idt25");
		selenium.click("j_idt20:j_idt21:0:j_idt24");
		selenium.click("j_idt20:j_idt35");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=home");
		selenium.waitForPageToLoad("30000");
		selenium.click("loginForm:login");
		selenium.type("loginForm:login", "commissioner1");
		selenium.type("loginForm:password", "commissioner1");
		selenium.click("loginForm:submitLogin");
		selenium.click("link=Click here to continue, commissioner");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=View elections");
		selenium.waitForPageToLoad("30000");
		selenium.click("//a[@onclick=\"mojarra.jsfcljs(document.getElementById('j_idt16:2:j_idt18'),{'j_idt16:2:j_idt18:j_idt19':'j_idt16:2:j_idt18:j_idt19','electionId':'1'},'');return false\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Udalost");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Stop vote");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=home");
		selenium.waitForPageToLoad("30000");
		selenium.type("loginForm:login", "voter1");
		selenium.type("loginForm:password", "voter1");
		selenium.click("loginForm:submitLogin");
		selenium.click("link=Click here to continue, voter");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Results");
		selenium.waitForPageToLoad("30000");
		selenium.click("//a[@onclick=\"mojarra.jsfcljs(document.getElementById('j_idt20:1:j_idt29'),{'j_idt20:1:j_idt29:j_idt30':'j_idt20:1:j_idt29:j_idt30','eventId':'1'},'');return false\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("30000");
	}

    @After
    @Override
    public void tearDown() throws Exception {
        selenium.stop();
    }
}
