<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>NetNag</title>

    <!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<link href="css/creative.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="floating-labels.css" rel="stylesheet">
  </head>
  <body>

	
<div class="container mx-auto log-reg">

<div class="text-center">
	<img class="mb-4 mx-auto" src="/img/logo.png" alt="" width="72" height="72"><br>
</div>

	<button type="button" class="btn btn-light mb-4 rounded "> <i class="fas fa-chevron-left"></i> </button>
	
	<div class="text-center"><b>Forgot Your Password?</b><br>
	<p>
	We get it, stuff happens. Just enter your email address below and we'll send you a link to reset your password!
	</p>
	</div>
<br>
			<c:if test="${resetEmailSent == true}">
	<div class="alert alert-success">
		Password reset email sent, please check your inbox and follow the instructions.
	</div>
</c:if>
<c:if test="${noEmailMatch == true}">
	<div class="alert alert-danger">
		We can't find this email address in our databases! 
	</div>
</c:if>
			<div id="log">
			<h1 class="h3 mb-3 font-weight-normal">Reset password</h1>
			<form class="form-signin" method="post" >
			
			  <label for="l_inputEmail" class="sr-only">Email address</label>
			  <input name="email" type="email" id="l_inputEmail" class="form-control mb-2" placeholder="Email address" required autofocus>
			  
			  		<input type="hidden"                        
			name="${_csrf.parameterName}"
			value="${_csrf.token}"/>
			  <button class="btn btn-lg btn-primary btn-block" type="submit">Reset Password</button>
			</form>
			 <p class="mt-5 mb-3 text-muted">&copy; NetNag 2019</p>
			</div>
			
	</div>
</body>
</html>
