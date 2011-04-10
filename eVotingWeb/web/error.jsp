<jsp:include page="header.jsp" />
<f:view>
    <div class="login"><div>
            <h1>Chybné přihlášení</h1>
             <p>Neplatná kombinace jména a hesla.</p>

             <bt /><bt />
            
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
</f:view>
<jsp:include page="footer.jsp" />
