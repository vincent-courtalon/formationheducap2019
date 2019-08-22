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
<title>Mon super blog</title>
</head>
<body>
	<div class="container">
		<h2 class="text-center">mon super blog</h2>
		<div class="row">	
			<form class="form-inline" action="/search" method="get">
				<div class="form-group">
					<label for="searchTerm">recherche par auteur</label>
					<input type="text" name="searchTerm" id="searchTerm" class="form-control ml-2" />
					<input class="btn btn-success ml-2" type="submit" value="chercher" />
				</div>
			</form>
			<a class="btn btn-primary ml-2" href="/">retour a la liste complete</a>
		</div>
		<div class="row mt-3">
			<c:forEach items="${posts}" var="p">
				<div class="col-sm-4">
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">${p.titre}</h5>
							<h6 class="card-subtitle mb-2 text-muted">posté le ${p.formatedDateCreation}</h6>
							<p class="card-text">${p.corps}</p>
						</div>
						<div class="card-footer">
							<h6>écrit par ${p.auteur}</h6>
							<a class="btn btn-primary float-left ml-2" href="/edit/${p.id}">edition</a>
							<form class="float-left ml-2" action="/delete" method="post">
								<input type="hidden" name="delete_id" value="${p.id}"/>
								<input type="submit" class="btn btn-danger" value="suprresion"/>
							</form>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="row mt-2">
			<a class="btn btn-primary" href="/create">creation</a>
		</div>
	</div>
</body>
</html>