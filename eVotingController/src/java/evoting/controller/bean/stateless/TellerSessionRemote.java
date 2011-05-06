/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package evoting.controller.bean.stateless;

import javax.ejb.Remote;

/**
 *
 * @author defiler
 */
@Remote
public interface TellerSessionRemote {

    String getLoginLoggedUser();
    
}
