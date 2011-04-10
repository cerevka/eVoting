
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<jsp:include page="header.jsp" />
  <div class="login"><div>
        <h1>Login</h1>
        <form action="j_security_check">
          <table class="login">
            <tbody>
              <tr>
                <th>Username:</th><td><input type="text" name="j_username"/></td>
              </tr>
              <tr>
                <th>Password:</th><td><input type="password" name="j_password"/></td>
              </tr>
              <tr>
                <td colspan="2">
                  <input type="submit" value="login"/>
                </td>
              </tr>
            </tbody>
          </table>
        </form>
</div></div>

<jsp:include page="footer.jsp"/>