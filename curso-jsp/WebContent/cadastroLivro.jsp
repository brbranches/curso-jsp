<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CADASTRO DE LIVROS</title>

<link rel="stylesheet" href="resources/css/cadastro.css">

</head>
<body>

<a href="acessoliberado.jsp">Início</a>
	<tr> | </tr>
	<a href="index.jsp">Sair</a>

	<div class="form-style-10">
		<h1>Cadastro de Livros</h1>
		<div class="section"></div>
		<div class="inner-wrap">
			<form action="salvarLivro" method="post" id="formLivro">
				<table>

					<tr>
						<td>Código</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${livro.id}"></td>
					</tr>


					<tr>
						<td>Titulo</td>
						<td><input type="text" id="titulo" name="titulo"
							value="${livro.titulo}"></td>
					</tr>

					<tr>
						<td>Autor</td>
						<td><input type="text" id="autor" name="autor"
							value="${livro.autor}"></td>
					</tr>

					<tr>
						<td>Publicação</td>
						<td><input type="text" id="anoPublicacao"
							name="anoPublicacao" value="${livro.anoPublicacao}"></td>
					</tr>

					<tr>
						<td>Páginas</td>
						<td><input type="text" id="qntPaginas" name="qntPaginas"
							value="${livro.qntPaginas}"></td>
					</tr>

					<tr>
						<td></td>

						<td>
						<input type="submit" value="Salvar"> 
						<input type="submit" value="Cancelar" onclick="document.getElementById('formLivro').action='salvarLivro?acao=reset'">
						</td>

					</tr>
				</table>
		</div>
	</div>
	</form>

	<h3>${mensagem}</h3>
	<h3>${mensagem2}</h3>
	<h3>${mensagem3}</h3>

	<br>

	<div class="form-style-10">
		<h1>Lista de Livros</h1>
		<div class="section"></div>
		<div class="inner-wrap">

			<table>
				<tr>
					<td style="width: 150px">Código</td>
					<td style="width: 150px">Título</td>
					<td style="width: 150px">Autor</td>
					<td style="width: 150px">Ano Publicação</td>
					<td style="width: 150px">Qnt Páginas</td>
				</tr>



				<C:forEach items="${livros}" var="livro">
					<tr>
						<td style="width: 50px"><C:out value="${livro.id}"></C:out></td>
						<td style="width: 150px"><C:out value="${livro.titulo}"></C:out></td>
						<td style="width: 150px"><C:out value="${livro.autor}"></C:out></td>
						<td style="width: 150px"><C:out
								value="${livro.anoPublicacao}"></C:out></td>
						<td><C:out value="${livro.qntPaginas}"></C:out></td>

						<td><a href="salvarLivro?acao=editar&livro=${livro.id}"><img
								title="Editar" src="resources/img/editar.png" width="20px"
								height="20px;"> </a></td>
						<td><a href="salvarLivro?acao=delete&livro=${livro.id}"><img
								title="Excluir" src="resources/img/excluir.png" width="20px"
								height="20px;"> </a></td>
					</tr>

				</C:forEach>

			</table>
		</div>
	</div>
</body>
</html>