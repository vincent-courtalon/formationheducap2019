<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>edition jeux</title>
</head>
<body>
	<h2>edition jeux</h2>
	<form method="post" action="/macromania/JeuxEditServlet/">
		<input type="hidden"
			   name="id"
			   id="id"
			   value="<c:out value='${requestScope.edit_jeu.id}'></c:out>"
			   />
		<label for="titre">titre</label>
		<input type="text"
			   name="titre"
			   id="titre"
			   value="<c:out value='${requestScope.edit_jeu.titre}'></c:out>"
			   />
		<br />
		<label for="description">description</label>
		<textarea  name="description"
			   		id="description" rows="5" cols="50"><c:out value='${requestScope.edit_jeu.description}'></c:out></textarea>
		<br />
		<label for="plateforme">plateforme</label>
		<input type="text"
			   name="plateforme"
			   id="plateforme"
			   value="<c:out value='${requestScope.edit_jeu.plateforme}'></c:out>"
			   />
		<br />
		<label for="annee">annee</label>
		<input type="number"
			   name="annee"
			   id="annee"
			   value="<c:out value='${requestScope.edit_jeu.annee}'></c:out>"
			   />
		<br />
		<input type="submit" value="sauver" />
	</form>
</body>
</html>