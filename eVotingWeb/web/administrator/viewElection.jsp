<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<jsp:include page="../header.jsp" />

<f:view>
    <div class="menu">
        <ul class="buttons">
            <li>
                <h:outputLink value="createElectionPage.jsf">
                    create election
                </h:outputLink>
            </li>
            <li>
                <h:outputLink value="viewElections.jsf">
                    View elections
                </h:outputLink>
            </li>
            <li>
                <h:form>
                    <h:commandLink value="logout" action="#{default.logout}"/>
                </h:form>
            </li>
        </ul>
        <h1>View election details</h1>
    </div>
    <h:messages styleClass="message"/>
    <h2>Information</h2>
    <div class="elinfo">
        <table>
            <tr><th>ID:</th><td><h:outputText value="#{createElection.election.id}"/><td></tr>
            <tr><th>Name:</th><td><h:outputText value="#{createElection.election.name}"/></td></tr>
            <tr><th>Type:</th><td><h:outputText value="#{createElection.election.type}"/></td></tr>
        </table>
    </div>

    <div class="electionCommisioners">
        <h2>Commisioners associated with these elections</h2>
        <h:dataTable styleClass="electionsInfo" cellspacing="0" value="#{createElection.electionCommissioners}" var="item">
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Login"/>
                </f:facet>
                <h:outputText value="#{item.login}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="First name"/>
                </f:facet>
                <h:outputText value="#{item.firstName}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Last name"/>
                </f:facet>
                <h:outputText value="#{item.lastName}"/>
            </h:column>

        </h:dataTable>
        <ul class="buttons">
            <li><h:outputLink value="addCommissioner.jsf">
                    add commissioner
                    <f:param name="electionId" value="#{createElection.electionId}"/>
                </h:outputLink></li>
        </ul>

    </div>
</f:view>
<jsp:include page="../footer.jsp" />