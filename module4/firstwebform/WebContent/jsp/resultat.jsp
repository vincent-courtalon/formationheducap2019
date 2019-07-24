<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>resultat du calcul</title>
<link rel="stylesheet" href="css/resultat.css" />
</head>
<body>
	<h2>resultat du calcul</h2>
	<p class="resultat"> le calcul a donné <%= request.getAttribute("resultat") %></p>
	<a href="index.html">retour a l'accueil</a>
</body>
</html>