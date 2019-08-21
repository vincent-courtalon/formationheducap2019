<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<title>${nomApplication}</title>
</head>
<body>
	<div class="container">
	<h2  class="text-center">${nomApplication}</h2>
	<form class="form-inline" action="/search_by_nom" method="get">
		<div class="form-group">
			<label for="searchTerm">chercher par nom</label>
			<input type="text" class="form-control ml-3" name="searchTerm" id="searchTerm" />
			<input type="submit" class="btn btn-success ml-3" value="chercher" />
		</div>
	</form>
	<a class="btn btn-warning btn-block mt-2 mb-2" href="/">revenir a la liste générale</a>
	<table class="table table-striped">
		<thead class="thead-dark">
			<tr>
				<th>ID</th>
				<th>NOM</th>
				<th>POPULATION</th>
				<th>SURFACE</th>
				<th>PAYS</th>
				<th>ACTIONS</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${villes}" var="v" >
			<tr>
				<td>${v.id}</td>
				<td>${v.nom}</td>
				<td>${v.population} habitants</td>
				<td>${v.surface} m2</td>
				<td>${v.pays}</td>
				<td>
					<a class="btn btn-primary" href="/edit/${v.id}">edition</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<a class="btn btn-primary" href="/create">nouvelle ville</a>
	</div>
</body>
</html>