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

<p>
<c:if test="${error == true}">
	<div class="alert alert-danger">
		<a class="error">Invalid Email and/or password or your email is not verified</a>
	</div>
	</c:if>
<c:if test="${logout == true}">
	<div class="alert alert-danger">
		<a class="logout">You have been logged out.</a>
	</div>
</c:if>
<c:if test="${register == true}">
	<div class="alert alert-success">
		You have successfully registered, please check your email address for confirmation.
	</div>
</c:if>
<c:if test="${successreg == true}">
	<div class="alert alert-success">
		You have successfully verified your account, you can log in now.
	</div>
</c:if>
<c:if test="${wronguser == true}">
	<div class="alert alert-danger">
		Wrong token
	</div>
</c:if>
<c:if test="${exists == true}">
	<div class="alert alert-danger">
		User with this email already exists
		<script>		$(document).ready(function(){	$("#reg").slideDown(0);
		$("#log").slideUp(0);});</script>
	</div>
</c:if>
<c:if test="${nomatch == true}">
	<div class="alert alert-danger">
		Passwords dont match
		<script>		$(document).ready(function(){	$("#reg").slideDown(0);
		$("#log").slideUp(0);});</script>
	</div>
</c:if>
</p>

	<button type="button" id="register" class="btn btn-outline-secondary float-right ml-2">Register</button>
	<button type="button" id="login" class="btn btn-outline-primary float-right ml-2">Log in</button>
	
	<br>
	<img class="mb-4" src="/img/logo.png" alt="" width="72" height="72">
			
			<div id="log">
			
			  
			<h1 class="h3 mb-3 font-weight-normal">Log in</h1>
				<c:url value="/login" var="loginUrl"/>
				
	<form action="${loginUrl}" method="post">   
	    
		<c:if test="${param.error != null}">        
			<div class="alert alert-danger">
				Invalid username and password.
			</div>
		</c:if>
		<c:if test="${param.logout != null}">       
			<div class="alert alert-danger">
				You have been logged out.
			</div>
		</c:if>
		<p>
			<label for="username" class="sr-only">Email</label>
			<input type="email" id="username" class="form-control mb-2" name="username" placeholder="Email" required/>	
		</p>
		
			<label for="password" class="sr-only">Password</label>
			<input type="password" id="password" name="password" maxlength="40" class="form-control" placeholder="Password" required/>	
		
		
		<a href="/password_reset" class="btn btn-link float-right mb-3" role="button" aria-pressed="true">Forgot Password</a>
		
		
		
		<input type="hidden"                        
			name="${_csrf.parameterName}"
			value="${_csrf.token}"/>
		<button class="btn btn-lg btn-primary btn-block" type="submit" class="btn">Log in</button>
		<p class="mt-5 mb-3 text-muted">&copy; NetNag 2019</p>
	</form>	
			
			
			</div>
			
			<div id="reg">
			<h1 class="h3 mb-3 font-weight-normal">Register</h1>
			<c:url value="/register" var="regUrl"/>
				<form action="${regUrl}" modelAttribute="user" method="post">   
		<p>
			<label for="username" class="sr-only">Email</label>
			<input type="email" id="username" class="form-control mb-2" name="login" placeholder="Email" required/>	
			
		</p>
		<p>
		
			<div class="input-group">
			<input type="text" id="firstName" class="form-control mb-2" name="firstName" placeholder="Name" required/>	
			
			
			<input type="text" id="lastName" class="form-control mb-2" name="lastName" placeholder="Surname" required/>	
			</div>
		</p>
		
		
		
		
		
		
		<p>
			<label for="password" class="sr-only">Password</label>
			<input type="password" id="password" name="password" class="form-control mb-2" maxlength="40" placeholder="Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" onchange="form.password2.pattern = RegExp.escape(this.value);" required/>	
		</p>
		<p>
			<label for="password2" class="sr-only">Repeat password</label>
			<input type="password" id="password" name="password2" class="form-control mb-2" maxlength="40" placeholder="Repeat password"  required/>	
		</p>
		<input type="hidden"                        
			name="${_csrf.parameterName}"
			value="${_csrf.token}"/>
		<button class="btn btn-lg btn-primary btn-block" type="submit" class="btn">Register</button>
		<p class="mt-5 mb-3 text-muted">&copy; NetNag 2019</p>
	</form>	
			</div>
			
	</div>
</body>
</html>
