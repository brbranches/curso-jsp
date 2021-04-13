
<jsp:useBean id="BeanUsuario" class="beans.BeanCursoJsp"
	type="beans.BeanCursoJsp" scope="page"></jsp:useBean>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/estiloAcessoPermitido.css">
</head>
<body>

	<h2>SEJA BEM-VINDO AO SISTEMA EM JSP</h2>

	<table class="table">
		<tr>
			<td><center><a href="salvarUsuario?acao=listar"> <img title="Adicionar usuário" src="resources/img/add_user.png" width="50px" height="50px;"></a></td> </center>
			<td> </td>
			<td> </td>
			<td> </td>
			<td><center><a href="salvarLivro?acao=listar"> <img title="Adicionar livro" src="resources/img/add_livro.png" width="50px" height="50px;"></a></td> </center>
			<td> </td>
			<td> </td>
			<td> </td>
			<td><center><a href="salvarProduto?acao=listar"> <img title="Adicionar Produto" src="resources/img/add_produto.png" width="60px" height="60px;"></a></td> </center>
		</tr>
		<tr>
			<td><a href="salvarUsuario?acao=listar">[ Adicionar usuário ]</a></td>
			<td> </td>
			<td> </td>
			<td> </td>
			<td><a href="salvarLivro?acao=listar">[ Adicionar livro ]</a></td>
			<td> </td>
			<td> </td>
			<td> </td>
			<td><a href="salvarProduto?acao=listar">[ Adicionar produto ]</a></td>
		</tr>
	</table>
	
</body>
</html>