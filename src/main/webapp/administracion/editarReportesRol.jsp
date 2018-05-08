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
		<h1>Configuraci&oacute;n de Reportes/Roles</h1>
        <h2>
        	<!-- <a href="new">Agregar Configuraci&oacute;n</a>
        	&nbsp;&nbsp;&nbsp;-->
        	<a href="configuracionreportesrol?accion=">Listar configuraciones</a>
        	
        </h2>
	</center>
    <div align="center">
		<c:if test="${configuracionReporteRol != null}">
			<form action=configuracionreportesrol?accion=update method="post">
        </c:if>
        <c:if test="${configuracionReporteRol == null}">
			<form action="configuracionreportesrol?accion=insert" method="post">
        </c:if>
        <table class="table table-hover">
            <caption>
            	<h2>
            		<c:if test="${configuracionReporteRol != null}">
            			Editar configuraci&oacute;n
            		</c:if>
            		<c:if test="${configuracionReporteRol == null}">
            			Agregar nueva configuraci&oacute;n
            		</c:if>
            	</h2>
            </caption>        
           	<c:if test="${configuracionReporteRol != null}">
            	<tr>
					<th>ID Rol: </th>
	                <td>
	                	<input type="text" name="idRol" size="10" value="<c:out value='${configuracionReporteRol.idRol}'/>" readonly="readonly"/>
	                </td>
	            </tr>
	            <tr>
	            	<th>ID Reporte: </th>
	                <td>
             	        <input type="text" name="idReporte" size="10" value="<c:out value='${configuracionReporteRol.idReporte}'/>" readonly="readonly"/>
	                </td>
	            </tr>
	        </c:if>
	        <c:if test="${configuracionReporteRol == null}">
	        	<tr>
					<th>Roles: </th>
	                <td>
					<select name="idRol" style="width:98%">
						<c:forEach items="${configuracionRoles}" var="rol">
							<option value="<c:out value='${rol.idConfigrol}'/>"><c:out value='${rol.nombre}'/></option>
						</c:forEach>
					</select>
	                </td>
                </tr>
	        	<tr>
					<th>Reportes: </th>
	                <td>
					<select name="idReporte" style="width:98%">
						<c:forEach items="${configuracionReportes}" var="reporte">
							<option value="<c:out value='${reporte.idConfigreporte}'/>"><c:out value='${reporte.nombre}'/></option>
						</c:forEach>
					</select>
	                </td>
                </tr>
	        </c:if>
                
            
            <tr>
                <th>Nombre: </th>
                <td>
                	<input type="text" name="nombre" size="50" value="<c:out value='${configuracionReporteRol.nombreDespliegue}' />" />
                </td>
            </tr>
            <tr>
                <th>Orden: </th>
                <td>
                	<input type="number" name="orden" min="0" size="10" value="0"/>
                </td>
            </tr>    
            <tr>
                <th>Mandatorios: </th>
                <td>
                	<input type="text" name="mandatorios" size="50" value="<c:out value='${configuracionReporteRol.parametrosMandatorios}' />"/>
                </td>
            </tr>
            <tr>
                <th>Opcionales: </th>
                <td>
                	<input type="text" name="opcionales" size="50" value="<c:out value='${configuracionReporteRol.parametrosOpcionales}' />"/>
                </td>
            </tr>
            <tr>
                <th>Nulos: </th>
                <td>
                	<input type="text" name="nulos" size="50" value="<c:out value='${configuracionReporteRol.parametrosNulos}' />"/>
                </td>
            </tr>
            <tr>
                <th>P&aacute;gina: </th>
                <td>
                	<input type="text" name="pagina" size="50" value="<c:out value='${configuracionReporteRol.pagina}' />"/>
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