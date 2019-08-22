<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<title>LA FNAK</title>
</head>
<body>
	<div class="container">
		<h2 class="text-center">La Fnak</h2>
		<div class="row">
			<table class="table table-hover table-bordered">
				<thead class="thead-dark">
					<tr>
						<th>TITRE</th>
						<th>ISBN</th>
						<th>NB PAGES</th>
						<th>AUTEUR</th>
						<th>GENRES</th>
						<th>ACTIONS</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${livres}" var="livre">
						<tr>
							<td>${livre.titre}</td>
							<td>${livre.isbn}</td>
							<td>${livre.nbPages}</td>
							<td>${livre.auteur.nom} ${livre.auteur.prenom}</td>
							<td>
								<c:forEach items="${livre.genres}" var="genre" varStatus="v">
									<c:if test="${v.last}">
										${genre.libelle}
									</c:if>
									<c:if test="${not v.last}">
										${genre.libelle},
									</c:if>
								</c:forEach>
							</td>
							<td>
								<a class="btn btn-success btn-block" href="/livre/${livre.id}/editgenres">etiquetage</a>
								<a class="btn btn-primary btn-block" href="/edit/${livre.id}">edition</a>
								<form action="/delete" method="post">
									<input type="hidden" name="delete_id" id="delete_id" value="${livre.id}" />
									<input class="btn btn-danger btn-block mt-2" type="submit" value="supprimer" />
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a class="btn btn-primary" href="/create">creation nouveau livre</a>
		</div>
	</div>
</body>
</html>