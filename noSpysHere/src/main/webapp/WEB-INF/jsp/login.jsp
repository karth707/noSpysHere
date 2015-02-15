<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title><fmt:message key="title"/></title>
</head>

<body onload='document.loginForm.username.focus();'>
    <h1>No SpYs Here!</h1>
	<div>
		<p>spyCode: ${spyCode}</p>
	  	<p>isASpy: ${isASpy}</p>
	  	
		<h3>Login with Username and Password</h3> 
		
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if> 
		
		<form name='login'
  			action="<c:url value='/j_spring_security_check' />" method='POST' role="form" style="text-align: center;">
			<table>
				<tr>
					<td>User ID:</td>
					<td><input type='text' name='username'></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password'/></td>
				</tr>
				<tr>
					<td>Login:</td>
					<td><input name="submit" type="submit" value="submit"/></td>
				</tr>
			</table>
  			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		<br/>
		<div>
			<p>New User? Register <a href="<c:url value="/user/register"/>">Here!</a></p>
		</div>
	</div>
</body>
</html>