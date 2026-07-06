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
	
	<c:if test="${not empty sessionScope.mensajeError}">
    <div style="color: white; background-color: #ff4d4d; padding: 10px; border-radius: 5px; margin-bottom: 15px;">
        ${sessionScope.mensajeError}
    </div>
    <c:remove var="mensajeError" scope="session" />
	</c:if>
	
	<form method="POST" action="${pageContext.request.contextPath}/GestionarUsuariosController?ruta=guardarNuevo">

		<input type="hidden" name="Id" id="Id" /> 
		<label for="Nombre">Nombre :</label> 
		<input type="text" name="Nombre" id="Nombre" /> 
		<label for="Clave">Clave :</label> 
		<input type="text" name="Clave" id="Clave" /> 
		<label for="Clave">Correo :</label> 
		<input type="text" name="Correo" id="Correo" /> 
		<label for="Rol">Es administrador? :</label> 
		<input type="checkbox" name="Rol" id="Rol" value="true" />
		<br><br> 
		<input type="submit" value="Guardar" />

	</form>

</body>
</html>