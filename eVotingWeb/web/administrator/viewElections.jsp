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
        <h1>View election</h1>
    </div>

        <h:messages styleClass="message"/>

        <h2>List of available elections</h2>

    <h:dataTable cellspacing="1" styleClass="elections" value="#{createElection.elections}" var="item">
        <h:column>
            <f:facet name="header">
                <h:outputText value="id"/>
            </f:facet>
            <h:outputText value="#{item.id}"/>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:outputText value="Name"/>
            </f:facet>
            <h:outputLink styleClass="link"  value="viewElection.jsf">
                <h:outputText value="#{item.name}"/>
                <f:param name="electionId" value="#{item.id}"/>
            </h:outputLink>
                       <f:attribute name="align" value="left" />
        </h:column>
    </h:dataTable>


</f:view>




<jsp:include page="../footer.jsp" />
