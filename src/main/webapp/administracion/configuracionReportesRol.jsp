<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Portal Obiee - Administraci&oacute;n</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
	<center>
		<h1>Configuraci&oacute;n de Reportes/Roles</h1>
        <h2>
        	<a href="administracion">Regresar a m&oacute;dulo de administraci&oacute;n</a>
        </h2>
        <h2>
        	<a href="configuracionreportesrol?accion=new">Agregar nuevo registro</a>
        </h2>
	</center>
    <div align="center">
        <table class="table table-hover">
            <caption><h2>Lista de Roles</h2></caption>
            <tr>
                <th>ID_ROL</th>
                <th>ID_REPORTE</th>
                <th>NOMBRE</th>
                <th>ORDEN</th>
                <th>MANDATORIO</th>
                <th>OPCIONAL</th>
                <th>NULO</th>
                <th>PÁGINA</th>
                <th>EDICIÓN</th>
            </tr>
            <c:forEach var="configuracionRol" items="${listaConfiguracionesRoles}">
                <tr>
                    <td><c:out value="${configuracionRol.idRol}" /></td>
                    <td><c:out value="${configuracionRol.idReporte}" /></td>
                    <td><c:out value="${configuracionRol.nombreDespliegue}" /></td>
                    <td><c:out value="${configuracionRol.ordenDespliegue}" /></td>
                    <td><c:out value="${configuracionRol.parametrosMandatorios}" /></td>
                    <td><c:out value="${configuracionRol.parametrosOpcionales}" /></td>
                    <td><c:out value="${configuracionRol.parametrosNulos}" /></td>
                    <td><c:out value="${configuracionRol.pagina}" /></td>
                    <td>
                    	<a href="configuracionreportesrol?accion=edit&id=<c:out value='${configuracionRol.idRol}' />&idReporte=<c:out value='${configuracionRol.idReporte}' />">Editar</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="configuracionreportesrol?accion=delete&id=<c:out value='${configuracionRol.idRol}' />&idReporte=<c:out value='${configuracionRol.idReporte}' />">Borrar</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
    </div>    
</body>
</html>
