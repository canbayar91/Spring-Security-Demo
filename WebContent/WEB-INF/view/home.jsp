<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Home Page</title>
</head>
<body>
	<h2>Home Page</h2>
	<hr>
	<p>Welcome to the home page</p>
	<hr>
	<p>
		User: <security:authentication property="principal.username"/>
		<br><br>
		Roles: <security:authentication property="principal.authorities"/>
	</p>
	<security:authorize access="hasRole('MANAGER')">
		<p><a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a></p>
	</security:authorize>
	<hr>
	<security:authorize access="hasRole('ADMIN')">
		<p><a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a></p>
	</security:authorize>
	<hr>
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout"/>
	</form:form>
</body>
</html>