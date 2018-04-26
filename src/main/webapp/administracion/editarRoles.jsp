<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Portal OBIEE Administraci&oacute;n</title>
</head>
<body>
	<center>
		<h1>Configuraci&oacute;n de Roles</h1>
        <h2>
        	<!-- <a href="new">Agregar Configuraci&oacute;n</a>
        	&nbsp;&nbsp;&nbsp;-->
        	<a href="configuracionroles?accion=">Listar configuraciones</a>
        	
        </h2>
	</center>
    <div align="center">
		<c:if test="${configuracionRol != null}">
			<form action=configuracionroles?accion=update method="post">
        </c:if>
        <c:if test="${configuracionRol == null}">
			<form action="configuracionroles?accion=insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${configuracionRol != null}">
            			Editar configuraci&oacute;n
            		</c:if>
            		<c:if test="${configuracionRol == null}">
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
                			value="<c:out value='${configuracionRol.idConfigrol}'/>"
                		/>
                </td>
            </tr>
            <tr>
                <th>Nombre: </th>
                <td>
                	<input type="text" name="nombre" size=50"
                			value="<c:out value='${configuracionRol.nombre}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Par&aacute;metro: </th>
                <td>
                	<input type="text" name="parametro" size="50"
                			value="<c:out value='${configuracionRol.parametro}' />"
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
</body>
</html>