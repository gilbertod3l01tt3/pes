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
				<input id="consultar" type="button" class="btn btn-primary" value="Consultar" style="display:hidden;"/>
            </nav>

            <!-- Page Content Holder -->
            <div id="content">
				
                <nav class="navbar navbar-light">
                    <div class="container-fluid">
						<c:forEach var="mandatorio" items="${mandatorios}">
    						<p>${mandatorio}</p>
    					</c:forEach>
                        <div class="navbar-header">
                            <button type="button" id="sidebarCollapse" class="btn btn-info navbar-btn">
                                <i class="glyphicon glyphicon-align-left"></i>
                                <span>Buscar</span>
                            </button>
                        </div>
                         <div style="float:right;">
                        	<button type="button" id="btnLogout" class="btn btn-warning navbar-btn">
                                <i class="glyphicon"></i>
                                <span>Logout</span>
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
        
        	$('#consultar').hide();
        
        	var n = "${fn:length(roles)}";
        	if(n > 1){
        		$('#rol').on('change',function(){  
        			var rolVar = $('#rol').val();
        			if(rolVar == 0){
        				$("#selected_rol").html("Sin seleccionar");
        				$('#reportes').html("");
        			}
       				$("#selected_rol").html(rolVar);
        			$("#ventanaReporte").attr("src","");
        			$('#reportes').html("");
        			$('#selectores').html("");
        			$('#consultar').hide();
        			
        	    		           		
        			$.post('generaOpcionesReportes', {
        				rol : rolVar
        			}, function(responseText) {
        				var html="<p><strong>Reporte a visualizar:</strong></p><select id=\"option\">";
        				html+="<option value=\"0\">Seleccione una opci&oacute;n</option>";
        				$.each(responseText, function (index, reporte) {
        	            	
        	            	html+="<option value="+reporte.idReporte+">"+reporte.nombreDespliegue+"</option>";
        	            });
        				html+="</select></br>";
        				$('#reportes').html(html);
        			}).fail(function(error){
        	   			console.log(error);
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
        				var html="<p><strong>Reporte a visualizar:</strong></p><select id=\"option\">";
        				html+="<option value=\"0\">Seleccione una opci&oacute;n</option>";
        				$.each(responseText, function (index, reporte) {
        	            	
        	            	html+="<option value="+reporte.idReporte+">"+reporte.nombreDespliegue+"</option>";
        	            });
        				html+="</select></br>";
        				$('#reportes').html(html);
        			}).fail(function(error){
        	   			console.log(error);
        	   		});
        		}
        	}
        	
        	$('#reportes').on('change',"#option",function(){  
        		$('#selectores').html("");
        		var reporteVar = $('#option').val();
        		var rolVar = $("#selected_rol").html();
        		
        		$.post('generaSelectores', {
        			reporte : reporteVar,
        			rol : rolVar
        		}, function(responseText){
        			var html="";
        			var edos = [
        				"Aguascalientes",
						"Baja California",
						"Baja California Sur",
						"Campeche",
						"Chiapas",
						"Chihuahua",
						"Ciudad De México",
						"Coahuila De Zaragoza",
						"Colima",
						"Durango",
						"Guanajuato",
						"Guerrero",
						"Hidalgo",
						"Jalisco",
						"México",
						"Michoacán De Ocampo",
						"Morelos",
						"Nayarit",
						"Nuevo León",
						"Oaxaca",
						"Puebla",
						"Querétaro",
						"Quintana Roo",
						"San Luis Potosí",
						"Sinaloa",
						"Sonora",
						"Tabasco",
						"Tamaulipas",
						"Tlaxcala",
						"Veracruz De Ignacio De La Llave",
						"Yucatán",
						"Zacatecas"];
        			if (responseText !== undefined) {
        				$.each(responseText, function (index, parametros) {  
	        				var keys=Object.keys(parametros);
	        				//console.log(parametros);
	        				//console.log(keys);        				
	        				$.each(parametros, function(i, parametro) {
	        					console.log(parametro);
	        					html+="<p><strong>"+parametro+"</strong></p>";
	        					html+="<select id="+String(i).replace(/[\""]/g,'\&#34;').replace(/([ /])/g,'\&#32;')+" class=\"combos\">";
	        					html+="<option value=\"0\">Seleccione "+parametro+"</option>";
	        					// Llenar combos
	        					if (parametro.toLowerCase().includes("estado")) {
	        						$.each(edos, function (index, edo) {  
	        							html+="<option value="+edo.replace(/([ /])/g,'\&#32;')+">" + edo + "</option>";
	        						});
	        					}
	        					
	        					html+="</select></br>";
	        				});
	        	        });
       				}
       				
       				
        			$('#selectores').html(html);
        			$('#consultar').show();
        		}).fail(function(error){
        			console.log(error);
        		});
        	});
        	
        	$('#consultar').on('click', function(){
        		var reporteVar = $('#option').val();
        		var rolVar = $("#selected_rol").html();
        		var datosJSON = {};
        		datosJSON.rol = rolVar;
        		datosJSON.reporte = reporteVar;
        		var filtro=null;
        		var valor=null;
        		$(".combos").each(function(index) {
        	      //alert(index + ": " + $(this).text());
        	      filtro=$(this).attr('id');
        	      valor=$(this).val();
        	      datosJSON[filtro]=valor;
        	  });
        		
        		xhr = new XMLHttpRequest();
        	    xhr.open("POST", "generaUrlDinamica", true);
        	    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        	    
        	    xhr.send(JSON.stringify(datosJSON));
        	    xhr.onload = function () {
        	        if (xhr.readyState === xhr.DONE) {
        	            if (xhr.status === 200) {
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
           
           $('#btnLogout').click(function() {
				window.location.href = 'home?accion=logout';
			});
        });
        </script>
	</body>
</html>
