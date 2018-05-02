<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Portal OBIEE Administraci&oacute;n</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
	<center>
		<h1>Configuraci&oacute;n de par&aacute;metros en general</h1>
        <h2>
        	<!-- <a href="new">Agregar Configuraci&oacute;n</a>
        	&nbsp;&nbsp;&nbsp;-->
        	<a href="configuracion?accion=">Listar configuraciones</a>
        	
        </h2>
	</center>
    <div align="center">
		<c:if test="${configuracion != null}">
			<form action=configuracion?accion=update method="post">
        </c:if>
        <c:if test="${configuracion == null}">
			<form action="configuracion?accion=insert" method="post">
        </c:if>
        <table class="table table-hover">
            <caption>
            	<h2>
            		<c:if test="${configuracion != null}">
            			Editar configuraci&oacute;n
            		</c:if>
            		<c:if test="${configuracion == null}">
            			Agregar nueva configuraci&oacute;n
            		</c:if>
            	</h2>
            </caption>
        		<!--<c:if test="${configuracion != null}">
        			<input type="hidden" name="id" value="<c:out value='${configuracion.idConfigobiee}' />" />
        		</c:if>-->            
             <tr>
                <th>Identificador: </th>
                <td>
                	<input type="text" name="id" size="10" readonly="readonly"
                			value="<c:out value='${configuracion.idConfigobiee}'/>"
                		/>
                </td>
            </tr>
            <tr>
                <th>Par&aacute;metro: </th>
                <td>
                	<input type="text" name="parametro" size="50"
                			value="<c:out value='${configuracion.parametro}'/>"
                		/>
                </td>
            </tr>
            <tr>
                <th>Valor: </th>
                <td>
                	<input type="text" name="valor" size="50"
                			value="<c:out value='${configuracion.valor}' />"
                	/>
                </td>
            </tr>           
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Guardar" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
    </div>
</body>
</html>