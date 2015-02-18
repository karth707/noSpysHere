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
		<p>${info}</p>
		<p>spyCode: ${spyCode}</p>
		<p>isASpy: ${isASpy}</p>
		<p>ses: ${ses}</p>
	    <form:form action="/noSpysHere/message/add" method="post" commandName="messageForm" name="create-message">
            <table>
                <tr>
                    <td><h2>Add a new message</h2></td>
                </tr>
                <tr>
                    <td>Ttile:</td>
                </tr>
                <tr>
                    <td><form:input path="title" type="text" value="Enter Title"/></td>
                </tr>
                <tr>
                    <td>message:</td>
                </tr>
            </table>           
            <textarea name="message" form="messageForm">Enter Message</textarea>
            <input type="submit" name="submit" value="submit" />
        </form:form>
	    <br>
	    <p><a href="<c:url value="/message/list"/>">View all messages</a></p>
	    
	  	</sec:authorize>
	</body>
</html>