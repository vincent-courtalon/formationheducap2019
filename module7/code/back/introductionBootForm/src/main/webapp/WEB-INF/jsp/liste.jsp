<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>liste des produits</title>
</head>
<body>
	<h2>liste des produits</h2>
	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>NOM</th>
				<th>PRIX</th>
				<th>POIDS</th>
				<th>ACTIONS</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${produits}" var="p" >
			<tr>
				<td>${p.id}</td>
				<td>${p.nom}</td>
				<td>${p.prix}</td>
				<td>${p.poids}</td>
				<td>
					<a href="/edit/${p.id}">edition</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/create">nouveau produit</a>
</body>
</html>