<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nuevo Usuario</title>
</head>
<body>
    <h1>Crear Nuevo Usuario</h1>

    <c:if test="${not empty mensajeError}">
        <p style="color: red;"><strong>${mensajeError}</strong></p>
    </c:if>

	<form action="${pageContext.request.contextPath}/gestionarControlador" method="post">
        <input type="hidden" name="ruta" value="crearUsuario">
        
        <div style="margin-bottom: 10px;">
            <label>Nombre:</label>
            <input type="text" name="nombre" required>
        </div>
        <div style="margin-bottom: 10px;">
            <label>Correo Electrónico:</label>
            <input type="email" name="correo" required>
        </div>
        <div style="margin-bottom: 10px;">
            <label>Perfil (Rol):</label>
            <input type="text" name="rol" required>
        </div>
        
        <button type="submit">Guardar</button>
		<a href="${pageContext.request.contextPath}/gestionarControlador?ruta=ingresar">Cancelar</a>
    </form>
</body>
</html>