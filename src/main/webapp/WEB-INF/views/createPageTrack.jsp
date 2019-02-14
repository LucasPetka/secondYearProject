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

<div  class="mx-auto w-25 p-3" style=" background-color:#c5c5c5;  "  align="center">

<h1 style="font-size:4vw;" class="display-4">Add Page</h1>
<hr>
<br>
  
<form:form method="POST" modelAttribute="page" action="/addPage?id=${websiteId}">
  <div class="form-group">
  
    <form:label path="name" for="formGroupExampleInput1">Page Name</form:label>
    <form:input path="name" type="text" class="form-control" id="formGroupExampleInput1" placeholder="Name"/>
    <form:errors path="name"/>
    
    <form:label path="url" for="formGroupExampleInput1">Page URL</form:label>
    <form:input path="url" type="text" class="form-control" id="formGroupExampleInput1" placeholder="URL"/>
    <form:errors path="url"/>
 
   <form:label path="frequency" for="formGroupExampleInput1">Refresh every</form:label>
    <form:select path="frequency" class="form-control" id="exampleFormControlSelect1">
	<form:option value="2"> 2 min </form:option>
    <form:option value="5"> 5 min </form:option>
    <form:option value="15"> 15 min </form:option>
    <form:option value="60">60 min </form:option>
    <form:option value="3"> 3 hours</form:option>
    </form:select>
    <form:errors path="frequency"/>
    <br>
    
    		<input type="submit" value="Add" name="add" class="btn btn-primary"/>
        <input type="submit" value="Cancel" name="cancel" class="btn btn-danger"/>
        
   
  </div>
  
</form:form>
</div>

</body>
</html>