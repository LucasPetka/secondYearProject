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

<h1 style="font-size:4vw;" class="display-4">Add Website</h1>
<hr>

<br>
<form:form method="POST" modelAttribute="website" action="/websiteList">
<div class="form-group">
<table>
	<tr>
		<td><form:label path="name" for="formGroupExampleInput">Website Name</form:label></td>
        <td><form:input path="name" type="text" class="form-control" id="formGroupExampleInput" placeholder="Name"/></td>
    </tr>
    <tr>

		<td><form:label path="url" for="formGroupExampleInput2">Website URL</form:label></td>
        <td><form:input path="url" type="text" class="form-control" id="formGroupExampleInput2" placeholder="URL"/></td>
                
    </tr>
    <tr>            
		<td> <input type="submit" value="Add" name="add" class="btn btn-primary"/></td>
        <td> <input type="submit" value="Cancel" name="cancel" class="btn btn-primary"/></td>
               
    </tr>
                
</table>
</div>
</div>
</form:form>
        


</body>
</html>
