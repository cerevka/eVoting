package jms;

import DTO.VotesDTO;
import ejb.ValidatedVotesSessionLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import pojos.CounterException;

/**
 * Obsluha {@code jms/validatedVotes} fronty, do ktere posila komponenta Validator
 * zasifrovany seznam vsech hlasu dane volebni udalosti ({@code electionEventID}).
 * {@link ObjectMessage} je sifrovan verejnym klicem voleb, do ktereho spada dana volebni udalost.
 * @author Martin Zahradnicky
 */
@MessageDriven(mappedName = "jms/validatedVotes", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class VotesMessageBean implements MessageListener {

    @EJB
    private ValidatedVotesSessionLocal validatedVotesSessionBean;
    
    public VotesMessageBean() {
    }

    /**
     * Obsluzna metoda pro zpracovani zprav prichozich do {@code jms/validatedVotes} fronty.
     * Zpravou v JMS je {@link VotesDTO}. Ten se rozsifruje PK volebni udalosti, transformuje se
     * do entity {@link ElectionEventResult} a ulozi do {@code counterdb} databaze.
     * @param message Prichozi zprava do {@code jms/validatedVotes} fronty.
     */
    public void onMessage(Message message) {
        ObjectMessage om = (ObjectMessage) message;
        VotesDTO votesDTO = new VotesDTO();
        try {
            votesDTO = (VotesDTO) om.getObject();
        } catch (Exception ex) {
            Logger.getLogger(VotesMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            validatedVotesSessionBean.saveValidatedVotes(votesDTO);
        } catch (CounterException ex) {
            Logger.getLogger(VotesMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
