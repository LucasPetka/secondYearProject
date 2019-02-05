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
  
<form:form method="POST" modelAttribute="page" action="/addPage">
  <div class="form-group">
  <table>
  <tr>
    <td><form:label path="name" for="formGroupExampleInput1">Page Name</form:label></td>
    <td><form:input path="name" type="text" class="form-control" id="formGroupExampleInput1" placeholder="Name"/></td>
  </tr>
  <tr>
    <td><form:label path="url" for="formGroupExampleInput1">Page URL</form:label></td>
    <td><form:input path="url" type="text" class="form-control" id="formGroupExampleInput1" placeholder="URL"/></td>
   </tr>
    <td><form:label path="frequency" for="formGroupExampleInput1">Refresh every</form:label></td>
    <td><form:select path="frequency" class="form-control" id="exampleFormControlSelect1"></td>
	<form:option value="2"> 2 min </form:option>
    <form:option value="5"> 5 min </form:option>
    <form:option value="15"> 15 min </form:option>
    <form:option value="60">60 min </form:option>
    <form:option value="3"> 3 hours</form:option>
    </form:select>
    </tr>
    <tr>
    		<td> <input type="submit" value="Add" name="add" class="btn btn-primary"/></td>
        <td> <input type="submit" value="Cancel" name="cancel" class="btn btn-primary"/></td>
        </tr>
    </table>
  </div>
  
</form:form>
</div>

</body>
</html>