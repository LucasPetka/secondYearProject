<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>Dashboard Template · Bootstrap</title>

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
		<a class="mr-4 text-light">Signed in, as John</a>
      <a href="/logout" class="btn btn-sm btn-primary">Sign out</a>
    </li>
  </ul>
</nav>

<div class="container-fluid mt-5">
  <div class="row">
    <nav class="col-md-2 d-none d-md-block bg-light sidebar">
      <div class="sidebar-sticky">
	  
	  <img class="mb-4 mx-auto" src="/img/logo.png" alt="" width="72" height="72">
	  
        <ul class="nav flex-column">
          <li class="nav-item side_link">
            <a class="nav-link" href="#">
              <span data-feather="home"></span>
              Profile 
            </a>
          </li>
          <li class="nav-item side_link">
            <a class="nav-link" href="/websiteList">
              <span data-feather="file"></span>
              My websites
            </a>
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
        <h1 class="h2">Dashboard</h1>
      </div>

      <div id="container m-4 w-100">
	  Statistics and other things to sum up about websites
	  <br>
	  <br>
	  <br>
	  <br>
	  <br>
	  <br>
	  <br>
	  <br>
	  <br>
	  <br>
	  </div>

      <h2>My Websites</h2>
	  
	  <hr>
	  
		<script>
		$(document).ready(function(){
			$("#add_on").click(function(){
			$("#upload_on" ).hide();
			$("#upload").toggle( "slide" );
			});
			
			$("#add_off").click(function(){
			$("#upload_on" ).toggle( "slide" );
			$("#upload").hide();
			});	
		});
		</script>
	  
	  	  <div id="upload_on">
			<div class="col-md-2 mb-3">
				<button class="btn btn-success" id="add_on" type="submit"><i class="fas fa-plus"></i> Add Web</button>
			</div>
	  </div>
	  <c:if test="${badlink == true}">
	<div class="alert alert-danger">
		Website Does not exist or link does not contain http(s)://
		<script>$(document).ready(function(){$("#upload_on" ).hide();
		$("#upload").toggle(0);});</script>
	</div>
</c:if>
	 <div id="upload">
		<form  method="POST" modelAttribute="website" action="/addWebsite">
			  <div class="row justify-content-center">
			  <div class="col-md-4 mb-3">
			  <div class="input-group">
				<div class="input-group-prepend">
				  <span class="input-group-text" id="inputGroupPrepend">Website Name</span>
				</div>
			<input type="text" id="validationCustomUsername" class="form-control" name="name" placeholder="My blog" aria-describedby="inputGroupPrepend" required/>	
			<form:errors path="name"/>
			  </div>
			</div>
			
			<div class="col-md-4 mb-3">
			  <div class="input-group">
				<div class="input-group-prepend">
				  <span class="input-group-text" id="inputGroupPrepend">Website URL</span>
				</div>
				
				<input type="text" id="validationCustomUsername" class="form-control" name="url" placeholder="https://www.example.com" aria-describedby="inputGroupPrepend" required/>	
				<form:errors path="url"/>
			  </div>
			</div>
					<input type="hidden"                        
			name="${_csrf.parameterName}"
			value="${_csrf.token}"/>
			
			<div class="col-md-2 mb-3">
			<input type="submit" value="Add" name="add" class="btn btn-success"/>
			</div>
			
			<div class="col-md-2 mb-3">
			<button type="button" id="add_off" class="btn btn-outline-danger"><i class="fas fa-times"></i> Close</button>
			</div>
			
			
			</div>
		</form>
	</div>
	
	
      <div class="table-responsive">
        <table class="table table-striped table-sm">
          <thead>
            <tr>
              <th>#</th>
			  <th>Website name</th>
			  <th>Link</th>
			  <th>Active pages</th>
			  <th></th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${websites}" var="website">
				<tr>
					<td><c:out value="${website.id}"/></td>
					<td><c:out value="${website.name}"/></td>
					<td><c:out value="${website.url}"/></td>
					<td><c:out value="${website.activePages}"/></td>
					<td><c:out value="${website.tracking}"/></td>
					
					<td>
					<a class="btn main_b" href="/pageList?id=${website.id}" role="button"> Check pages </a> 
					<a class="btn btn-danger" href="/deleteWebsite?id=${website.id}" role="button"> <i class="fas fa-trash-alt"></i> </a>
					</td>
				</tr>
			</c:forEach>
          </tbody>
        </table>
      </div>
    </main>
  </div>

</div>
</body>
</html>
