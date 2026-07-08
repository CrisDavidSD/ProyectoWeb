<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<a
			href="${pageContext.request.contextPath}/GestionarUsuariosController?ruta=nuevo">Nuevo</a>
	</div>

	<c:if test="${not empty sessionScope.mensajeError}">
		<div
			style="color: white; background-color: #ff4d4d; padding: 10px; border-radius: 5px; margin-bottom: 15px;">
			${sessionScope.mensajeError}</div>
		<c:remove var="mensajeError" scope="session" />
	</c:if>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Contraseña</th>
				<th>Correo</th>
				<th>Rol</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="persona" items="${personas}">
				<tr>
					<td>${persona.id}</td>
					<td>${persona.nombre}</td>
					<td>${persona.clave}</td>
					<td>${persona.correo}</td>
					<td>${persona.admin ? 'Administrador' : 'Usuario normal'}</td>
					<td><a
						href="${pageContext.request.contextPath}/GestionarUsuariosController?ruta=actualizar&idpersona=${persona.id}">Actualizar</a>
						| <a
						href="${pageContext.request.contextPath}/GestionarUsuariosController?ruta=eliminar&idpersona=${persona.id}">Eliminar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


</body>
</html>