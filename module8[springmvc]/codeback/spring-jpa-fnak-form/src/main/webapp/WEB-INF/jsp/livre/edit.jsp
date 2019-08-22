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
<title>Edition Livre</title>
</head>
<body>
	<div class="container">
		<h2>edition livre</h2>
		<form action="/save" method="post">

			<input type="hidden" name="id" id="id" value="${livre.id}" />
			<div class="from-group">
				<label for="titre">titre</label> <input type="text"
					class="form-control" name="titre" id="titre" value="${livre.titre}" /><br />
			</div>

			<div class="from-group">
				<label for="nbPages">nombre de pages</label> <input type="text"
					class="form-control" name="nbPages" id="nbPages"
					value="${livre.nbPages}" /><br />
			</div>

			<div class="from-group">
				<label for="isbn">isbn</label> <input type="text"
					class="form-control" name="isbn" id="isbn" value="${livre.isbn}" /><br />
			</div>

			<div class="from-group">
				<label for="auteur">auteur</label> 
				<select class="form-control" name="auteurId" id="auteurId">
					<c:forEach items="${auteurs}" var="a">
						<c:if test="${a.id ==  livre.auteur.id}">
							<option value="${a.id}" selected >${a.nom} ${a.prenom}</option>
						</c:if>
						<c:if test="${a.id !=  livre.auteur.id}">
							<option value="${a.id}">${a.nom} ${a.prenom}</option>
						</c:if>
					</c:forEach>
				</select>				
			</div>
			<input type="submit" class="btn btn-success btn-block" value="sauvegarder" />
		</form>
	</div>
</body>
</html>