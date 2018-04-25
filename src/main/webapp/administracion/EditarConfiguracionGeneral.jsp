<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Portal OBIEE Administraci&oacute;n</title>
</head>
<body>
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
        <table border="1" cellpadding="5">
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
                	<input type="text" name="id" size="10"
                			value="<c:out value='${configuracion.idConfigobiee}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Par&aacute;metro: </th>
                <td>
                	<input type="text" name="parametro" size="45"
                			value="<c:out value='${configuracion.parametro}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Valor: </th>
                <td>
                	<input type="text" name="valor" size="45"
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
</body>
</html>