<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Validation Result</title>
</head>
<body>

<%

	String queryName = request.getAttribute("query").toString();
	boolean flag = Boolean.parseBoolean(request.getAttribute("flag").toString());
	
	// Customize message
	String message = "The queried name <b>" + queryName + "</b> is "; 
	if (!flag)
		message += "not ";
	message += "a valid professor in Hogwarts";
%>


<br><br>
<%= message %>


</body>
</html>