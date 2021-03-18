<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tweet App: forgot password</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel = "stylesheet" href="styles/main.css?v=1" type="text/css" />
        <script src="javascript/main.js" type="text/javascript"></script>
    </head>
    <jsp:include page="header.jsp" />
    <body>
        <div id="signUpForm">
        <h1>Forgot Password</h1>
        <form action = "forgotpassword" method = "post">
            <label>Enter Your  email address</label>
            <br>
            <br>
            <input type="hidden" name="action" value="forgotpassword">
            <input type="email" name="email" placeholder = "email" required>
            <br>
            <label>&nbsp;</label>
                <input class="twitButton" type="submit" value="Submit" class="margin_left">        
        </form>
        <br>
        <c:if test="${message != \"\"}">
            <h3 style="text-align: center;">${message}</h3>
        </c:if>
        </div>
    </body>
    <jsp:include page="footer.jsp" />
</html>