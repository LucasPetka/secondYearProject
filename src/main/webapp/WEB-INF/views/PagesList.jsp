<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>NetNag</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">

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
.red{
border-color:#DC3545;
}
.btn-primary{
margin:5px;
}
</style>

</head>
<body>

<div class="col-xs-1" align="center">
<h1 class="display-4">${website.name}</h1>
<hr>
</div>
<br>
<div class="mx-auto w-75" style=" background-color:#c5c5c5;  "   align="left">


<div class="mx-auto w-100 mr-3" style=" background-color:#c1c1c1;  "   align="left">
<table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>
	  <th scope="row">Name</th>
      <th scope="col">Url</th>
      <th scope="col">Last Updated</th>
	  <th scope="col">Checking every</th>
	  <th scope="col"></th>
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
		<a class="btn btn-primary" href="/view_changes?id=${page.id}" role="button"> Check changes </a> 
		<a class="btn btn-danger" href="/deletePage?id=${page.id}" role="button"> <i class="fas fa-trash-alt"></i> </a>
		</td>
	</tr>
	</c:forEach>
	
  </tbody>
</table>
</div>

<div class="mx-auto w-10 p-2" style=" background-color:#212529;  "  align="left">
<a class="btn btn-primary btn_or red" href="websiteList" role="button"> Back </a>
<a class="btn btn-primary btn_or" href="addPage" role="button">Add new page</a>
</div>


</div>




</body>
</html>
