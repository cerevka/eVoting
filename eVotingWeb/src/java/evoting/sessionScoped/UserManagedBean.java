package evoting.sessionScoped;

import java.io.Serializable;
import java.security.Principal;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tomáš Čerevka
 */
@ManagedBean(name = "user")
@SessionScoped
public class UserManagedBean implements Serializable {

    private String login;

    private String password;

    public UserManagedBean() {
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public String doLogin() {        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            request.login(this.login, this.password);
            this.login = this.password = null;
            return "success";
        } catch (ServletException ex) {
            this.login = this.password = null;
            return "fail";
        }
    }

    public String doLogout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        return "logout";
    }
    
    public boolean isLogged() {        
        return getRole() != null;
    }

    public String getRole() {
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        if (principal == null) {
            return null;
        }
        return principal.getName();
    }

    public boolean isVoter() {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        if (ectx.isUserInRole("voters")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAdministrator() {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        if (ectx.isUserInRole("administrators")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCommissioner() {
        ExternalContext ecxt = FacesContext.getCurrentInstance().getExternalContext();
        if (ecxt.isUserInRole("commissioners")) {
            return true;
        } else {
            return false;
        }
    }

    public String getUserName() {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        return ectx.getRemoteUser();

    }
}
