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

<div  class="mx-auto w-25 p-3" style=" background-color:#c5c5c5;  " ;  align="center">

<h1 style="font-size:4vw;" class="display-4">Add Page</h1>
<hr>
<br>
  
<form:form method="POST" modelAttribute="page" action="/create">
  <div class="form-group">
    <form:label path="page_name" for="formGroupExampleInput1">Page Name</label>
    <form:input path="page_name" type="text" class="form-control" id="formGroupExampleInput1" placeholder="Name">
  </div>
  <div class="form-group">
    <form:label path="page_url" for="formGroupExampleInput1">Page URL</label>
    <form:input path="page_url" type="text" class="form-control" id="formGroupExampleInput1" placeholder="URL">
  </div>
  <div class="form-group">
    <form:label path="ref_time" for="formGroupExampleInput1">Refresh every</label>
    <form:select path="ref_time" class="form-control" id="exampleFormControlSelect1">
	<option value="2"> 2 min </option>
    <option value="5"> 5 min </option>
    <option value="15"> 15 min </option>
    <option value="60">60 min </option>
    <option value="3"> 3 hours</option>
    </select>
  </div>
</form:form>
  
<input type="submit" value="Add" name="add" class="btn btn-primary"/>
<input type="submit" value="Cancel" name="cancel" class="btn btn-danger"/>

</div>





</body>
</html>