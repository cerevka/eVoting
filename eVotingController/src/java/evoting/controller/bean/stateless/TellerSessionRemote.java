package evoting.controller.bean.stateless;

import javax.ejb.Remote;

@Remote
public interface TellerSessionRemote {

    String getLoginLoggedUser();
    
}
