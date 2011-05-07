package evoting.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name="default")
@RequestScoped
public class DefaultManagedBean {

    private String role;

    /** Creates a new instance of DefaultManagedBean */
    public DefaultManagedBean() {
    }

    public String logout() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        session.invalidate();
        return "logout";
    }

    public String getRole() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        if (ec.isUserInRole("voters")) {
            role = "voters";
        }
        if (ec.isUserInRole("commissioners")) {
            role = "commissioners";
        }
        if (ec.isUserInRole("administrators")) {
            role = "administrators";
        } else {
            role = "nobody";
        }

        return role;
    }

    public boolean isVoter() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        if (ec.isUserInRole("voters")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAdministrator() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        if (ec.isUserInRole("administrators")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCommissioner() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        if (ec.isUserInRole("commissioners")) {
            return true;
        } else {
            return false;
        }
    }

    public String getUsersName() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        return ec.getRemoteUser();

    }
}
