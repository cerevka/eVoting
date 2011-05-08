package evoting.controller.bean.stateless;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
public class TellerSessionBean implements TellerSessionRemote {
    @Resource
    private SessionContext ctx;

    @Override
    public String getLoginLoggedUser() {
        return ctx.getCallerPrincipal().getName();
    }
}
