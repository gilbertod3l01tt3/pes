<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Portal OBIEE</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
    
        
  		<link rel="stylesheet" href="css/styles.css">
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>  
  		<script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>
  		
  			
  	    <script>
			function setURL(){
				var select = document.getElementById('option');
				var agent_id = select.options[select.selectedIndex].value;
				if(agent_id == 1){
					document.getElementById('ventanaReporte').src = "http://172.31.10.150:9502/analytics/saw.dll?dashboard&PortalPath=shared/Carpeta de Pruebas/_portal/PanelPruebaIntegracion&Page=EstadosSIN&Action=Print&NQUser=usr_Estado&NQPassword=usr_3st4d0";
				}
				if(agent_id == 2){
			    	document.getElementById('ventanaReporte').src = "http://172.31.10.150:9502/analytics/saw.dll?dashboard&PortalPath=shared/Carpeta de Pruebas/_portal/PanelPruebaIntegracion&Page=Estados&Action=Print&NQUser=usr_Estado&NQPassword=usr_3st4d0";
				}
				
			}
		</script>
		<style>
			.input_debug{
				display:none;
			}
		</style>
    </head>
	<body>

        <div class="wrapper">
            <!-- Sidebar Holder -->
            <nav id="sidebar">
                <div id="dismiss">
                    <i class="glyphicon glyphicon-arrow-left"></i>
                </div>

                <div class="sidebar-header">
                    <h3>Seleccion de Reportes</h3>
                </div>
				<div id="reportes">
					<input id="consultar" type="button" class="btn btn-primary" onclick="setURL()" value="Consultar"/>
				</div>
                
						
            </nav>

            <!-- Page Content Holder -->
            <div id="content">
				
                <nav class="navbar navbar-light">
                    <div class="container-fluid">

                        <div class="navbar-header">
                            <button type="button" id="sidebarCollapse" class="btn btn-info navbar-btn">
                                <i class="glyphicon glyphicon-align-left"></i>
                                <span>Buscar</span>
                            </button>
                        </div>
                        <span class="navbar-text">
					    	Bienvenido: <strong>${userInfo.givenName} ${userInfo.familyName}</strong>                			 
					    </span>
					    <span class="navbar-text">
					    	Rol:
					    	<select id="rol">
					    	<option value="0">Seleccione un rol</option>
					    	<c:forEach var="rol" items="${roles}">
      							<option value="${rol}">${rol}</option>
      						</c:forEach>
      					</select>
						   										       
						   		
					    </span>
                    </div>
                </nav>					

                <div class="embed-responsive embed-responsive-16by9">
      					<iframe class="embed-responsive-item" id="ventanaReporte"></iframe>
				</div>
				
            </div>
        </div>



        <div class="overlay"></div>

       	<script type="text/javascript">
            $(document).ready(function () {
            	 $('#rol').on('change',function(){                     
                     $("#ventanaReporte").attr("src","");
             			var rolVar = $('#rol').val();
             			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
             			$.post('generaOpcionesReportes', {
             				rol : rolVar
             			}, function(responseText) {
             				$('#reportes').html(responseText);
             			});
                });
            	 $('#consultar').on('click',function(){
            		 $('#sidebar').removeClass('active');
                     $('.overlay').fadeOut();
            	 })
                $("#sidebar").mCustomScrollbar({
                    theme: "minimal"
                });

                $('#dismiss, .overlay').on('click', function () {
                    $('#sidebar').removeClass('active');
                    $('.overlay').fadeOut();
                });

                $('#sidebarCollapse').on('click', function () {
                    $('#sidebar').addClass('active');
                    $('.overlay').fadeIn();
                    $('.collapse.in').toggleClass('in');
                    $('a[aria-expanded=true]').attr('aria-expanded', 'false');
                });
            });
        </script>    
        
    </body>
</html>
