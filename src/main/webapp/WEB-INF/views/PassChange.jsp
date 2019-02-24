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
			<h4 class="card-title">Change Password</h4>
		</div>
		<div class="card-body">
			<div class="container mx-auto">
				<div class="row">
					<div class="col-sm-4 mx-auto">
					
						 <form  method="POST" modelAttribute="user" action="/changed">
						 
						<label>Current Password</label>
						<div class="form-group pass_show"> 
							<input name="old_pass" type="password" value="" class="form-control" placeholder="Current Password"> 
						</div> 
						   <label>New Password</label>
						<div class="form-group pass_show"> 
							<input name="new_pass" type="password" value="" class="form-control" placeholder="New Password"> 
						</div> 
						   <label>Confirm Password</label>
						<div class="form-group pass_show"> 
							<input name="conf_pass" type="password" value="" class="form-control" placeholder="Confirm Password"> 
						</div> 
						
						<button type="submit" class="btn btn-xs btn-primary float-right">Update Password</button>
						</form>
					</div>  
				</div>
			</div>
		</div>
    </div>
	
    </main>
  </div>

</div>
</body>
</html>
