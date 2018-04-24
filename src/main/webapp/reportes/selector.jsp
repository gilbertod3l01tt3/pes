<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Portal OBIEE / Administraci&oacute;n</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>  	
  	    
		<script>
			$(document).ready(function() {
				$('#roles').click(function(event) {					
					$.get('configuracion/adminRoles', {
					}, function(responseText) {
						$('#selector').html(responseText);
					});
				});
			});
			
			$(document).ready(function() {
				$('#reportes').click(function(event) {					
					$.get('configuracion/adminReportes', {
					}, function(responseText) {
						$('#selector').html(responseText);
					});
				});
			});
			$(document).ready(function() {
				$('#config').click(function(event) {					
					$.get('configuracion?accion=', {
					}, function(responseText) {
						$('#selector').html(responseText);
					});
				});
			});
			$(document).ready(function() {
				$('#asociacion').click(function(event) {					
					$.get('configuracion/adminAsociacion', {
					}, function(responseText) {
						$('#selector').html(responseText);
					});
				});
			});
		</script>
    </head>
	<body>  
	<div class="container">
	</br>
	</br>
	</br>
		<div class="row">
		  <div class="col-xs-6 col-md-6">
		  	<input type="button" id="roles" class="btn btn-primary btn-lg btn-block" onclick="" value="Configurar Roles"/>
		  </div>
		  <div class="col-xs-6 col-md-6">
		    <input type="button" id="reportes" class="btn btn-secondary btn-lg btn-block" onclick="" value="Configurar Reportes"/>			
		  </div>
		</div>
		<div class="row">
		  <div class="col-xs-6 col-md-6">
		  	<input type="button" id="config" class="btn btn-info btn-lg btn-block" onclick="" value="Configurar Par&aacute;metros en general"/>
		  </div>
		  <div class="col-xs-6 col-md-6">
		    <input type="button" id="asociacion" class="btn btn-warning btn-lg btn-block" onclick="" value="Vincular Reportes a Rol"/>			
		  </div>
		</div>
	</div>
	</body>
</html>
