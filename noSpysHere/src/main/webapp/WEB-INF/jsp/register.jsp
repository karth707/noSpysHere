<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>noSpysHere-Register</title>
</head>
<body> 
    <div>
    	<p style="background-color:red;color: white; text-align: center;opacity: 0.6; width: 40%;margin-top: 10px;">${newUserError}</p>
        <form:form action="/noSpysHere/user/register" method="post" commandName="userForm">
            <table>
                <tr>
                    <td><h2>No SpYs - Registration</h2></td>
                </tr>
                <tr>
                    <td>UserName:</td>
                </tr>
                <tr>
                    <td><form:input path="username" type="text"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                </tr>
                <tr>    
                    <td><form:password path="password"/></td>
                </tr>
                <tr>
                    <td>Confirm Password:</td>
                </tr>
                <tr>    
                    <td><form:input path='password_confirm' type="password"/></td>
                </tr>
            </table>
            <br/>
            <input type="submit" name="submit" value="Register" />
        </form:form>
        <br/>
        <div style="font-size: large"><a href="<c:url value="/user/login"/>">Login here!</a></div>
        <br/>
    </div>
</body>
</html>