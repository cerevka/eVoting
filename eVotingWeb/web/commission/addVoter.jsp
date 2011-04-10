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
        <h1>Add voter</h1>
    </div>
    <h:messages styleClass="message"/>
    <h:form>
        <table>
            <tr>
                <td>Select voter:</td>
                <td>
                    <h:selectOneMenu id="selectCom" value="#{createElectionEvent.voterLogin}" >
                        <f:selectItems value="#{createElectionEvent.voterSel}" />
                    </h:selectOneMenu>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: right">
                    <h:commandButton  value="Add voter" action="#{createElectionEvent.addVoter}"/>
                </td>
            </tr>
        </table>
    </h:form>
    <ul class="buttons">
        <li>
            <h:outputLink styleClass="buttons" value="viewEvent.jsf">
                Go back
                <f:param name="evenId" value="#{createElectionEvent.eventId}"/>
            </h:outputLink>
        </li>
    </ul>
</f:view>

<jsp:include page="../footer.jsp" />
