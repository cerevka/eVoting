<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<jsp:include page="header.jsp" />
<f:view>
    <h:outputText rendered="#{default.commissioner}"><h1>E-volby welcome page</h1></h:outputText>
    <h:outputText rendered="#{default.voter}"><h1>E-volby úvodní stránka</h1></h:outputText>
    <br />
    <h:outputText rendered="#{default.administrator}">Welcome </h:outputText>
    <h:outputText rendered="#{default.commissioner}">Welcome </h:outputText>
    <h:outputText rendered="#{default.voter}">Vítejte </h:outputText>
    <h:outputText value="#{default.usersName}" />
    <h:form>
    <h:commandLink value="Klikněte zde pro pokračování na výběr voličských akcí" action="voters" rendered="#{default.voter}" />
    <h:commandLink value="Click here to continue, commissioner" action="commissioners" rendered="#{default.commissioner}" />
    <h:commandLink value="Click here to continue, administrator" action="administrators" rendered="#{default.administrator}" />
    </h:form>
</f:view>

<jsp:include page="footer.jsp"/>


