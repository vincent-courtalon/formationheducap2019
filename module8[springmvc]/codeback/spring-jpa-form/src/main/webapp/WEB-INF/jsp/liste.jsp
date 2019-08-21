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
<title>Liste livres</title>
</head>
<body>
	<div class="container">
		<h2>les livres</h2>
		<table class="table table-striped">
			<thead class="thead-dark">
				<tr>
					<th>ID</th>
					<th>TITRE</th>
					<th>NB PAGES</th>
					<th>ISBN</th>
					<th>AUTEUR</th>
					<th>ACTIONS</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${livres}" var="l">
					<tr>
						<td>${l.id}</td>
						<td>${l.titre}</td>
						<td>${l.nbPages}pages</td>
						<td>${l.isbn}</td>
						<td>${l.auteur}</td>
						<td>
							<a class="btn btn-primary" href="/edit/${l.id}">edition livre</a>
							<form action="/delete" method="post">
								<input type="hidden" name="delete_id" id="delete_id" value="${l.id}" />
								<input type="submit" value="effacer" class="btn btn-danger" />
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a class="btn btn-primary" href="/create">creation livre</a>
	</div>
</body>
</html>