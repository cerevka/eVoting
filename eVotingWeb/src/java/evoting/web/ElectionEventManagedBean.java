package evoting.web;

import evoting.controller.bean.stateless.ElectionSessionRemote;
import evoting.controller.bean.stateless.NominatingSessionRemote;
import evoting.controller.bean.stateless.TellerSessionRemote;
import evoting.controller.bean.stateless.VotingSessionRemote;
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
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "electionEvent")
@RequestScoped
public class ElectionEventManagedBean {

    @EJB
    private ElectionSessionRemote electionSessionBean;

    @EJB
    private TellerSessionRemote tellerSessionBean;

    @EJB
    private NominatingSessionRemote nominatingSessionBean;

    @EJB
    private VotingSessionRemote votingSessionBean;

    @ManagedProperty(value = "#{param.electionId}")
    private Integer electionId;

    @ManagedProperty(value = "#{param.eventId}")
    private Integer eventId;

    private String eventName;
    
    private String voterLogin;

    private String info;

    private String voterName;

    private ElectionEvent electionEvent;

    private List<SelectItem> voterSel;

    private Candidate candidate;

    @ManagedProperty(value = "#{param.candidateLogin}")
    private String candidateLogin;   
    
   

    private String commissionersAgreeTableHeader;

    public ElectionEventManagedBean() {
    }

