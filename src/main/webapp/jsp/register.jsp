<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Registration</title>
</head>
<body>


<form:form id="regForm" modelAttribute="user" name = "check" onsubmit="return checkMail();" action=" registerProcess" method="post" >
    <table align="center">
        <tr>
            <td>
                <form:label path="username">Username</form:label>
            </td>
            <td>
                <form:input path="username" name="username" id="username"/>
            </td>
             <td>
                <form:errors path="username" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="password">Password</form:label>
            </td>
            <td>
                <form:password path="password" name="password" id="password"/>
            </td>
            <td>
                <form:errors path="password" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="firstname">FirstName</form:label>
            </td>
            <td>
                <form:input path="firstname" name="firstname" id="firstname"/>
            </td>
             <td>
                <form:errors path="firstname" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="lastname">LastName</form:label>
            </td>
            <td>
                <form:input path="lastname" name="lastname" id="lastname"/>
            </td>
             <td>
                <form:errors path="lastname" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="email">Email</form:label>
            </td>
            <td>
                <form:input path="email" name="email" id="email"/>
            </td>
             <td>
                <form:errors path="email" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="address">Address</form:label>
            </td>
            <td>
                <form:input path="address" name="address" id="address"/>
            </td>
             <td>
                <form:errors path="address" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="phone">Phone</form:label>
            </td>
            <td>
                <form:input path="phone" name="phone" id="phone"/>
            </td>
             <td>
                <form:errors path="phone" />
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <form:button id="register" type = "submit" name="register">Register</form:button>
            </td>
        </tr>
        <tr></tr>
        <tr>
            <td></td>
            <td><a href="home.jsp">Home</a>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
