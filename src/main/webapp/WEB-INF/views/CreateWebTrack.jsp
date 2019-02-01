<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>NetNag</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>
<body>




<div class="mx-auto w-25 p-3" style=" background-color:#c5c5c5;  " ;  align="center">

<h1 class="display-4">Add Website</h1>
<hr>

<br>
<form:form method="POST" modelAttribute="todo" action="/create">
  <div class="form-group">
    <form:label path="web_name" for="formGroupExampleInput">Website Name</label>
    <form:input path="web_name" type="text" class="form-control" id="formGroupExampleInput" placeholder="Name">
  </div>
  <div class="form-group">
    <form:label path="web_url" for="formGroupExampleInput2">Website URL</label>
    <form:input path="web_url" type="text" class="form-control" id="formGroupExampleInput2" placeholder="URL">
  </div>
</form:form>

<input type="submit" value="Add" name="add" class="btn btn-primary"/>
<input type="submit" value="Cancel" name="cancel" class="btn btn-primary"/>
</div>





</body>
</html>