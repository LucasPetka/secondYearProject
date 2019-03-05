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




<div class="mx-auto w-25 p-3" style=" background-color:#c5c5c5;"  align="center">

<h1 style="font-size:4vw;" class="display-4">Add Website</h1>
<hr>

<br>
<form  method="POST" modelAttribute="website" action="/editWebsiteClicked?id=${website.id}">
<div class="form-group">
		<label path="name" for="formGroupExampleInput">Website Name</label>
        <input name="name" type="text" class="form-control" id="formGroupExampleInput" placeholder="Name"/>
        <errors name="name"/>
        
        <label path="email" for="formGroupExampleInput">Email Address to be Nagged</label>
        <input name="email" type="text" class="form-control" id="formGroupExampleInput" placeholder="drop down box.. of emails............"/>
        <errors name="email"/>
       
                    
		<input type="submit" value="Add" name="add" class="btn btn-primary"/>
        <input type="submit" value="Cancel" name="cancel" class="btn btn-primary"/>
        
        <input type="hidden"                        
			name="${_csrf.parameterName}"
			value="${_csrf.token}"/>
	
</div>
</form>
</div>
        


</body>
</html>
