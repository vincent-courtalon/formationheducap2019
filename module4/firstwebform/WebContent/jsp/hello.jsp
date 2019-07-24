<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hello!</title>
</head>
<body>
	<h2>bienvenue sur la page jsp</h2>
	<p>
	nous somme le <%= LocalDateTime.now() %>
	<p>
	<ul>
		<%
		for (int i = 1; i <= 10; i++) {
		%>
			<li>i: <%= i %></li>		
		<%
		}
		%>
	</ul>
</body>
</html>