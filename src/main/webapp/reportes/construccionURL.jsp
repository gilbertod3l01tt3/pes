<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Portal OBIEE</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">

<link rel="stylesheet" href="../css/styles.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>

</head>
<body>

	<label>Rol: </label>
	<input type="text" id="rol" name="name" value="Director"
		placeholder="Name..." />
	<label>IdReporte: </label>
	<input type="text" id="idReporte" name="age" value="1" placeholder="Age..." />
	<label>country: </label>
	<input type="text" id="country" name="country" value="Mexico"
		placeholder="Country..." />
		<label>Estados: </label>
		  <select name="estados" size="4" multiple>
			  <option value="volvo">Volvo</option>
			  <option value="saab">Saab</option>
			  <option value="fiat">Fiat</option>
			  <option value="audi">Audi</option>
			</select>  
	<input type="button" id="sendjsonpost" name="sendjsonpost"
		value="Send JSON POST" />
	<hr />


	<script type="text/javascript">
$(document).ready(function () {
 $('#sendjsonpost').on('click', function () {
		var rol = document.getElementById("rol").value;
        var reporte = document.getElementById("idReporte").value;
        var country = document.getElementById("country").value;
        
		xhr = new XMLHttpRequest();
        xhr.open("POST", "generaUrlDinamica", true);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
       	var datosJSON = {rol : rol, reporte : reporte, country : country, '"Ubicacion"."Entidad federativa"':"Aguascalientes,Puebla"};
        alert(JSON.stringify(datosJSON));
        xhr.send(JSON.stringify(datosJSON));			           
	});
 });
</script>
</body>
</html>