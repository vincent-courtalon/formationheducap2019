<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>liste des jeux</title>
</head>
<body>
	<h2>MacroMania</h2>
	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>TITRE</th>
				<th>DESCRIPTION</th>
				<th>PLATE FORME</th>
				<th>ANNEE</th>
				<th>EDITION</th>
				<th>SUPRESSION</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.liste_jeux}" var="jeux" >
				<tr>
					<td><c:out value="${jeux.id}"></c:out></td>
					<td><c:out value="${jeux.titre}"></c:out></td>
					<td><c:out value="${jeux.description}"></c:out></td>
					<td>
						<a href="/macromania/JeuxHomeServlet/<c:out value='${jeux.plateforme}'></c:out>">
							<c:out value="${jeux.plateforme}"></c:out>
						</a>
					</td>
					<td><c:out value="${jeux.annee}"></c:out></td>
					<td><a href="/macromania/JeuxEditServlet/<c:out value='${jeux.id}'></c:out>" >editer</a></td>
					<td>
						<form method="post">
							<input type="hidden" name="supprId" value="<c:out value='${jeux.id}'></c:out>" />
							<input type="submit" value="supprimer" />
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/macromania/JeuxHomeServlet/">revenir a la liste complete</a>
	<a href="/macromania/JeuxEditServlet/0">creer nouveau jeux</a>
</body>
</html>