    @PostConstruct
    public void init() {
        Integer id = getEventId();
        if (id == null) {
            return;
        }
        try {
            electionEvent = electionSessionBean.getElectionEvent(id);
            this.eventName = electionEvent.getName();
            this.info = electionEvent.getInfo();
        } catch (ControllerException ex) {
            Logger.getLogger(ElectionEventManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String createEvent() {
        try {
            electionSessionBean.createElectionEvent(electionId, eventName, info);
            FacesMessage m = new FacesMessage("Event \"" + eventName + "\" was succesfully created");
            FacesContext.getCurrentInstance().addMessage("", m);
        } catch (ControllerException ex) {
            Logger.getLogger(ElectionEventManagedBean.class.getName()).log(Level.SEVERE, "Error during event creation.", ex);
            return "";
        }
        setElectionId(electionId);
        return "goViewEvents";
    }

    public String viewEvent() {
        setEventId(eventId);
        return "goViewEvent";
    }

    public String changeEvent() {
        try {
            ElectionEvent electionEvent = electionSessionBean.getElectionEvent(getEventId());
            electionEvent.setName(eventName);
            electionEvent.setInfo(info);
            electionSessionBean.changeEvent(electionEvent);
            FacesMessage m = new FacesMessage("Event was succesfully changed");
            FacesContext.getCurrentInstance().addMessage("", m);
        } catch (ControllerException ex) {
            Logger.getLogger(ElectionEventManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }

        return "";
    }

    public String addVoter() {
        Collection<Person> personList = getPersonList();
        Person finalPerson = null;
        for (Person p : personList) {
            if (p.getLogin().equals(voterLogin)) {
                finalPerson = p;
                break;
            }
        }
        if (finalPerson == null) {
            return "";
        }
        try {
            electionSessionBean.addVoter(voterLogin, getEventId());
            FacesMessage m = new FacesMessage("Voter " + voterLogin + " was successfully added");
            FacesContext.getCurrentInstance().addMessage("", m);
        } catch (ControllerException ex) {
            Logger.getLogger(ElectionEventManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        return "";
    }

    public Collection<Voter> getEventVoters() {
        try {
            return electionSessionBean.getEventVoters(getEventId());
        } catch (ControllerException ex) {
            Logger.getLogger(ElectionEventManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<SelectItem> getVoterSel() {
        Collection<Person> col = electionSessionBean.getAllPerson();
        voterSel = new ArrayList<SelectItem>();
        for (int i = 0; i < col.size(); i++) {
            voterSel.add(new SelectItem(((Person) col.toArray()[i]).getLogin()));
        }
        return voterSel;
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

    public String getVoterName() {
        return voterName;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }

    private Collection<Person> getPersonList() {
        return electionSessionBean.getAllPerson();
    }

    public Collection<ElectionEvent> getUnfinishedElectionEvents() {
        try {
            return electionSessionBean.getUnfinishedElectionEvents(electionSessionBean.getElection(electionId));
        } catch (ControllerException ex) {
            Logger.getLogger(ElectionEventManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public Collection<ElectionEvent> getEndedEvents() {
        String login = tellerSessionBean.getLoginLoggedUser();
        try {
            return electionSessionBean.getEndedEvents(login);
        } catch (ControllerException ex) {
            Logger.getLogger(ElectionEventManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Collection<Election> getComElection() {
        String login = tellerSessionBean.getLoginLoggedUser();
        try {
            return electionSessionBean.getCommissionerElection(login);
        } catch (ControllerException ex) {
            Logger.getLogger(ElectionEventManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Collection<Commissioner> getAgreedCom() {
        if (isRenderedTableEndNom()) {
            return nominatingSessionBean.getComToEndNominating(getEventId());
        } else if (isRenderedTableStarVot()) {
            return votingSessionBean.getComToStartVoting(getEventId());
        } else if (isRenderedTableEndVot()) {
            return votingSessionBean.getComToEndVoting(getEventId());
        }
        return null;
    }

    public boolean isRenderStartNominating() {
        eventId = getEventId();
        if ((nominatingSessionBean.isStartedNominating(eventId) == false) && (votingSessionBean.isStartedVoting(eventId) == false) && (isAnyoneNominated() == false)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isRenderEndNominating() {
        eventId = getEventId();
        String login = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        if ((nominatingSessionBean.isStartedNominating(eventId) == true) && (votingSessionBean.isStartedVoting(eventId) == false) && (isAnyoneNominated() == true) && !nominatingSessionBean.isComToEndNominating(eventId, login)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isRenderStartVoting() {
        eventId = getEventId();
        String login = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        if ((nominatingSessionBean.isStartedNominating(eventId) == false) && (votingSessionBean.isStartedVoting(eventId) == false) && (isAnyoneNominated() == true) && !votingSessionBean.isComToStartVoting(eventId, login)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isRenderEndVoting() {
        eventId = getEventId();
        String login = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        if ((nominatingSessionBean.isStartedNominating(eventId) == false) && (votingSessionBean.isStartedVoting(eventId) == true) && (isAnyoneNominated() == true) && !votingSessionBean.isComToEndVoting(eventId, login)) {
            return true;
        } else {
            return false;
        }
    }

    public String getCandidateLogin() {
        return candidateLogin;
    }

    public void setCandidateLogin(String candidateLogin) {
        this.candidateLogin = candidateLogin;
    }

    public boolean isRenderedTableEndNom() {
        eventId = getEventId();
        if ((nominatingSessionBean.isStartedNominating(eventId) == true) && (votingSessionBean.isStartedVoting(eventId) == false) && (isAnyoneNominated() == true)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isRenderedTableStarVot() {
        eventId = getEventId();
        if ((nominatingSessionBean.isStartedNominating(eventId) == false) && (votingSessionBean.isStartedVoting(eventId) == false) && (isAnyoneNominated() == true)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isRenderedTableEndVot() {
        eventId = getEventId();
        if ((nominatingSessionBean.isStartedNominating(eventId) == false) && (votingSessionBean.isStartedVoting(eventId) == true) && (isAnyoneNominated() == true)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAnyoneNominated() {
        int count = 0;
        count = getCandidates().size();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String deleteCandidate() {
        this.candidate = nominatingSessionBean.getCandidate(candidateLogin);
        this.eventId = getEventId();
        try {
            nominatingSessionBean.deleteCandidateFromEvent(candidate, eventId);
            FacesMessage m = new FacesMessage("Candidate " + candidate.getLogin() + " was successfully removed");
            FacesContext.getCurrentInstance().addMessage("nic", m);
        } catch (ControllerException ex) {
            Logger.getLogger(VotingManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        return "";

    }

    public Collection<Candidate> getCandidates() {
        return nominatingSessionBean.getCandidates(getEventId());
    }

    public String viewResultEvent() {
        return "goViewResultEvent";
    }

    public void setElectionId(Integer electionId) {
        this.electionId = electionId;
    }

    public Integer getElectionId() {
        return electionId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getVoterLogin() {
        return voterLogin;
    }

    public void setVoterLogin(String voterLogin) {
        this.voterLogin = voterLogin;
    }

    public String getCommissionersAgreeTableHeader() {
        if (isRenderedTableEndNom()) {
            return "Commissioners that agreed with end of nominating";
        } else if (isRenderedTableStarVot()) {
            return "Commissioners that agreed with start of voting";
        } else if (isRenderedTableEndVot()) {
            return "Commissioners that agreed with end of voting";
        }
        return "";
    }

    public void setCommissionersAgreeTableHeader(String commissionersAgreeTableHeader) {
        this.commissionersAgreeTableHeader = commissionersAgreeTableHeader;
    }
}
