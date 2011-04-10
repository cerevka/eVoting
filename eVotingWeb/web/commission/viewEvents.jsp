<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>


<jsp:include page="../header.jsp" />
<f:view>
    <div class="menu">
        <ul class="buttons">
            <li>
                <h:outputLink value="commissionerElection.jsf">
                    View election
                </h:outputLink>
            </li>
            <li>
                <h:form>
                    <h:commandLink value="logout" action="#{default.logout}"/>
                </h:form>
            </li>
        </ul>
        <h1>Election events</h1>
    </div>
    <h:messages styleClass="message"/>

    <h:dataTable styleClass="electionsInfo" cellspacing="1" value="#{createElectionEvent.unfinishedElectionEventsModel}" var="item">
        <h:column>
            <f:facet name="header">
                <h:outputText value="Name"/>
            </f:facet>
            <h:form>
                <h:commandLink styleClass="link"  action="#{createElectionEvent.viewEvent}">
                    <h:outputText value="#{item.name}"/>
                    <f:param name="eventId" value="#{item.id}"/>
                    <f:param name="elecId" value="#{createElectionEvent.elecId}"/>
                </h:commandLink>
            </h:form>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:outputText value="Nominating"/>
            </f:facet>
            <h:outputText value="#{item.nominatingStarted}"/>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:outputText value="Voting"/>
            </f:facet>
            <h:outputText  value="#{item.votingStarted}"/>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:outputText value="Messages"/>
            </f:facet>
            <h:outputText value="#{createElectionEvent.alertText}" styleClass="alertRow" rendered="#{createElectionEvent.renderAlert}"/>
        </h:column>
    </h:dataTable>
    <ul class="buttons">
        <li>
            <h:outputLink  value="createNewEvent.jsf">
                Create new event
                <f:param name="elecId" value="#{createElectionEvent.elecId}"/>
            </h:outputLink>
        </li>
    </ul>

</f:view>
<jsp:include page="../footer.jsp" />

