<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="//code.jquery.com/jquery-1.12.4.js"></script>
	<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</head>
 <body>
    <nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
	  <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">NetNag</a>
	  <ul class="navbar-nav px-3">
		<li class="nav-item text-nowrap">
			<a class="mr-4 text-light">Signed in as ${logfirstName}</a>
	      <a href="/logout" class="btn btn-sm btn-primary">Sign out</a>
	    </li>
	  </ul>
	</nav>

<div class="container-fluid pt-5 h-100">
  <div class="row h-100">
    <nav class="col-md-2 d-none d-md-block bg-light sidebar">
      <div class="sidebar-sticky">
	  
	  <img class="mb-4 mx-auto" src="/img/logo.png" alt="" width="72" height="72">
	  
        <ul class="nav flex-column">
          <li class="nav-item side_link">
            <a class="nav-link" href="/profile">
              <span data-feather="home"></span>
              Profile 
            </a>
          </li>
          <li id="open_webs" class="nav-item side_link">
            <a class="nav-link" href="/websiteList">
              <span data-feather="file"></span>
              My websites
            </a>
            
            	<div id="webs">
					<ul class="side_w">
						<c:forEach items="${websites}" var="website">
						<li class="nav-item side_link"><a class="nav-link" href="/pageList?id=${website.id}"><c:out value="${website.name}"/></a></li>
						</c:forEach>
					</ul>
				</div>

          </li>
          <li class="nav-item side_link">
            <a class="nav-link" href="#">
              <span data-feather="shopping-cart"></span>
              Tokens
            </a>
          </li>
        </ul>
      </div>
    </nav>

    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">${logfirstName}'s Dashboard</h1>
      </div>

      <div class="card w-75">
           <div class="card-header">
               <h4 class="card-title">Edit Profile</h4>
           </div>
           
           <div class="card-body">
           
           <form method="POST" modelAttribute="user" action="/updateUser">

				<div class="row">
	                <div class="col-md-12">
	                   <p class="lead float-right"> Tokens in your wallet: <b>15</b> <i class="fas fa-coins ml-2"></i></p>   
	                </div>
	            </div>
	
	
	            <div class="row">
	             	<div class="col-md-6 pr-1">
		                 <div class="form-group">
		                     <label>First Name</label>
		                     <input type="text" name="firstName" class="form-control" placeholder="First Name" value="${logfirstName}">
		                 </div>
	            	</div>
	            	
	                <div class="col-md-6 pl-1">
	                    <div class="form-group">
	                        <label>Last Name</label>
	                        <input type="text" name="lastName" class="form-control" placeholder="Last Name" value="${loglastName}">
	                    </div>
	                </div>
	             </div>
	
	
	        <input type="hidden"                        
			name="${_csrf.parameterName}"
			value="${_csrf.token}"/>
	                            
			    <button type="submit" class="btn btn-xs btn-primary pull-right">Update Profile</button>
				<a href="/changePassword" class="btn btn-xs btn-outline-primary" role="button" aria-pressed="true">Change Password</a>
				<div class="clearfix"></div>
				
          </form>

		<div class="row mt-4">
			<div class="col-md-9 p-0">
				<button type="button" id="email_on" class="btn btn-link float-left">Choose the e-mails to which you want to get information about Websites updates</button>
			</div>
		</div>

		<div id="emails_l">
		
		<div class="input-group">
			Choose how much e-mails you want to add:
			<br>
		<div class="col-md-3">	
		<select class="custom-select" id="ft_nr" name="ft_nr" onchange="ft_count()" >
			<option selected disabled>Choose:</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
		</select>
		</div>
		</div>
		
		<div class="row mt-2">
			<div class="col-md-7 mx-auto">
				<div id="email_card" class="card pb-3">
				  <div class="card-body">
					<p id="fotoss">
					</p>
				  </div>
				</div>
			</div>
		</div>
		
		</div>


		<script>
			function ft_count(){
				var count =	document.getElementById("ft_nr").value;
				document.getElementById("fotoss").innerHTML = "";
				document.getElementById('email_card').style.display = 'block';
				
				for(i=1; i<=count; i++){
					var kint = "image" + i;
					document.getElementById("fotoss").innerHTML = document.getElementById("fotoss").innerHTML + "<div class='row'> <div class='col-md-12'> <div class='input-group mb-1'> <div class='input-group-prepend'> <span class='input-group-text' id='basic-addon1'> Email "+ i +" </span> </div> <input type='text' class='form-control' id='"+kint+"' name='"+kint+"' placeholder='E-mail' aria-label='E-mail' aria-describedby='basic-addon1'>  </div> </div> </div>"
					}
					document.getElementById("fotoss").innerHTML = document.getElementById("fotoss").innerHTML + " <button type='submit' class='btn btn-xs btn-primary float-right p-2'>Upload E-mails</button>";
				}
			
				$(document).ready(function(){
					$("#email_on").click(function(){
					$("#emails_l").slideToggle("slow");
					});
				});
				
				$("#open_webs").hover(function(){
				$("#webs").finish().slideToggle();
				});
		</script>
		
         </div>
       </div>				
    </main>
  </div>

</div>
</body>
</html>