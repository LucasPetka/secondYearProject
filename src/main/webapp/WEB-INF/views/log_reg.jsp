
<!doctype html>
<html lang="en">
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
	
<div class="container mx-auto log-reg">
	<button type="button" id="register" class="btn btn-outline-secondary float-right ml-2">Register</button>
	<button type="button" id="login" class="btn btn-outline-primary float-right ml-2">Login</button>
	
	
	
	<br>
	<img class="mb-4" src="logo.png" alt="" width="72" height="72">
			
			<div id="log">
			<form class="form-signin">
			  
			  <h1 class="h3 mb-3 font-weight-normal">Sign in</h1>
			  <label for="l_inputEmail" class="sr-only">Email address</label>
			  <input type="email" id="l_inputEmail" class="form-control mb-2" placeholder="Email address" required autofocus>
			  <label for="l_inputPassword" class="sr-only">Password</label>
			  <input type="password" id="l_inputPassword" class="form-control mb-2" placeholder="Password" required>
			  <div class="checkbox mb-3">
				<label>
				  <input type="checkbox" value="remember-me"> Remember me
				</label>
			  </div>
			  <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
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