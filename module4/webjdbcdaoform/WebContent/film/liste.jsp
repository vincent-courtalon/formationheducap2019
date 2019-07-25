<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>liste des films</title>
</head>
<body>
	<h2>Good Movies</h2>
	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>TITRE</th>
				<th>LONGUEUR</th>
				<th>ANNEE</th>
				<th>GENRE</th>
				<th>ACTIONS</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.films}" var="film" >
				<tr>
					<td><c:out value="${film.id}"></c:out></td>
					<td><c:out value="${film.titre}"></c:out></td>
					<td><c:out value="${film.longueur}"></c:out></td>
					<td><c:out value="${film.annee}"></c:out></td>
					<td><c:out value="${film.genre}"></c:out></td>
					<td><a href="/webjdbcdaoform/FilmServlet/<c:out value='${film.id}'></c:out>" >editer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/webjdbcdaoform/FilmServlet/0">creer nouveau film</a>
</body>
</html>