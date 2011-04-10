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
        <h1>Election event</h1>
    </div>
    <h:messages styleClass="message" />

    <div>
        <table class="electionsInfo">
                <tr>
                    <th>Event name:</th>
                    <td><h:outputText value="#{createElectionEvent.eventName}" /></td>
                </tr>
                <tr>
                    <th>Event info:</th>
                    <td><h:outputText value="#{createElectionEvent.info}" /></td>
                </tr>
        </table>
    </div>
    <br/>
    <div>
        <h:form>
            <h:dataTable style="float:left" styleClass="elections" cellspacing="0" value="#{voting.allVotersModel}" var="item">
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Voters"/>
                    </f:facet>
                    <h:outputText value="#{item.login}"/>
                </h:column>
                <h:column>
                    <h:commandLink styleClass="link" value="Remove" action="#{voting.deleteVoter}"  >

                    </h:commandLink>
                </h:column>
            </h:dataTable>
        </h:form>
        <h:form>
            <h:dataTable style="float: left" styleClass="elections" cellspacing="0" value="#{createElectionEvent.candidatesModel}" var="item">
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Candidates"/>
                    </f:facet>
                    <h:outputText value="#{item.login}"/>
                </h:column>
                <h:column>
                    <h:outputText value="#{item.candidateRole}" />
                </h:column>
                <h:column>
                    <h:commandLink styleClass="link" value="Remove" action="#{createElectionEvent.deleteCandidate}" />
                </h:column>

            </h:dataTable>
        </h:form>

        <h:form rendered="#{!createElectionEvent.renderStartNominating}">
            <h:dataTable styleClass="elections" cellspacing="0" value="#{createElectionEvent.agreedCom}" var="item">
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="#{createElectionEvent.commissionersAgreeTableHeader}"/>
                    </f:facet>
                    <h:outputText value="#{item.login}" />
                </h:column>
            </h:dataTable>
        </h:form>

    </div>
    <div style="clear: both; padding-top: 10px">
        <ul class="buttons">
            <h:form>
                <li>
                    <h:commandLink value="Start nominating" action="#{nominating.startNominating}" rendered="#{createElectionEvent.renderStartNominating}" />
                </li>
                <li>
                    <h:commandLink value="End nominating" action="#{nominating.endNominating}" rendered="#{createElectionEvent.renderEndNominating}" />
                </li>
                <li>
                    <h:commandLink value="Start voting" action="#{voting.startVoting}" rendered="#{createElectionEvent.renderStartVoting}" />
                </li>
                <li>
                    <h:commandLink value="End voting" action="#{voting.endVoting}" rendered="#{createElectionEvent.renderEndVoting}" />
                </li>
            </h:form>
            <li>
                <h:outputLink styleClass="button" value="addVoter.jsf">
                    Add voter
                </h:outputLink>
            </li>
        </ul>
    </div>
</f:view>
<jsp:include page="../footer.jsp" />
