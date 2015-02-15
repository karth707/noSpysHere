<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head>
      <title><fmt:message key="title"/></title>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" type="text/css">
      <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/bootstrap.min.css">
<style>
  </head>
  <body>
  <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
          <div class="collapse navbar-collapse navHeaderCollapse">
              <ul class="nav navbar-nav navbar-right navbar">
              	<li>
              		<c:if test="${pageContext.request.userPrincipal.name != null}">
        				<a href="javascript:formSubmit()">Logout</a>
        			</c:if>
              	</li>
              </ul>
          </div>
      </div>
  </div>
	<sec:authorize access="hasRole('ROLE_USER')">
    <!-- For login user -->
    <c:url value="/j_spring_security_logout" var="logoutUrl" />
    	<form action="${logoutUrl}" method="post" id="logoutForm">
        	<input type="hidden" name="${_csrf.parameterName}"
                 value="${_csrf.token}" />
      	</form>
      	<script>
       		function formSubmit() {
            	document.getElementById("logoutForm").submit();
          	}
      	</script>

  <div align="center" style="margin-top:100px; width:100%; padding: 0px 15px 15px 15px;">
  	<p>hello : ${username}</p>
  </div>
  <br>
  <br>
  <br>
  <br>
  </sec:authorize>
  </body>
</html>