<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Portal Obiee - Administraci&oacute;n</title>
</head>
<body>
	<center>
		<h1>Configuraci&oacute;n de par&aacute;metros en general</h1>
        <h2>
        	<a href="administracion">Regresar a m&oacute;dulo de administraci&oacute;n</a>
        </h2>
        <h2>
        	<a href="configuracion?accion=new">Agregar nuevo par&aacute;metro</a>
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de Par&aacute;metros</h2></caption>
            <tr>
                <th>ID</th>
                <th>PARAMETRO</th>
                <th>VALOR</th>
            </tr>
            <c:forEach var="configuracion" items="${listaConfiguraciones}">
                <tr>
                    <td><c:out value="${configuracion.idConfigobiee}" /></td>
                    <td><c:out value="${configuracion.parametro}" /></td>
                    <td><c:out value="${configuracion.valor}" /></td>
                    <td>
                    	<a href="configuracion?accion=edit&id=<c:out value='${configuracion.idConfigobiee}' />">Editar</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="configuracion?accion=delete&id=<c:out value='${configuracion.idConfigobiee}' />">Borrar</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
