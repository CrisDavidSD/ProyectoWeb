<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Eliminar Usuario</title>
</head>
<body>
    <h1>Eliminar Usuario</h1>
    <p>¿Está seguro de que desea eliminar este usuario del sistema?</p>

	<form action="${pageContext.request.contextPath}/gestionarControlador" method="post">
        <input type="hidden" name="ruta" value="eliminarUsuario">
        
        <div style="margin-bottom: 10px;">
            <label>Correo a eliminar:</label>
            <input type="email" name="correo" value="${param.correo}" readonly style="border:none; background:transparent;">
        </div>
        
        <button type="submit" style="color: red;">Confirmar Eliminación</button>
		<a href="${pageContext.request.contextPath}/gestionarControlador?ruta=ingresar">Cancelar</a>
    </form>
</body>
</html>