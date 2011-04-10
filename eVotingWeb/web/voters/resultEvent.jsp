<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
        <h1>Výsledek</h1>
    </div>
    <h:messages styleClass="message" />
    
    <h:dataTable styleClass="elections" cellspacing="1" value="#{generatingResults.eeResultModel}" var="item">
        <h:column>
            <f:facet name="header">
                <h:outputText value="Kandidát"/>
            </f:facet>
            <h:outputText value="#{item.candidate}"/>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:outputText value="Počet hlasů"/>
            </f:facet>
            <h:outputText value="#{item.votes}"/>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:outputText value="Výsledek" />
            </f:facet>
            <h:outputText value="#{generatingResults.votingResultLabel}"/>
        </h:column>
    </h:dataTable>

</f:view>


<jsp:include page="../footer.jsp" />