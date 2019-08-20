<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edition produit</title>
</head>
<body>
	<h2>edition produit</h2>
	<form action="/save" method="post">
		<input type="hidden" name="id" id="id" value="${produit.id}" />
		<label for="nom">nom produit</label>
		<input type="text" name="nom" id="nom" value="${produit.nom}" /><br />
		<label for="prix">prix produit</label>
		<input type="text" name="prix" id="prix" value="${produit.prix}" /><br />
		<label for="poids">poids produit</label>
		<input type="text" name="poids" id="poids" value="${produit.poids}" /><br />
		<input type="submit" value="sauvegarder" />
	</form>
</body>
</html>