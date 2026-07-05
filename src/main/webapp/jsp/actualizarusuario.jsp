<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Actualizar Usuario</title>
</head>
<body>

	<h1>Actualizar Usuario</h1>

	<form method="POST" action="GestionarUsuariosController?ruta=guardarExistente">
		<input type="hidden" name="txtId" id="txtId" value="${persona.id}" /> 
		<label for="txtNombre">Nombre : </label> 
		<input type="text" name="txtNombre" id="txtNombre" value="${persona.nombre}"/> 
		<label for="txtClave">Clave:</label> 
		<input type="text" name="txtClave" id="txtClave" value="${persona.clave}"/> 
		<br><br> 
		<input type="submit" value="Guardar" />
	</form>

</body>
</html>