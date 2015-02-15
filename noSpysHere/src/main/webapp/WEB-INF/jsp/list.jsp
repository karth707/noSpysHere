<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
      	<title><fmt:message key="title"/></title>
  	</head>
  	
  	<body>
		<sec:authorize access="hasRole('ROLE_USER')">
		
		<div>
	  		<p>hello : ${username}</p>
	  		<p>spyCode: ${spyCode}</p>
	  		<p>isASpy: ${isASpy}</p>
	  		<c:if test="${pageContext.request.userPrincipal.name != null}">
	    		<a href="javascript:formSubmit()">Logout</a>
			</c:if>
	  		<p><a href="<c:url value="/message/add"/>">Add a message</a></p>
	  	</div>
		<h2>Messages:</h2>
		<div class="messages">
			<c:if test="${not empty messages}">
				<c:forEach items="${messages}" var="message">
					<div class="message">
					Title: ${message.title} <br> 
					Message: ${message.message}
					</div>
					<br>
				</c:forEach>
			</c:if> 
		</div>
		<br>
		<br>
	    <!-- For login user -->
	    <c:url value="/j_spring_security_logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	    <script>
			function formSubmit() {
		    	document.getElementById("logoutForm").submit();
		  	}
	    </script>
	  	</sec:authorize>
	</body>
</html>