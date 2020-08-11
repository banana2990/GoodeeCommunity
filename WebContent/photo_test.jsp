<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
	</head>
	
	<body>
	<form action="photoUp" method ="post" enctype="multipart/form-data">
	<br/>
	<input type="text" name="id"/>
	<br/>
	<input type="file" name="photo"/>
	<br/>
	<input type="submit"/>
	</form>
	
	
	
	
	</body>
	
	<script>
	
	</script>
</html>