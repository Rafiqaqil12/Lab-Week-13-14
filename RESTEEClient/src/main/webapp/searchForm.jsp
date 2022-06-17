<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Professor by surname</title>
</head>
<body>

<br><br>
Enter surname of the professor that you want to search.
<br><br>
<form action="searchServlet" method="post">

Surname: <input type="text" name="param">

<input type="submit" value="Search">

</form>
</body>
</html>