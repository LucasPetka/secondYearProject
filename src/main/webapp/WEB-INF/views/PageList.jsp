<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link href="/css/creative.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

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
            <a class="nav-link" href="#">
              <span data-feather="home"></span>
              Profile 
            </a>
          </li>
          <li class="nav-item side_link">
            <a class="nav-link" href="#">
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
        <h1 class="h2">${logfirstName}'s Dashboard</h1>
      </div>

      <div id="container m-4 w-100">
	  Statistics and other things to sum up about pages
	  <br>
	  <br>
	  <br>
	  <br>
	  <br>
	  <br>
	  <br>
	  </div>

      <h2>${websitename}</h2>
	  
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
				<button class="btn btn-success" id="add_on" type="submit"><i class="fas fa-plus"></i> Add Page</button>
			</div>
	  </div>
	  
	  <div id="upload">
		<form>
			  <div class="row">
			  <div class="col-md-3 mb-3">
			  <div class="input-group">
				<div class="input-group-prepend">
				  <span class="input-group-text" id="inputGroupPrepend">Website Name</span>
				</div>
				<input type="text" class="form-control" id="validationCustomUsername" placeholder="Name" aria-describedby="inputGroupPrepend" required>
				<div class="invalid-feedback">
				  Please input web-name.
				</div>
			  </div>
			</div>
			
			<div class="col-md-3 mb-3">
			  <div class="input-group">
				<div class="input-group-prepend">
				  <span class="input-group-text" id="inputGroupPrepend">Website URL</span>
				</div>
				<input type="text" class="form-control" id="validationCustomUsername" placeholder="URL" aria-describedby="inputGroupPrepend" required>
				<div class="invalid-feedback">
				  Please input web-URL.
				</div>
			  </div>
			</div>
			
			<div class="col-md-3 mb-3">
			<div class="input-group">
			  <div class="input-group-prepend">
				<label class="input-group-text" for="inputGroupSelect01">Refresh every</label>
			  </div>
			  <select class="custom-select" id="inputGroupSelect01">
				<option selected>Choose...</option>
				<option value="2"> 2 min </option>
				<option value="5"> 5 min </option>
				<option value="15"> 15 min </option>
				<option value="60">60 min </option>
				<option value="3"> 3 hours</option>
			  </select>
			</div>
			</div>
			
			
			<div class="col-md-2 mb-3">
			<button class="btn btn-success" type="submit">Add website</button>
			</div>
			
			<div class="col-md-1 mb-1">
			<button type="button" id="add_off" class="btn btn-outline-danger"><i class="fas fa-times"></i></button>
			</div>
			
			
			</div>
		</form>
	</div>
	
	
      <div class="table-responsive">
        <table class="table table-striped table-sm">
          <thead>
            <tr>
              <th>#</th>
			  <th>Name</th>
			  <th>Link</th>
			  <th>Last Change</th>
			  <th>Checking every</th>
			  <th></th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${pages}" var="page">
				<tr>
					<td><c:out value="${page.id}"/></td>
					<td><c:out value="${page.name}"/></td>
					<td><c:out value="${page.url}"/></td>
					<td><c:out value="${page.lastUpdated}"/></td>
					<td><c:out value="${page.frequency}"/></td>
					
					<td>
					<a class="btn main_b" href="/view_changes?id=${page.id}" role="button"> Check changes </a> 
					<a class="btn btn-danger" href="/removePage?id=${page.id}&websiteid=${websiteId}" role="button"> <i class="fas fa-trash-alt"></i> </a>
					</td>
				</tr>
			</c:forEach>
          </tbody>
        </table>
      </div>
    </main>
  </div>
</div>

<a class="btn btn-primary btn_or red" href="websiteList" role="button"> Back </a>
<a class="btn btn-primary btn_or" href="addPage?id=${websiteId}" role="button">Add new page</a>
</body>
</html>
