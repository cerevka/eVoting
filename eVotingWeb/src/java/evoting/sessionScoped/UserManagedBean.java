package evoting.sessionScoped;

import java.io.Serializable;
import java.security.Principal;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * User manager.
 * @author Tomáš Čerevka
 */
@ManagedBean(name = "user")
@SessionScoped
public class UserManagedBean implements Serializable {

    private static final Logger logger = Logger.getLogger(UserManagedBean.class.getName());
    
       
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

    /**
     * Login user.
     * @return Target page.
     */
    public String doLogin() {
        FacesContext ctx = FacesContext.getCurrentInstance();               
        HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
        try {
            request.login(this.login, this.password);
            this.login = this.password = null;
            return "success";
        } catch (ServletException ex) {
            this.login = this.password = null;
            ResourceBundle bundle = ctx.getApplication().getResourceBundle(ctx, "msg");
            ctx.addMessage("loginForm:submitLogin", new FacesMessage(bundle.getString("message.error.login")));             
            return null;
        }
    }

    /**
     * Logout user.
     * @return Target page.
     */
    public String doLogout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();        
        return "logout";
    }
    
    /**
     * Decide if user is logged.
     * @return True for logged user, otherwise false.
     */
    public boolean isLogged() {        
        return getRole() != null;
    }

    /**
     * Return role of user.
     * @return Role of user. For unlogged user return null.
     */
    public String getRole() {
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        if (principal == null) {
            return null;
        }
        return principal.getName();
    }

    /**
     * Decide if user is voter.
     * @return True for voter, otherwise false.
     */
    public boolean isVoter() {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        if (ectx.isUserInRole("voters")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Decide if user is administrator.
     * @return True for administrator, otherwise false.
     */
    public boolean isAdministrator() {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        if (ectx.isUserInRole("administrators")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Decide if user is commissioner.
     * @return True for commissioner, otherwise false.
     */
    public boolean isCommissioner() {
        ExternalContext ecxt = FacesContext.getCurrentInstance().getExternalContext();
        if (ecxt.isUserInRole("commissioners")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return login of user.
     * @return 
     */
    public String getUserName() {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        return ectx.getRemoteUser();

    }
}
