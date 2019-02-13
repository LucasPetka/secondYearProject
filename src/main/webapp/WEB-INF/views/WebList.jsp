<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>NetNag</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

<style>
.btn_or {
background-color:#212529;
border-color:#007BFF;

}
.btn_or:hover{
background-color:#ffffff;
border-color:#007BFF;
color:#212529;

}

</style>



</head>
<body>


<div class="col-xs-1" align="center">
<h1 class="display-4">My websites list</h1>
<hr>
</div>
<br>
<div class="mx-auto w-75" style=" background-color:#c5c5c5; "   align="left">

<div class="mx-auto w-100 mr-3" style=" background-color:#c1c1c1; "   align="left">
<table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Website name</th>
      <th scope="col">URL</th>
      <th scope="col">Active pages</th>
      <th scope="col">Tracking</th>
	  <th scope="col"></th>
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
		<a class="btn btn-primary" href="/pageList?id=${website.id}" role="button"> Check pages </a> 
		<a class="btn btn-primary" href="/deleteWebsite?id=${website.id}" role="button"> Delete website tracking </a>
		</td>
	</tr>
	</c:forEach>
    
  </tbody>
</table>
</div>

<div class="mx-auto w-10 p-2" style=" background-color:#212529; "  align="left">
<a class="btn btn-primary btn_or" href="/addWebsite" role="button">Add new website</a>
</div>

</div>






</body>
</html>