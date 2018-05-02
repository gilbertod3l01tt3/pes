<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Portal Obiee - Administraci&oacute;n</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
	<center>
		<h1>Configuraci&oacute;n de Roles</h1>
        <h2>
        	<a href="administracion">Regresar a m&oacute;dulo de administraci&oacute;n</a>
        </h2>
        <h2>
        	<a href="configuracionroles?accion=new">Agregar nuevo rol</a>
        </h2>
	</center>
    <div align="center">
        <table class="table table-hover">
            <caption><h2>Lista de Roles</h2></caption>
            <tr>
                <th>ID</th>
                <th>NOMBRE</th>
                <th>PARAMETRO</th>
            </tr>
            <c:forEach var="configuracionRol" items="${listaConfiguracionesRoles}">
                <tr>
                    <td><c:out value="${configuracionRol.idConfigrol}" /></td>
                    <td><c:out value="${configuracionRol.nombre}" /></td>
                    <td><c:out value="${configuracionRol.parametro}" /></td>
                    <td>
                    	<a href="configuracionroles?accion=edit&id=<c:out value='${configuracionRol.idConfigrol}' />">Editar</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="configuracionroles?accion=delete&id=<c:out value='${configuracionRol.idConfigrol}' />">Borrar</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
    </div>
    
</body>
</html>
