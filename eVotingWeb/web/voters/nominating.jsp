<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>


<jsp:include page="../header.jsp" />
<f:view>
    <div class="menu">
        <ul class="buttons">
            <li>
            <li>
                <h:outputLink value="nominating.jsf" disabled="#{nominating.renderNominovat}">
                    Nominovat
                </h:outputLink>
            </li>
            <li>
                <h:outputLink  value="voting.jsf"  disabled="#{voting.renderVolit}">
                    Hlasovat
                </h:outputLink>
            </li>
            <li>
                <h:outputLink  value="endedEvents.jsf">
                    Výsledky
                </h:outputLink>
            </li>
            <li>
                <h:form>
                    <h:commandLink value="Odhlásit" action="#{default.logout}"/>
                </h:form>
            </li>
        </ul>
        <h1>Nominování</h1>
    </div>
    <h:messages styleClass="message" />

    <h2>Nominovat se do volební události</h2>
    <h:form>
        <table class="form" cellspacing="1">
            <tbody>
                <tr>
                    <th>Volební událost:</th>
                    <td>
                        <h:selectOneMenu value="#{nominating.eventId}">
                            <f:selectItems value="#{nominating.selectItems}"/>
                        </h:selectOneMenu>
                    </td>
                </tr>
                <tr>
                    <th>Program:</th>
                    <td><h:inputTextarea value="#{nominating.programme}" /></td>
                </tr>
                <tr>
                    <td colspan="2" class="button">
                        <h:commandButton value="Nominovat" action="#{nominating.nominate}" />
                    </td>
                </tr>
            </tbody>
        </table>
    </h:form>
</f:view>
<jsp:include page="../footer.jsp" />

