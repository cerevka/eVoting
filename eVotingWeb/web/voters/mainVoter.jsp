<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<jsp:include page="../header.jsp" />
<f:view>
    <div class="menu">
        <ul class="buttons">
            <li>
                <h:outputLink value="nominating.jsf" disabled="#{nominating.renderNominovat}">
                    Nominovat
                </h:outputLink>
            </li>
            <li>
                <h:outputLink  value="voting.jsf"  disabled="#{voting.renderVolit}">
                    Hlasovat
                </h:outputLink>
            </li>
            <li>
                <h:outputLink  value="endedEvents.jsf">
                    Výsledky
                </h:outputLink>
            </li>
            <li>
                <h:form>
                    <h:commandLink value="Odhlásit" action="#{default.logout}"/>
                </h:form>
            </li>
        </ul>
        <h1>Hlavní strana</h1>
    </div>
    <h:messages styleClass="message" />

    <p>Vítejte. Vyberte akci z menu nahoře.</p>




</f:view>
    <jsp:include page="../footer.jsp" />