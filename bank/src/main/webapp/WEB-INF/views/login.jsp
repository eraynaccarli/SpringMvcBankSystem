<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<div class="row">
		<div class="col-md-4">
			<form class="form-group" action="login" method="POST">
				<label for="name">Email </label>
				<input id ="name" class="form-control" name="email" type="text"> 
				<label for="name">Password</label>
				<input id = "password" class="form-control" name="password" type="password">
				<br>
				<input class="form-control btn btn-primary"type="submit" value="LOGIN">
			</form>
		</div>

	</div>
</body>
</html>