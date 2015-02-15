<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head>
      <title><fmt:message key="title"/></title>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  </head>
  <body>
	<sec:authorize access="hasRole('ROLE_USER')">
	
	<div align="center" style="margin-top:100px; width:100%; padding: 0px 15px 15px 15px;">
  		<p>hello : ${username}</p>
  	</div>
	
    <!-- For login user -->
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        				<a href="javascript:formSubmit()">Logout</a>
	</c:if>
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