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
        <h1>Create election</h1>
    </div>

    <h:messages styleClass="message" />

    <h:form>
        <table class="form">
            <tbody>
                <tr>
                    <th>Election name:</th>
                    <td><h:inputText id="name" requiredMessage="Election name is required!" value="#{createElection.name}"
                                 required="true" label="Name"/></td>
                </tr>
                <tr>
                    <th>Type:</th>
                    <td>
                        <h:selectOneRadio id="type" value="#{createElection.currentType}">
                            <f:selectItem itemLabel="Internet" itemValue="Internet"  />
                            <f:selectItem itemLabel="Local" itemValue="Local" />
                        </h:selectOneRadio>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="button">
                        <h:commandButton value="Create" action="#{createElection.create}"/>
                    </td>
                </tr>
            </tbody>
        </table>
    </h:form>
</f:view>

<jsp:include page="../footer.jsp" />