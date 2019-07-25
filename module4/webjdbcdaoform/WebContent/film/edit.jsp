<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>edition film</title>
</head>
<body>
	<h2>edition film</h2>
	<form method="post" action="/webjdbcdaoform/FilmServlet/">
		<input type="hidden"
			   name="id"
			   id="id"
			   value="<c:out value='${requestScope.film.id}'></c:out>"
			   />
		<label for="titre">titre</label>
		<input type="text"
			   name="titre"
			   id="titre"
			   value="<c:out value='${requestScope.film.titre}'></c:out>"
			   />
		<br />
		<label for="longueur">longueur</label>
		<input type="number"
			   name="longueur"
			   id="longueur"
			   value="<c:out value='${requestScope.film.longueur}'></c:out>"
			   />
		<br />
		<label for="annee">annee</label>
		<input type="number"
			   name="annee"
			   id="annee"
			   value="<c:out value='${requestScope.film.annee}'></c:out>"
			   />
		<br />
		<label for="genre">genre</label>
		<input type="text"
			   name="genre"
			   id="genre"
			   value="<c:out value='${requestScope.film.genre}'></c:out>"
			   />
		<br />
		<input type="submit" value="sauver" />
	</form>
</body>
</html>