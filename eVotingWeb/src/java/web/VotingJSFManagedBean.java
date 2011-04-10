/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.CreatingElectionSessionRemote;
import ejb.NominatingSessionRemote;
import ejb.TellerSessionRemote;
import ejb.VotingSessionRemote;
import entity.*;
import entity.Voter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import pojos.ControllerException;

/**
 *
 * @author defiler
 */
public class VotingJSFManagedBean {

    private ListDataModel AllVoterModel;
    private TellerSessionRemote tellerSessionBean;
    private VotingSessionRemote votingSessionBean;
    private CreatingElectionSessionRemote creatingElectionSessionBean;
    private NominatingSessionRemote nominatingSessionBean;
    private Voter voter = null;
    private Integer eventId = null;

    public VotingJSFManagedBean() throws ControllerException {
        Context context;
        try {
            context = new InitialContext();
            tellerSessionBean = (TellerSessionRemote) context.lookup("ejb.TellerSessionRemote");
            votingSessionBean = (VotingSessionRemote) context.lookup("ejb.VotingSessionRemote");
            creatingElectionSessionBean = (CreatingElectionSessionRemote) context.lookup("ejb.CreatingElectionSessionRemote");
            nominatingSessionBean = (NominatingSessionRemote) context.lookup("ejb.NominatingSessionRemote");
            // getAllVotersModel();
        } catch (NamingException ex) {
            Logger.getLogger(CreateElectionJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PostConstruct
    public void validate() {
        //TODO kontrola jestli komisarovi patri electionID a eventId
    }

    public String goVote() {
        setEventId(eventId);
        System.out.println("nastavuj EventId na "+getEventId());
        return "goVote";
    }

    public String startVoting() {
        try {
            votingSessionBean.supportStartVoting(getEventId(), FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
            if (nominatingSessionBean.isMajority(getEventId(), "START_VOTING")) {
                votingSessionBean.startVoting(getEventId());
                FacesMessage m = new FacesMessage("Voting started");
                FacesContext.getCurrentInstance().addMessage("", m);
                return "";
            }
            FacesMessage m = new FacesMessage("You agreed with start of voting");
            FacesContext.getCurrentInstance().addMessage("", m);
            return "";
        } catch (ControllerException ex) {
            Logger.getLogger(VotingJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    public String endVoting() {
        try {
            votingSessionBean.supportEndVoting(getEventId(), FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
            if (nominatingSessionBean.isMajority(getEventId(), "END_VOTING")) {
                votingSessionBean.endVoting(getEventId());
                FacesMessage m = new FacesMessage("Voting ended");
                FacesContext.getCurrentInstance().addMessage("", m);
                return "goMain";
            }
            FacesMessage m = new FacesMessage("You agreed with end of voting");
            FacesContext.getCurrentInstance().addMessage("", m);
            return "";
        } catch (ControllerException ex) {
            Logger.getLogger(VotingJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    public String deleteVoter() throws ControllerException {
        voter = (Voter) AllVoterModel.getRowData();
        this.eventId = getEventId();
        try {
            creatingElectionSessionBean.deleteVoterFromEvent(voter, eventId);
            FacesMessage m = new FacesMessage("Voter " + voter.getLogin() + " was successfully removed");
            FacesContext.getCurrentInstance().addMessage("", m);
        } catch (ControllerException ex) {
            Logger.getLogger(VotingJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        return "";
    }

    public List<SelectItem> getSelectItems() {
        try {
            String login = tellerSessionBean.getLoginLoggedUser();
            List<ElectionEvent> electionEvents = votingSessionBean.getVoterElectionEvents(login);
            List<SelectItem> items = new ArrayList<SelectItem>();
            Election electionForEvent;
            String electionsAndEvent;
            for (ElectionEvent event : electionEvents) {
                electionForEvent = nominatingSessionBean.getElectionFromEvent(event.getId());
                electionsAndEvent = electionForEvent.getName() + " - " + event.getName();
                items.add(new SelectItem(event.getId(), electionsAndEvent));
            }
            return items;
        } catch (ControllerException ex) {
            Logger.getLogger(VotingJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getToken() {
        return tellerSessionBean.getLoginLoggedUser();
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

    public DataModel getAllVotersModel() throws ControllerException {
        AllVoterModel = new ListDataModel(getEventVoters());
        return AllVoterModel;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public List<Voter> getEventVoters() {
        try {
            return (List<Voter>) creatingElectionSessionBean.getEventVoters(getEventId());
        } catch (ControllerException ex) {
            Logger.getLogger(CreateElectionEventJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Voter> getAllVoters() throws ControllerException {

        return (List<Voter>) votingSessionBean.getAllVoters();
    }

    public boolean getRenderVolit(){
        List si = getSelectItems();
        return si.isEmpty();
    }
}
