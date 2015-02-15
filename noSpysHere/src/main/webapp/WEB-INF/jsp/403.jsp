<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
   	<title><fmt:message key="title"/></title>
  	<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/bootstrap.min.css">
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="collapse navbar-collapse navHeaderCollapse">
            <ul class="nav navbar-nav navbar-left navbar">
                <li><a href="<c:url value="/login"/>">Login</a></li>
                <li><a href="<c:url value="/signup"/>">Signup</a></li>
            </ul>
        </div>
    </div>
</div>
<div style="text-align:center">
<br>
<br>
<br>
<br>
<br>
	<h1><font color="red">HTTP Status 403 - Access is denied</font></h1>
	<c:choose>
		<c:when test="${empty userName}">
		  <h2>You do not have permission to access this page!</h2>
		</c:when>
		<c:otherwise>
		  <h2>Username : ${userName} <br/>
                    You do not have permission to access this page!</h2>
		</c:otherwise>
	</c:choose>
 </div>
</body>
</html>