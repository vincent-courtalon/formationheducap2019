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
<title>Edition des genres d'un livre</title>
</head>
<body>
	<div class="container">
		<h2 class="text-center">ajouter/retirer des genres au livre</h2>
	
		<h3>titre livre: ${livre.titre}</h3>
		<h4>ecrit par: ${livre.auteur.prenom} ${livre.auteur.nom}</h4>

		<div class="row">
			<div class="col-sm-6">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">genres déjà associés</h5>
						<h6 class="card-subtitle mb-2 text-muted">cliquer pour
							retirer</h6>
						<div class="card-text">
							<c:forEach items="${selected_genres}" var="sg">
								<form action="/livre/removeGenre" method="post">
									<input type="hidden" name="livreId" value="${livre.id}" />
									<input type="hidden" name="genreId" value="${sg.id}" />
									<input type="submit"
										   class="mb-2 btn btn-sm btn-primary btn-block"
										   value="${sg.libelle}" />
								</form>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">genres non associés</h5>
						<h6 class="card-subtitle mb-2 text-muted">cliquer pour ajouter</h6>
						<div class="card-text">
							<c:forEach items="${unselected_genres}" var="sg">
								<form action="/livre/addGenre" method="post">
									<input type="hidden" name="livreId" value="${livre.id}" />
									<input type="hidden" name="genreId" value="${sg.id}" />
									<input type="submit"
										   class="mb-2 btn btn-sm btn-primary btn-block"
										   value="${sg.libelle}" />
								</form>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
		<a class="btn btn-success btn-block mt-2" href="/">retour à la liste des livres</a>
	</div>
</body>
</html>