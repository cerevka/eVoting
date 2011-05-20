package evoting.sessionScoped;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Tomáš Čerevka
 */
@ManagedBean(name = "user")
@SessionScoped
public class UserManagedBean {

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
}
