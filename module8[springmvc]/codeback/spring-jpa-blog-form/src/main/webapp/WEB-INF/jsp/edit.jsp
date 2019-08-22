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
<title>Edition post</title>
</head>
<body>
	<div class="container">
		<h2>edition Post</h2>
		<form action="/save" method="post">

			<input type="hidden" name="id" id="id" value="${post.id}" />
			<div class="from-group">
				<label for="titre">titre</label> <input type="text"
					class="form-control" name="titre" id="titre" value="${post.titre}" />
			</div>

			<div class="from-group">
				<label for="corps">contenu post</label> 
				<textarea cols="100" rows="5" class="form-control" 
					name="corps" id="corps">${post.corps}</textarea>
			</div>

			<div class="from-group">
				<label for="auteur">auteur</label> <input type="text"
					class="form-control" name="auteur" id="auteur"
					value="${post.auteur}" /><br />
			</div>
			<input type="submit" class="btn btn-success btn-block" value="sauvegarder" />
		</form>
	</div>
</body>
</html>