<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Demonstration of JSP as Web Service Client</title>
</head>
<body>

<br><br>
Enter a name of professor that you want to confirm his existance is Hogwarts.
<br><br>
<form action="validationServlet" method="post">

Name: <input type="text" name="name">

<input type="submit" value="Validate">

</form>


</body>
</html>