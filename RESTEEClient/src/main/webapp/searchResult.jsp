<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
 <%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ftmk.model.Professor" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Result</title>
</head>

<% 

int size = Integer.parseInt(session.getAttribute("size").toString());
List<Professor> result = (ArrayList<Professor>) session.getAttribute("result");

%>

<body>

<h2> Search Result</h2>

<br><br>
The list of professor that had been searched : <%= size %> 

<br><br>

<%
	int number = 0;
	for (Professor professor:result)
		out.print(++number + ". " + professor.getName() + "<br>");
%>

<br>
Click <a href="index.jsp">here</a> to return to the main page



</body>
</html>