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
        <h1>Create election event</h1>
    </div>
    <h:messages styleClass="message"/>
    <h:form>
      <h:inputHidden value="#{createElectionEvent.elecId}" />
      <table class="form">
        <tbody>
          <tr>
            <th>Event name:</th>
            <td><h:inputText style="width: 250px" required="true" requiredMessage="Event name is required" value="#{createElectionEvent.eventName}" /></td>
          </tr>
          <tr>
            <th>Info:</th>
            <td> <h:inputTextarea style="width: 250px" value="#{createElectionEvent.info}" /></td>
          </tr>
          <tr>
            <td class="button" colspan="2">
                <h:commandButton value="Create" action="#{createElectionEvent.createEvent}">
                    <f:param name="elecId" value="#{createElectionEvent.elecId}"/>
                </h:commandButton>
            </td>
          </tr>
        </tbody>
      </table>


    </h:form>
    <br/>
    <h:outputLink styleClass="button" value="mainCommissioner.jsf">
        Go back
    </h:outputLink>
</f:view>
<jsp:include page="../footer.jsp" />

