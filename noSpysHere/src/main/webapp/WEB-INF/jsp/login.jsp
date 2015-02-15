<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<head>
	<title><fmt:message key="title"/></title>
	<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" type="text/css">
	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/bootstrap.min.css">
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: white;
	background-color: red;
	border-color: #ebccd1;
	text-align: center;
	opacity: 0.7;
}
 
.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: white;
	background-color: green;
	border-color: darkgreen;
	text-align: center;
	opacity: 0.7;
}
#backLogin{
	width:100%;
	height:auto;
}
#login-box {
	width: 440px;
	padding: 20px;
	margin: auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
}

#header{
	width:100%;
	height:80px;
	padding: 5 25 5 25;
	background-color:black;
}

#headerBack {
	width:100%;
	height:220px;
	background-image:url("<c:url value="/resources/images/background_3.jpg" />");
}
#headerBack2 {
	width:100%;
	height:150px;
	background-image:url("<c:url value="/resources/images/background_3.jpg" />");
	opacity: 0.8;
}
h1, h2, h3 {	
	color: whitesmoke;
	text-align: center;	
}
h2, h3 {
	padding: 5 25 5 25;
	opacity: 0.6; 
	background-color:black;
}
table {
	width: 400px;
}

tr.spaceUnder > td
{
  padding-bottom: 2px;
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
  
<body onload='document.loginForm.username.focus();'>
   	<div id="headerBack">
    	<br>
    	<br>
    	<br>
    	<div id="header">
    		<h1>iBase - Your Personal Image Database</h1>
    	</div>   
    </div>   	
	<div id="backLogin">
		<div id="login-box">
			<h3>Login with Username and Password</h3> 
			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if> 
			<form name='loginForm'
	  			action="<c:url value='/j_spring_security_check' />" method='POST' role="form" style="text-align: center;">
				<table>
					<tr class="spaceUnder">
						<td id="field">User ID:</td>
						<td><input type='text' name='userId' style="width:100%;border-color: gainsboro;border-width: 3;"></td>
					</tr>
					<tr class="spaceUnder">
						<td id="field">Password:</td>
						<td><input type='password' name='password' style="width:100%; border-color: gainsboro;border-width: 3;"/></td>
					</tr>
				</table>
				<br>
				<input name="submit" type="submit"value="submit" style="font-size: large;"/>
	  			<input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />
			</form>
			<div style="font-size: large; text-align: center;">
				<p>New User? Signup <a href="<c:url value="/signup"/>"><font color="Green">Here!</font></a></p>
			</div>
		</div>
	</div>
	<div id="headerBack2">
    </div>
</body>
</html>