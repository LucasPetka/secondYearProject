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
<form:form method="POST" modelAttribute="website" action="/addWebsite">
<div class="form-group">
<form:label path="name" for="formGroupExampleInput">Website Name</form:label>

        <form:input path="name" type="text" class="form-control" id="formGroupExampleInput" placeholder="Name"/>
        <form:errors path="name"/>
        
		<form:label path="url" for="formGroupExampleInput2">Website URL</form:label>
        <form:input path="url" type="text" class="form-control" id="formGroupExampleInput2" placeholder="URL"/>
        <form:errors path="url"/>
                    
		<input type="submit" value="Add" name="add" class="btn btn-primary"/>
        <input type="submit" value="Cancel" name="cancel" class="btn btn-primary"/>
</div>
</form:form>
</div>
        


</body>
</html>
