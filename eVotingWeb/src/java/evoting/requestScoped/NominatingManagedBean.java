package evoting.requestScoped;

import evoting.controller.bean.stateless.NominatingSessionRemote;
import evoting.controller.bean.stateless.TellerSessionRemote;
import evoting.controller.entity.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import evoting.controller.pojo.ControllerException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "nominating")
@RequestScoped
public class NominatingManagedBean {

    @EJB
    private TellerSessionRemote tellerSessionBean;
    @EJB
    private NominatingSessionRemote nominatingSessionBean;
    @ManagedProperty(value = "#{param.eventId}")
    private Integer eventId;
    private String programme;

    public NominatingManagedBean() {
    }

    public String goNominate() {
        return "goNominate";
    }

    public String nominate() {
        setEventId(eventId);
        String login = tellerSessionBean.getLoginLoggedUser();
        try {
            nominatingSessionBean.nominate(login, getEventId(), programme);
            FacesMessage m = new FacesMessage("Byl jste úspěšně nominován");
            FacesContext.getCurrentInstance().addMessage("", m);
        } catch (ControllerException ex) {
            Logger.getLogger(NominatingManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        return "";
    }

    public String startNominating() {
        nominatingSessionBean.startNominating(getEventId());
        FacesMessage m = new FacesMessage("Nominating started");
        FacesContext.getCurrentInstance().addMessage("", m);
        return "";
    }

    public String endNominating() {
        nominatingSessionBean.supportEndNominating(getEventId(), FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
        if (nominatingSessionBean.isMajority(getEventId(), "END_NOMINATING")) {
            nominatingSessionBean.endNominating(getEventId());
            FacesMessage m = new FacesMessage("Nominating ended");
            FacesContext.getCurrentInstance().addMessage("", m);
            return "";
        }
        FacesMessage m = new FacesMessage("You agreed with end of nominating.");
        FacesContext.getCurrentInstance().addMessage("", m);
        return "";
    }

    public List<SelectItem> getSelectItems() {
        try {
            String login = tellerSessionBean.getLoginLoggedUser();
            Collection<ElectionEvent> electionEvents = nominatingSessionBean.getVoterElectionEvents(login);
            List<SelectItem> items = new ArrayList<SelectItem>();
            Election electionForEvent;
            String electionsAndEvent;
            for (ElectionEvent event : electionEvents) {
                electionForEvent = nominatingSessionBean.getElectionFromEvent(event.getId());
                electionsAndEvent = electionForEvent.getName() + " - " + event.getName();
                //value=id, rendered=elections+event name
                items.add(new SelectItem(event.getId(), electionsAndEvent));
            }
            return items;
        } catch (ControllerException ex) {
            Logger.getLogger(NominatingManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Integer getEventId() {
        if (eventId != null) {
            return eventId;
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) context.getSession(true);
        return (Integer) session.getAttribute("eventId");
    }

    public void setEventId(Integer eventId) {
        if (eventId == null) {
            return;
        }
        this.eventId = eventId;
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) context.getSession(true);
        session.setAttribute("eventId", eventId);
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public boolean getRenderNominovat() {
        List si = getSelectItems();
        boolean result = true;
        if (si != null) {
            result = si.isEmpty();
        }
        return result;
    }
}
