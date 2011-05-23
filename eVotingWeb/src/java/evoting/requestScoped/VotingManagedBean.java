package evoting.requestScoped;

import DTO.CandidateDTO;
import DTO.VotesDTO;
import DTO.VotingCardDTO;
import com.google.common.collect.Lists;
import evoting.controller.bean.stateless.ElectionSessionRemote;
import evoting.controller.bean.stateless.NominatingSessionRemote;
import evoting.controller.bean.stateless.TellerSessionRemote;
import evoting.controller.bean.stateless.VotingSessionRemote;
import evoting.controller.entity.*;
import java.util.ArrayList;
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
import evoting.validator.bean.stateless.ValidatorSessionRemote;
import evoting.validator.pojo.ValidatorException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "voting")
@RequestScoped
public class VotingManagedBean {

    private static final Logger logger = Logger.getLogger(VotingManagedBean.class.getName());

    private Map<String, Boolean> votes = new HashMap<String, Boolean>();

    public Map<String, Boolean> getVotes() {
        return votes;
    }

    public void setVotes(Map<String, Boolean> votes) {
        this.votes = votes;
    }
    @EJB
    private TellerSessionRemote tellerSessionBean;

    @EJB
    private VotingSessionRemote votingSessionBean;

    @EJB
    private ElectionSessionRemote electionSessionBean;

    @EJB
    private NominatingSessionRemote nominatingSessionBean;
    
    @EJB
    private ValidatorSessionRemote validatorSessionBean;

    private Voter voter = null;

    @ManagedProperty(value = "#{param.eventId}")
    private Integer eventId;

    @ManagedProperty(value = "#{param.voterLogin}")
    private String voterLogin;

    public VotingManagedBean() throws ControllerException {
    }

    public String getVoterLogin() {
        return voterLogin;
    }

    public void setVoterLogin(String voterLogin) {
        this.voterLogin = voterLogin;
    }

    public String doVote() {
        List<String> electedCandidates = new ArrayList<String>();
        Iterator it = votes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Boolean> candidate = (Map.Entry<String, Boolean>) it.next();
            if (candidate.getValue() == true) {
                electedCandidates.add(candidate.getKey());                
            }
        }       
        VotingCardDTO votingCard = new VotingCardDTO((String[]) electedCandidates.toArray(new String[electedCandidates.size()]), voterLogin, eventId);
        try {
           validatorSessionBean.sendVote(votingCard);            
        } catch (ValidatorException ex) {
            logger.log(Level.SEVERE, "Error during sending votes.", ex);
        }
        return null;
    }

    
    public String goVote() {
        setEventId(eventId);        
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
            Logger.getLogger(VotingManagedBean.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VotingManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    public Collection<CandidateDTO> getCandidates(int eventId) {
        try {
            CandidateDTO[] candidates = votingSessionBean.getCandidates(eventId);
            return Arrays.asList(candidates);
        } catch (ControllerException ex) {
            logger.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String deleteVoter() throws ControllerException {
        voter = votingSessionBean.getVoter(voterLogin);
        this.eventId = getEventId();
        try {
            electionSessionBean.deleteVoterFromEvent(voter, eventId);
            FacesMessage m = new FacesMessage("Voter " + voter.getLogin() + " was successfully removed");
            FacesContext.getCurrentInstance().addMessage("", m);
        } catch (ControllerException ex) {
            Logger.getLogger(VotingManagedBean.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VotingManagedBean.class.getName()).log(Level.SEVERE, null, ex);
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

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public List<Voter> getEventVoters() {
        try {
            if (getEventId() != null) {
                return (List<Voter>) electionSessionBean.getEventVoters(getEventId());
            }
        } catch (ControllerException ex) {
            Logger.getLogger(ElectionEventManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Voter> getAllVoters() throws ControllerException {

        return (List<Voter>) votingSessionBean.getAllVoters();
    }

    public boolean getRenderVolit() {
        List si = getSelectItems();
        boolean result = true;
        if (si != null) {
            result = si.isEmpty();
        }
        return result;
    }
}
