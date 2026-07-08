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
	<c:if test="${not empty sessionScope.mensajeError}">
		<div
			style="color: white; background-color: #ff4d4d; padding: 10px; border-radius: 5px; margin-bottom: 15px;">
			${sessionScope.mensajeError}</div>
		<c:remove var="mensajeError" scope="session" />
	</c:if>

	<form method="POST"
		action="${pageContext.request.contextPath}/GestionarUsuariosController?ruta=guardarExistente">
		<input type="hidden" name="Id" id="Id" value="${persona.id}" /> <label
			for="Nombre">Nombre : </label> <input type="text" name="Nombre"
			id="Nombre" value="${persona.nombre}" /> <label for="Clave">Clave:</label>
		<input type="text" name="Clave" id="Clave" value="${persona.clave}" />
		<label for="Corre">Correo:</label> <input type="text" name="Correo"
			id="Correo" value="${persona.correo}" /> <label for="Rol">Rol
			(Administrador) :</label> <input type="checkbox" name="Rol" id="Rol"
			value="true" ${persona.admin ? 'checked' : ''} /> <br>
		<br> <input type="submit" value="Guardar" />
	</form>

</body>
</html>