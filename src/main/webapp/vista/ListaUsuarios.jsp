<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuarios</title>
</head>
<body>
    <h1>Gestión de Usuarios</h1>
    <div style="margin-bottom: 15px;">
        <a href="${pageContext.request.contextPath}/gestionarControlador?ruta=newUser">Crear usuario</a>
    </div>

    <table border="1" style="text-align: left; width: 50%;">
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Correo</th>
                <th>Perfil</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="u" items="${usuarios}">
                <tr>
                    <td>${u.nombre}</td>
                    <td>${u.correo}</td>
                    <td>${u.perfil}</td>
                    <td>
						<a href="${pageContext.request.contextPath}/gestionarControlador?ruta=update&correo=${u.correo}">Actualizar</a> | 
						<a href="${pageContext.request.contextPath}/gestionarControlador?ruta=delete&correo=${u.correo}">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>