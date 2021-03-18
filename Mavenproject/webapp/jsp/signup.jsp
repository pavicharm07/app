<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Tweet App: edit profile</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel = "stylesheet" href="styles/main.css?v=1" type="text/css" />
        <script src="javascript/main.js" type="text/javascript"></script>
    </head>
    <jsp:include page="header.jsp" />
    <body>
        <div id="signUpForm">
        <h1>Edit Profile</h1>
        <form action = "membership" method = "post" name="signupform" onsubmit="return validateForm(event);">
            <span style="color: red">${message}</span>
            <input type="hidden" name="action" value="editprofile">

            <div id="errorMessage" class="notVisible"></div>
            <span id="tbFirstName_error" class="notVisible">*</span>
            <label id ="lbFirstName">First Name</label><br>
            <input type="text" id = "tbFullName" name = "firstName" value ="${user.getFirstName()}" class="textCSS" onclick = "test('clicked it')" required><br>
            
            <div id="errorMessage" class="notVisible"></div>
            <span id="tbLastName_error" class="notVisible">*</span>
            <label id ="lbLastName">Last Name</label><br>
            <input type="text" id = "tbLastName" name = "LastName" value ="${user.getLastName()}" class="textCSS" onclick = "test('clicked it')"><br>
            
            <div id="errorMessage" class="notVisible"></div>
            <span id="tbGender_error" class="notVisible">*</span>
            Gender<select name="gender"> 
    <option value=" "> EMPTY </option> 
    <option value="Male">Male</option> 
    <option value="Female">Female</option> 
</select> 
            <span id="tbEmail_error" class="notVisible">*</span>   
            <input type = "hidden" name="email" id = "tbEmail" value="${user.getEmail()}">

            <span id="tbPassword_error" class="notVisible">*</span>
            <label id="lbPassword">Password</label><br>
            <input type="password" id = "tbPassword" name = "password" value =""class="textCSS" required><br>
                
            <span id="tbConfirmPassword_error" class="notVisible">*</span>
            <label id="lbConfirmPassword">Confirm Password</label><br>
            <input type="password" id = "tbConfirmPassword" name = "confirmPassword" value ="" class="textCSS" required><br>

            <fmt:parseDate pattern="yyyy-MM-dd" value="${user.getDoB()}" type = "date" var="date" />
            <span id="tbDoB_error" class="notVisible">*</span>
            <label id="lbDoB">Date of Birth</label><br>
            <input type="date" id = "tbDoB" name = "DoB" value ="${date}" class="textCSS" ><br>
            
            <br>
           
            <br><br>

            <label>&nbsp;</label>
                <input type="submit" value="Submit" class="margin_left">
        </form>
        </div> <!-- signUpForm div-->
    </body>
    
    <jsp:include page="footer.jsp" />
</html>