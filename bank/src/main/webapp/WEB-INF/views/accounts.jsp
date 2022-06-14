<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    isELIgnored = "false"
    %>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Accounts</title>
</head>
<body>
<h1>List Of Accounts</h1>
<ul class="list-group">
	<c:forEach items="${accounts}"  var="account">
		<li class="list-group-item">
		<h1>Name : ${account.name}</h1>
		<p>Iban : ${account.iban }</p>
		<p>Balance : ${account.balance }<p>
		<button> Send Money </button>
		</li>
		<br>
	</c:forEach>
</ul>
</body>
</html>