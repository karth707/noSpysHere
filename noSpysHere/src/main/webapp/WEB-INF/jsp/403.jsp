<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
   	<title><fmt:message key="title"/></title>
</head>
<body>
	<h1>HTTP Status 403 - Access is denied</h1>
	<c:choose>
		<c:when test="${empty userName}">
		  <h2>You do not have permission to access this page!</h2>
		</c:when>
		<c:otherwise>
		  <h2>Username : ${userName} <br/>
                    You do not have permission to access this page!</h2>
		</c:otherwise>
	</c:choose>
</body>
</html>