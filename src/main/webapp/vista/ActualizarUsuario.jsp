<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Actualizar Usuario</title>
</head>
<body>
    <h1>Actualizar Usuario</h1>

	<form action="./gestionarControlador" method="post">
        <input type="hidden" name="ruta" value="actualizarUsuario">
        
        <div style="margin-bottom: 10px;">
            <label>Correo (Identificador):</label>
            <input type="email" name="correo" value="${param.correo}" readonly>
        </div>
        <div style="margin-bottom: 10px;">
            <label>Nuevo Nombre:</label>
            <input type="text" name="nombre" required>
        </div>
		<div style="margin-bottom: 10px;">
            <label>Nuevo Perfil:</label>
            <select name="perfil" required style="width: 100%; padding: 3px;">
                <option value="" disabled selected>Seleccione una opción...</option>
                <option value="Admin">Admin</option>
                <option value="Empleado">Empleado</option>
            </select>
        </div>
        
        <button type="submit">Guardar Cambios</button>
		<a href="./gestionarControlador?ruta=ingresar">Cancelar</a>
    </form>
</body>
</html>