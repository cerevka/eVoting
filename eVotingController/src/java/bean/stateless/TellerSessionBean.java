/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.stateless;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 *
 * @author defiler
 */
@Stateless
public class TellerSessionBean implements TellerSessionRemote {
    @Resource
    private SessionContext ctx;

    public String getLoginLoggedUser() {
        return ctx.getCallerPrincipal().getName();
    }
}
