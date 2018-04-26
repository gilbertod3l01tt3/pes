<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Portal Obiee - Administraci&oacute;n</title>
</head>
<body>
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
        <table border="1" cellpadding="5">
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
</body>
</html>
