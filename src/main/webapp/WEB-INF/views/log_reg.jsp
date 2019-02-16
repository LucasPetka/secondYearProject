<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>NetNag</title>

    <!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<link href="css/creative.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Custom styles for this template -->
    <link href="floating-labels.css" rel="stylesheet">
  </head>
  <body>

	<br>
	<script>
		$(document).ready(function(){
			$("#login").click(function(){
			$("#log").slideDown("slow");
			$("#reg").slideUp("slow");
			});
			
			$("#register").click(function(){
			$("#reg").slideDown("slow");
			$("#log").slideUp("slow");
			});	
		});
	</script>
	<center>
	<h1>NetNag</h1>
	</center>
<div class="container mx-auto log-reg">
	<button type="button" id="register" class="btn btn-outline-secondary float-right ml-2">Register</button>
	<button type="button" id="login" class="btn btn-outline-primary float-right ml-2">Log in</button>
	
	
	<p>
<c:if test="${error == true}">
	<b class="error">Invalid login or password.</b>
</c:if>
<c:if test="${logout == true}">
	<b class="logout">You have been logged out.</b>
</c:if>
</p>
	<br>
	<img class="mb-4" src="/img/logo.png" alt="" width="72" height="72">
			
			<div id="log">
			
			  
			<h1 class="h3 mb-3 font-weight-normal">Log in</h1>
				<c:url value="/login" var="loginUrl"/>
	<form action="${loginUrl}" method="post">       
		<c:if test="${param.error != null}">        
			<p>
				Invalid username and password.
			</p>
		</c:if>
		<c:if test="${param.logout != null}">       
			<p>
				You have been logged out.
			</p>
		</c:if>
		<p>
			<label for="username" class="sr-only">Email</label>
			<input type="email" id="username" class="form-control mb-2" name="username" placeholder="Email" required/>	
		</p>
		<p>
			<label for="password" class="sr-only">Password</label>
			<input type="password" id="password" name="password" class="form-control mb-2" placeholder="Password" required/>	
		</p>
		<input type="hidden"                        
			name="${_csrf.parameterName}"
			value="${_csrf.token}"/>
		<button class="btn btn-lg btn-primary btn-block" type="submit" class="btn">Log in</button>
		<p class="mt-5 mb-3 text-muted">&copy; NetNag 2019</p>
	</form>	
			
			
			</div>
			
			<div id="reg">
			<form class="form-signin">
			  
			  <h1 class="h3 mb-3 font-weight-normal">Register</h1>
			  <label for="r_inputEmail" class="sr-only">Email address</label>
			  <input type="email" id="r_inputEmail" class="form-control mb-2" placeholder="Email address" required autofocus>
			  <label for="r_inputPassword" class="sr-only">Password</label>
			  <input type="password" id="r_inputPassword" class="form-control mb-2" placeholder="Password" required>
			  <label for="r_repeatPassword" class="sr-only">Repeat Password</label>
			  <input type="password" id="r_repeatPassword" class="form-control mb-2" placeholder="Repeat Password" required>
			  <br>
			  
			  <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
			  <p class="mt-5 mb-3 text-muted">&copy; NetNag 2019</p>
			</form>
			</div>
			
	</div>
</body>
</html>
