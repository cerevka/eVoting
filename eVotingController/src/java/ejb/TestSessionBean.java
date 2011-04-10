/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Stateless;
/**
 *
 * @author defiler
 */
@Stateless
public class TestSessionBean implements TestSessionRemote {

    public String testMethod() {
        return "ahoj";
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
}
