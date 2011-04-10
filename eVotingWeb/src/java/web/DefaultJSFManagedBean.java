/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author defiler
 */
public class DefaultJSFManagedBean {

    private String role;

    /** Creates a new instance of DefaultJSFManagedBean */
    public DefaultJSFManagedBean() {
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
