<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

    <head>
        <title>Tweet APP: login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <c:if test="${checkedCookies == null}">
        <jsp:forward page="/redirection"/>
    </c:if>

    <c:if test="${sessionScope.loggedIn == true}">
        <jsp:forward page="/home.jsp"/>
    </c:if>

    
    <jsp:include page="header.jsp" />
    <body>
        <div id="signUpForm">
        <h1>Log In</h1>
        <span style="color: red;">${message}</span>
        <form action = "membership" method = "post">
            <input type="hidden" name="action" value="login">
            <input id="userEmail" name = "userEmail" placeholder="email or username" required><br><br>
            <input id="loginPassword" type="password" name="userPassword" placeholder="password" required><br><br>
            
            
            <label>&nbsp;</label>
            <input type="submit" value="Log in" class="margin_left"><br><br>
            <span id="dancingFont"><input type ="checkbox" name="rememberMe" value="rememberMe">Remember me<br><br>
                New?<a href="signup.jsp">   Sign up now</a><br><br>
            <a href= "forgotpassword.jsp">Forgot password?</a><br></span>

               
        </form>
        </div>
    </body>
    <jsp:include page="footer.jsp" />