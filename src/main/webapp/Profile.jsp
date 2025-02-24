<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String name =(String)request.getAttribute("name");
		String email =(String)request.getAttribute("email");
		int phno =(int)request.getAttribute("phno");
	%>
	<h2>Welcome <%=name%></h2>
	<h2>Your email is:<%=email %></h2>
	<h2>Your phone number is:<%=phno %></h2>
</body>
</html>