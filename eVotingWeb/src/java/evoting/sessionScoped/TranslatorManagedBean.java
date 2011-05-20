package evoting.sessionScoped;

import java.io.Serializable;
import java.util.Locale;
import java.util.logging.Logger;
import javax.annotation.security.PermitAll;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Control language of user interface.
 * @author Tomáš Čerevka
 */
@ManagedBean(name = "translator")
@SessionScoped
public class TranslatorManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    private static final Logger logger = Logger.getLogger(TranslatorManagedBean.class.getName());

    /**
     * Construct TranslatorManagedBean.
     */
    public TranslatorManagedBean() {
    }

    /**
     * Return current locale.
     * @return Current locale.
     */
    public Locale getLocale() {
        return this.locale;
    }

    /**
     * Return current language.
     * @return Current language.
     */
    public String getLanguage() {
        return this.locale.getLanguage();
    }

    /**
     * Change language.
     * @param language New language.
     */
    @PermitAll
    public void doChangeLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
