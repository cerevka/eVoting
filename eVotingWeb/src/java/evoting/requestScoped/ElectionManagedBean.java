package evoting.requestScoped;

import evoting.controller.bean.stateless.ElectionSessionRemote;
import evoting.controller.entity.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import evoting.controller.pojo.ControllerException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "election")
@RequestScoped
public class ElectionManagedBean {
    
    private static final Logger logger = Logger.getLogger(ElectionManagedBean.class.getName());

    @EJB
    private ElectionSessionRemote electionSessionBean;
    @ManagedProperty(value = "#{param.electionId}")
    private Integer electionId;
    private String name;
    private String currentType = "Internet";
    private String commissioners;
    private List<SelectItem> personSel;
    private String commissionerName;
    private String stringperson;

    public ElectionManagedBean() {
    }

    public String create() {
        try {
            electionSessionBean.createElection(name, currentType);
            FacesMessage m = new FacesMessage("The election " + name + " was successfully created");
            FacesContext.getCurrentInstance().addMessage("", m);
        } catch (ControllerException ex) {
            Logger.getLogger(ElectionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        return "success";
    }

    public String addCommissioner() {
        Collection<Person> personList = getPersonList();
        Person personOut = null;
        for (Person p : personList) {
            if (p.toString().equals(stringperson)) {
                personOut = p;
                break;
            }
        }
        if (personOut == null) {
            return "";
        }
        try {
            electionSessionBean.addCommissioner(personOut, getElectionId());
            FacesMessage m = new FacesMessage("The commissioner " + personOut.getLogin() + " was successfully added");
            FacesContext.getCurrentInstance().addMessage("", m);
        } catch (ControllerException ex) {
            Logger.getLogger(ElectionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        return "";
    }

    public Collection<Commissioner> getElectionCommissioners() {
        try {
            return electionSessionBean.getElectionCommissioners(getElectionId());
        } catch (ControllerException ex) {
            Logger.getLogger(ElectionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }  

    public List<SelectItem> getPersonSel() {
        Collection<Person> col = getPersonList();
        personSel = new ArrayList<SelectItem>();
        for (Person p : col) {
            personSel.add(new SelectItem(p.toString()));
        }
        return personSel;
    }
    
    public String viewElection() {
        setElectionId(electionId);
        return "goViewEvents";       
    }

    public void setPersonSel(List<SelectItem> personSel) {
        this.personSel = personSel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentType() {
        return currentType;
    }

    public void setCurrentType(String currentType) {
        this.currentType = currentType;
    }

    public String getCommissioners() {
        return commissioners;
    }

    public void setCommissioners(String commissioners) {
        this.commissioners = commissioners;
    }

    public Collection<Person> getPersonList() {
        return electionSessionBean.getAllPerson();
    }
    
   public String removeCommissioner(String commissionerLogin) {
       Commissioner commissioner = electionSessionBean.getCommissioner(commissionerLogin);
       this.electionId = getElectionId();
       try {
           electionSessionBean.deleteCommissionerFromEvent(commissioner, electionId);
           FacesMessage msg = new FacesMessage("Commissioner " + commissionerLogin + " was successfully removed.");
           FacesContext.getCurrentInstance().addMessage(null, msg);           
       } catch (ControllerException ex) {
           logger.log(Level.SEVERE, null, ex);
           return "";
       }
       return "";
   }

    public Collection<Election> getElections() {
        return electionSessionBean.getAllElection();
    }

    public Election getElection() {
        try {
            return electionSessionBean.getElection(getElectionId());
        } catch (ControllerException ex) {
            Logger.getLogger(ElectionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Integer getElectionId() {
        if (electionId != null) {
            return electionId;
        }
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) ctx.getSession(true);
        return (Integer) session.getAttribute("electionId");
    }

    public void setElectionId(Integer electionId) {
        if (electionId == null) {
            return;
        }
        this.electionId = electionId;
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) context.getSession(true);
        session.setAttribute("electionId", electionId);
    }

    public String getCommissionerName() {
        return commissionerName;
    }

    public void setCommissionerName(String commissionerName) {
        this.commissionerName = commissionerName;
    }

    public String getStringperson() {
        return stringperson;
    }

    public void setStringperson(String stringperson) {
        this.stringperson = stringperson;
    }
    
    public String goViewElection() {
        setElectionId(electionId);
        return "goViewElection";
    }
}
