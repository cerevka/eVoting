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
        <h1>Main page</h1>
    </div>
    <h:messages />
    <p>Hello commissioner, choose from the menu above.</p>

</f:view>
<jsp:include page="../footer.jsp" />

