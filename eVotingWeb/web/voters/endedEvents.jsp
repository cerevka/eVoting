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
        <h1>Výsledky</h1>
    </div>
    <h:messages styleClass="message" />
    <h:dataTable styleClass="electionsInfo" cellspacing="1" value="#{createElectionEvent.endedEvents}" var="item">
        <%--
        <h:column>
            <f:facet name="header">
                <h:outputText value="Id"/>
            </f:facet>
            <h:outputText value="#{item.id}"/>
        </h:column>
        --%>
        <h:column>
            <f:facet name="header">
                <h:outputText value="Událost"/>
            </f:facet>
            <h:outputText value="#{item.name}"/>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:outputText value="Výsledek"/>
            </f:facet>
            <h:form>
                <h:commandLink value="výsledek" action="#{createElectionEvent.viewResultEvent}">
                    <f:param name="eventId" value="#{item.id}"/>
                </h:commandLink>
            </h:form>
        </h:column>
    </h:dataTable>
</f:view>
<jsp:include page="../footer.jsp" />