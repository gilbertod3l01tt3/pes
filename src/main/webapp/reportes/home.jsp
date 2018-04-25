<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
					document.getElementById('ventanaReporte').src = "http://172.31.10.150:9502/analytics/saw.dll?dashboard&PortalPath=%2Fshared%2FCarpeta%20de%20Pruebas%2F_portal%2FPanelPruebaIntegracion&Page=EstadosSIN&Action=Print&NQUser=usr_Estado&NQPassword=usr_3st4d0";
				}
				if(agent_id == 2){
			    	document.getElementById('ventanaReporte').src = "http://172.31.10.150:9502/analytics/saw.dll?dashboard&PortalPath=%2Fshared%2FCarpeta%20de%20Pruebas%2F_portal%2FPanelPruebaIntegracion&Page=Estados&Action=Print&NQUser=usr_Estado&NQPassword=usr_3st4d0";
				}
				
			}
		</script>
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

                <p><strong>Seleccione el reporte que desea visualizar.</strong></p>
      					<select id="option">
      						<option value="0">Seleccione una opci&oacute;n</option>
      						<option value="1">Reporte</option>
      						<option value="2">Tablero</option>
      					</select>
      					     					
						<!-- <a href='http://172.31.10.150:9502/analytics/saw.dll?dashboard&PortalPath=%2Fshared%2FCarpeta%20de%20Pruebas%2F_portal%2FPanelPruebaIntegracion&Page=Estados&Action=Print&NQUser=usr_Estado&NQPassword=usr_3st4d0' target="ventanaReporte">Reporte</a>-->
						<input type="button" class="btn btn-primary" onclick="setURL()" value="Consultar"/>
            </nav>

            <!-- Page Content Holder -->
            <div id="content">

                <nav class="navbar navbar-default">
                    <div class="container-fluid">

                        <div class="navbar-header">
                            <button type="button" id="sidebarCollapse" class="btn btn-info navbar-btn">
                                <i class="glyphicon glyphicon-align-left"></i>
                                <span>Buscar</span>
                            </button>
                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                           <h3>Bienvenido: <strong>${userInfo.givenName} ${userInfo.familyName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rol:<strong>${rol}</strong></strong></h3>
						</div>
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
