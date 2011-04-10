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
        <h1>Elections</h1>
    </div>
    <h:messages />
    <h:dataTable styleClass="electionsInfo" cellspacing="0" value="#{createElectionEvent.comElection}" var="item">
        <h:column>
            <h:outputLink styleClass="link" value="viewEvents.jsf">
                <h:outputText value="#{item.name}"/>
                <f:param name="elecId" value="#{item.id}"/>
            </h:outputLink>
        </h:column>
    </h:dataTable>
</f:view>

<jsp:include page="../footer.jsp" />