

<jsp:useBean id="BeanUsuario" class="beans.BeanCursoJsp"
	type="beans.BeanCursoJsp" scope="page"></jsp:useBean>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C"%>
<%@ taglib prefix="myprefix" uri="WEB-INF/testetag.tld"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Faça seu login</title>
<link rel="stylesheet" href="resources/css/estilo.css">

</head>

<body>
	<h2 class="titulo" > SISTEMA DE CADASTRO</h2>

	<div class="login-page">
		<div class="form">
		<h3 class="titulo" >FAÇA SEU LOGIN</h3>
			<form action="LoginServlet" method="post" class="login-form">
				<input placeholder="login" type="text" id="login" name="login"> 
				</br> 
				<input placeholder="senha" type="password" id="senha" name="senha"> 
				
				<button type="submit" value="Logar">LOGAR</button>
			</form>
		</div>
	</div>
</body>
</html>