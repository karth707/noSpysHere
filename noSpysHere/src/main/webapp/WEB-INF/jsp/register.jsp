<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>

<html>
<head>
		<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" type="text/css">
      	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/bootstrap.min.css">
<title>iBase-Signup</title>
<style>
#login-box {
	width: 100%;	
	margin: 0 auto 0 auto;
	background: transparent;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
}
#headerBack {
	width:100%;
	height:220px;
}
#headerBack2 {
	width:100%;
	height:150px;
}
#backLogin{
	width:100%;
	height:auto;
}
#header{
	width:100%;
	height:80px;
	padding: 5 25 5 25;
	opacity: 0.8; 
	background-color:black;
}
h1, h2 {	
	color: whitesmoke;
	text-align: center;	
}
h2 {
	padding: 5 25 5 25;
	opacity: 0.6; 
	background-color:black;
}
table {
	width: 400px;
}

tr.spaceUnder > td
{
  padding-bottom: 5px;
}

#field {
	padding: 2 25 2 25;
	background-color:black;
	opacity: 0.6;
	color: whitesmoke;
	text-align: center;
	width: 40%;
	margin:4px;
}
</style>
</head>
<body> 
    
    <div id="headerBack">
    	<br>
    	<br>
    	<br>
    	<div id="header">
    		<h1>iBase - Your Personal Image Database</h1>
    	</div>   
    </div>
    <div id="backLogin">
    <div align="center" id="login-box">
    	<p style="background-color:red;color: white; text-align: center;opacity: 0.6; width: 40%;margin-top: 10px;">${newUserError}</p>
        <form:form action="register" method="post" commandName="register">
            <table border="0">
                <tr class="spaceUnder">
                    <td colspan="2" align="center"><h2>iBase - Registration</h2></td>
                </tr>
                <tr class="spaceUnder">
                    <td id="field" colspan="2" align="center">UserName:</td>
                </tr>
                <tr class="spaceUnder">
                    <td colspan="2" align="center"><form:input path="username" type="text" style="width: 100%; border-color: gainsboro;border-width: 3;"/></td>
                </tr>
                <tr class="spaceUnder">
                    <td id="field" colspan="2" align="center">Password:</td>
                </tr>    
                    <td colspan="2" align="center"><form:password path="password" type="password" style="width: 100%; border-color: gainsboro;border-width: 3;"/></td>
                </tr>
                <tr class="spaceUnder">
                    <td id="field" colspan="2" align="center">Confirm Password:</td>
                </tr>    
                    <td colspan="2" align="center"><form:password path="password_confirm" type="password" style="width: 100%; border-color: gainsboro;border-width: 3;"/></td>
                </tr>
            </table>
            <br>
            <input type="submit" name="submit" value="Register" />
        </form:form>
        <div style="font-size: large"><a href="<c:url value="/user/login"/>">Login here!</a></div>
        <br>
    </div>
    </div>
    <div id="headerBack2">
    </div>
</body>
</html>