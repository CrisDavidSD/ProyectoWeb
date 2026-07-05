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
		<a href="GestionarUsuariosController?ruta=nuevo">Nuevo</a>
	</div>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Contraseña</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="persona" items="${personas}">
				<tr>
					<td>${persona.id}</td>
					<td>${persona.nombre}</td>
					<td>${persona.clave}</td>
					<td><a href="GestionarUsuariosController?ruta=actualizar&idpersona=${persona.id}">Actualizar</a> | 
					<a href="GestionarUsuariosController?ruta=eliminar&idpersona=${persona.id}">Eliminar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


</body>
</html>