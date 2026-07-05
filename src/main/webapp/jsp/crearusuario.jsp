<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ingresar Usuario</title>
</head>
<body>

	<h1>Nuevo usuario</h1>
	<form method="POST" action="../GestionarUsuariosController?ruta=guardarNuevo">

		<input type="hidden" name="txtId" id="txtId" /> 
		<label for="txtNombre">Nombre :</label> 
		<input type="text" name="txtNombre" id="txtNombre" /> 
		<label for="txtClave">Clave :</label> 
		<input type="text" name="txtClave" id="txtClave" /> 
		<br><br> 
		<input type="submit" value="Guardar" />

	</form>

</body>
</html>