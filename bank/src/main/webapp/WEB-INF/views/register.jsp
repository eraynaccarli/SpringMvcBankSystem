<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    isELIgnored = "false"
    %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="error" items="${errors}">
	<p>
	  <c:out value="${error.key}"/></br>
	  <c:out value="${error.value}"/>
	</p>
	</c:forEach>
	<form action="/bank/register" method="POST">
		Name :<input name="name" type="text">
		Surname :<input name="surname" type="text">
		Email :<input name="email" type="text">
		Password :<input name="password" type="text">
		Tc :<input name="tc" type="text">
		<input value="Register" type="submit">
	</form>
</body>
</html>