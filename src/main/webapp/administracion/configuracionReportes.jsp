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
		<h1>Configuraci&oacute;n de Reportes</h1>
        <h2>
        	<a href="administracion">Regresar a m&oacute;dulo de administraci&oacute;n</a>
        </h2>
        <h2>
        	<a href="configuracionreportes?accion=new">Agregar nuevo reporte</a>
        </h2>
	</center>
    <div align="center">
        <table class="table table-hover">
            <caption><h2>Lista de Reportes</h2></caption>
            <tr>
                <th>ID</th>
                <th>NOMBRE</th>
                <th>ESTATUS</th>
                <th>PARAMETRO</th>
                <th>PATH</th>
                <th>PANEL</th>
            </tr>
            <c:forEach var="configuracionReporte" items="${listaConfiguracionesReportes}">
                <tr>
                    <td><c:out value="${configuracionReporte.idConfigreporte}" /></td>
                    <td><c:out value="${configuracionReporte.nombre}" /></td>
                    <td><c:out value="${configuracionReporte.estatus}" /></td>
                    <td><c:out value="${configuracionReporte.parametro}" /></td>
                    <td><c:out value="${configuracionReporte.path}" /></td>
                    <td><c:out value="${configuracionReporte.panel}" /></td>
                    <td>
                    	<a href="configuracionreportes?accion=edit&id=<c:out value='${configuracionReporte.idConfigreporte}' />">Editar</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="configuracionreportes?accion=delete&id=<c:out value='${configuracionReporte.idConfigreporte}' />">Borrar</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </div>	
</body>
</html>
