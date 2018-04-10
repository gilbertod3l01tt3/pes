<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Portal OBIEE</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>  	
  	    <script>
			function setURL(url){
			    document.getElementById('ventanaReporte').src = url;
			}
		</script>
    </head>
	<body>  
		<div class="row">
    		<div class="col-sm-3">
      			<div class="panel panel-primary">
      				<div class="panel-heading">Selecci&oacute;n de Reportes</div>
      				<div class="panel-body">
      					<h3>Bienvenido: <strong>${userInfo.givenName}</strong></h3>
      					<h4>Rol:<strong>${rol}</strong></h4>
      					<p><strong>Seleccione el reporte que desea visualizar.</strong></p>
      					<select>
      						<option value="0">Selecione una opci&oacute;n</option>
      						<option value="123">Reporte de Integraci&oacute;n</option>
      					</select>
      					     					
						<!-- <a href='http://172.31.10.150:9502/analytics/saw.dll?dashboard&PortalPath=%2Fshared%2FCarpeta%20de%20Pruebas%2F_portal%2FPanelPruebaIntegracion&Page=Estados&Action=Print&NQUser=usr_Estado&NQPassword=usr_3st4d0' target="ventanaReporte">Reporte</a>-->
						<input type="button" class="btn btn-primary" onclick="setURL('http://172.31.10.150:9502/analytics/saw.dll?dashboard&PortalPath=%2Fshared%2FCarpeta%20de%20Pruebas%2F_portal%2FPanelPruebaIntegracion&Page=Estados&Action=Print&NQUser=usr_Estado&NQPassword=usr_3st4d0')" value="Consultar"/>						
					</div>      					
    			</div>
    		</div>
    
    		<div class="col-sm-9">      			
      			<div class="panel panel-primary">
      				<div class="panel-heading">Reporte Generado</div>
      				<div class="panel-body">
      					<!-- <a href='http://172.31.10.150:9502/analytics/saw.dll?dashboard&PortalPath=%2Fshared%2FCarpeta%20de%20Pruebas%2F_portal%2FPanelPruebaIntegracion&Page=Estados&Action=Print&NQUser=usr_Estado&NQPassword=usr_3st4d0'>Reporte</a> 
      					<iframe name="ventanaReporte" src="http://www.bing.com/"></iframe>-->
      					<iframe id="ventanaReporte"></iframe>
						<!-- <object type="text/html" data="http://172.31.10.150:9502/analytics/saw.dll?dashboard&PortalPath=%2Fshared%2FCarpeta%20de%20Pruebas%2F_portal%2FPanelPruebaIntegracion&Page=Estados&Action=Print&NQUser=usr_Estado&NQPassword=usr_3st4d0" width="900" height="900"></object>-->       					
					</div>
      			</div>
    		</div>
  		</div>
     </body>
</html>
