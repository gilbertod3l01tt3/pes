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
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">    
  		<link rel="stylesheet" href="css/styles.css">
  		<script src="js/jquery-3.3.1.min.js"></script>
  		<script src="js/bootstrap.min.js"></script>  
  		<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
  		
    </head>
	<body>
        <div class="wrapper">
            <!-- Sidebar Holder -->
            <nav id="sidebar">
                <div id="dismiss">
                    <i class="glyphicon glyphicon-arrow-left"></i>
                </div>
                <div class="sidebar-header">
                    <h3>Selecci&oacute;n de Reportes</h3>                    
                </div>
                <div id="rolDiv">
                	<strong>Rol:</strong>
			    	<select id="rol">
			    		<option value="0">Seleccione un rol</option>
			    		<c:forEach var="rol" items="${roles}">
    						<option value="${rol}">${rol}</option>
    					</c:forEach>
					</select>
                </div>
				<div id="reportes"></div>
				<div id="selectores"></div>
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
					    <span  class="navbar-text">
					    	Rol: <strong id="selected_rol">Sin seleccionar</strong>
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
        			var a = "${fn:length(roles)}";
        			if(a > 1){
		           		$('#rol').on('change',function(){  
		           			var rolVar = $('#rol').val();
		           			if(rolVar == 0){
		           				$("#selected_rol").html("Sin seleccionar");
		           				$('#reportes').html("");
		           			}else{
		           				$("#selected_rol").html(rolVar);
		           			}
		           			$("#ventanaReporte").attr("src","");
			            		           		
			           		// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			       			$.post('generaOpcionesReportes', {
			       				rol : rolVar
			       			}, function(responseText) {
			       				$('#reportes').html(responseText);
			       			});
		           		});
        			}else{
        				var rolVar =  "${roles[0]}";
        				if(rolVar == "Administrador"){
        					window.location.href = 'administracion';
        				}else{
	        				$("#rolDiv").html("");
	        				$("#selected_rol").html(rolVar);
	        				$.post('generaOpcionesReportes', {
			       				rol : rolVar
			       			}, function(responseText) {
			       				$('#reportes').html(responseText);
			       			});
        				}
        			}
        			$('#reportes').on('change',"#option",function(){  
        				$('#selectores').html("");
	           			var reporteVar = $('#option').val();
	           			var rolVar = $("#selected_rol").html();
		           		// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
		       			$.post('generaSelectores', {
		       				reporte : reporteVar,
		       				rol : rolVar
		       			}, function(responseText) {
		       				$('#selectores').html(responseText);
		       			});
	           		});
        			
			       	 $('#selectores').on('click','#consultar',function(){
			       		var reporteVar = $('#option').val();
			       		var rolVar = $("#selected_rol").html();
			       		xhr = new XMLHttpRequest();
				        xhr.open("POST", "generaUrlDinamica", true);
				        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
				       	var datosJSON = {rol : rolVar, reporte : reporteVar, '"Ubicacion"."Entidad federativa"':"Aguascalientes"};
				        //alert(JSON.stringify(datosJSON));
				        xhr.send(JSON.stringify(datosJSON));
				        xhr.onload = function () {
				            if (xhr.readyState === xhr.DONE) {
				                if (xhr.status === 200) {
				                    //alert(xhr.response);
				                    $('#ventanaReporte').attr('src',xhr.responseText);
				                }
				            }
				        };
			       		$('#sidebar').removeClass('active');
			            $('.overlay').fadeOut(1000);
			       	 });
			       	 
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
