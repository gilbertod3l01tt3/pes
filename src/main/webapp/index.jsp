<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Portal OBIEE</title>
    </head>
    <body>
        <h1>Ingrese su nombre y usuario por favor</h1>
        <form action="LoginController" method="post">
			Usuario :<input type="text" name="username"> <br>
			Password :<input type="password" name="password"><br>
			<input type="submit" value="Login">
		</form>
    </body>
</html>
