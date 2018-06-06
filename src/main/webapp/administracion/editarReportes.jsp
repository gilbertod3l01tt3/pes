<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Portal OBIEE Administraci&oacute;n</title>
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
        	<!-- <a href="new">Agregar Configuraci&oacute;n</a>
        	&nbsp;&nbsp;&nbsp;-->
        	<a href="configuracionreportes?accion=">Listar configuraciones</a>
        	
        </h2>
	</center>
    <div align="center">
		<c:if test="${configuracionReporte != null}">
			<form action=configuracionreportes?accion=update method="post">
        </c:if>
        <c:if test="${configuracionReporte == null}">
			<form action="configuracionreportes?accion=insert" method="post">
        </c:if>
        <table class="table table-hover">
            <caption>
            	<h2>
            		<c:if test="${configuracionReporte != null}">
            			Editar configuraci&oacute;n
            		</c:if>
            		<c:if test="${configuracionReporte == null}">
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
                			value="<c:out value='${configuracionReporte.idConfigreporte}'/>"
                	/>
                </td>
            </tr>
            <tr>
                <th>Nombre: </th>
                <td>
                	<input type="text" name="nombre" size=50"
                			value="<c:out value='${configuracionReporte.nombre}' />"
                	/>
                </td>
            </tr>
              <tr>
                <th>Estatus: </th>
                <td>
                	<input type="text" name="estatus" size="50"
                			value="<c:out value='${configuracionReporte.estatus}' />"
                		/>
                </td>
            </tr>    
            <tr>
                <th>Par&aacute;metro: </th>
                <td>
                	<input type="text" name="parametro" size="50"
                			value="<c:out value='${configuracionReporte.parametro}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Path: </th>
                <td>
                	<input type="text" name="path" size="50"
                			value="<c:out value='${configuracionReporte.path}' />"
                		/>
                </td>
            </tr>   
            <tr>
                <th>Panel: </th>
                <td>
                	<input type="text" name="panel" size="50"
                			value="<c:out value='${configuracionReporte.panel}' />"
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