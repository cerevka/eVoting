package evoting.requestScoped;

import evoting.controller.bean.stateless.ResultsSessionRemote;
import evoting.controller.bean.stateless.NominatingSessionRemote;
import evoting.controller.bean.stateless.TellerSessionRemote;
import evoting.controller.entity.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;
import evoting.controller.pojo.ControllerException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "results")
@RequestScoped
public class ResultsManagedBean {

    @EJB
    private ResultsSessionRemote resultsBean;
    @EJB
    private TellerSessionRemote tellerSessionBean;
    @EJB
    private NominatingSessionRemote nominatingSessionBean;
    private String eventName;
    private Integer eventId;
    private DataModel eeResultModel;

    public ResultsManagedBean() {
    }

    public Collection<ElectionEvent> getEndedEvents() {
        String login = tellerSessionBean.getLoginLoggedUser();
        try {
            return resultsBean.getEndedEvents(login);
        } catch (ControllerException ex) {
            Logger.getLogger(ResultsManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Collection<ElectionResult> getElectionEventResults() {
        try {
            System.out.println("TRY");
            Collection<ElectionResult> results = resultsBean.getElectionEventResults(getEventId());
            Collection<Candidate> candidates = nominatingSessionBean.getCandidates(getEventId());

            if (results.isEmpty()) {
                resultsBean.generateResult(getEventId());
                results = resultsBean.getElectionEventResults(getEventId());
                if (results.isEmpty() && candidates.isEmpty()) {
                    Candidate announce = new Candidate();
                    announce.setLogin("Event had no candidates");
                    ElectionResult ann = new ElectionResult();
                    ann.setCandidate(announce);
                    ann.setVotes(0);
                    ann.setElected(3);
                    results.add(ann);
                    return results;
                }
            }

            /*  if (results.size()==1 ) {
            System.out.println("Vyplul som results lebo ==1");
            List<ElectionResult> r = new ArrayList<ElectionResult>();
            r.addAll(results);
            return results;
            }*/

            //CHECK MISSING PEOPLE
            List<Candidate> candidatesL = new ArrayList<Candidate>();
            candidatesL.addAll(candidates);
            System.out.println("VELKOST " + results.size());
            List<ElectionResult> resList = new ArrayList<ElectionResult>();
            List<ElectionResult> outList = new ArrayList<ElectionResult>();
            System.out.println("VELKOST " + resList.size());
            resList.addAll(results);
            List<Candidate> hadVotes = new ArrayList<Candidate>();
            for (int i = 0; i < resList.size(); i++) {
                hadVotes.add(resList.get(i).getCandidate());
            }
            for (int i = 0; i < candidatesL.size(); i++) {

                if (!hadVotes.contains(candidatesL.get(i))) {
                    Candidate looser = new Candidate();
                    looser.setLogin(candidatesL.get(i).getLogin());
                    ElectionResult er = new ElectionResult();
                    er.setCandidate(looser);
                    er.setVotes(0);
                    er.setElected(3);
                    results.add(er);

                } else {
                }

            }
            resList.clear();
            resList.addAll(results);
            //END OF CHECK
            try {
                //   resList.addAll(results);
                System.out.println("VELKOST " + resList.size());
                int max;
                int pos = 0;
                if (resList.size() > 1) {
                    while (!resList.isEmpty()) {
                        pos = 0;
                        max = resList.get(0).getVotes();

                        for (int i = 0; i < resList.size(); i++) {
                            if (max < resList.get(i).getVotes()) {
                                max = resList.get(i).getVotes();
                                pos = i;

                            }
                        }
                        outList.add(resList.get(pos));
                        resList.remove(pos);
                    }
                } else {
                    System.out.println("VYPLUL SOM POVODNE " + results.size());
                    //  System.out.println(resList.get(1).getVotes());

                    return results;
                }


            } catch (Exception ex) {
                System.out.println(ex);
                System.out.println("EXCEPTED2");
                return results;
            }
            results.clear();
            results.addAll(outList);
            System.out.println("CLEARED");
            return results;
        } catch (ControllerException ex) {
            System.out.println("Vyplul som null");
            Logger.getLogger(ResultsManagedBean.class.getName()).log(Level.SEVERE, null, ex);
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

    public String getEventName() {
        return "fsafsa";
    }

    public void setEventName(String name) {
        eventName = name;
    }

    public DataModel getEeResultModel() {
        eeResultModel = new ListDataModel((List) getElectionEventResults());
        return eeResultModel;
    }

    public String getVotingResultLabel() {
        ElectionResult er = (ElectionResult) eeResultModel.getRowData();
        Integer i = er.getElected();
        switch (i) {
            case 1:
                return "Zvolen";
            case 2:
                return "Nedostatek místa";
            case 3:
                return "Nesplňuje podmínky";
            default:
                return "Chyba";
        }
    }
}
