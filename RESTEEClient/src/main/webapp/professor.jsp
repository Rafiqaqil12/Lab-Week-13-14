<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="ftmk.ws.consumer.ProfessorFacade" %>
    
<%
    	ProfessorFacade professorFacade = new ProfessorFacade();

    	String result = professorFacade.getFixedProfessor();
%> 
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Demonstration of JSP as Web Service Client</title>
</head>
<body>

<h2>Default Hogwarts Professor from Web Service</h2>

<%= result %>


</body>
</html>