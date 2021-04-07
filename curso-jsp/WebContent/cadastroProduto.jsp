<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CADASTRO DE PRODUTOS</title>

<script src="resources/javascript/jquery.min.js" type="text/javascript"></script>
<script src="resources/javascript/jquery.maskMoney.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" href="resources/css/cadastro.css">


</head>
<body>

	<a href="acessoliberado.jsp">Início</a>

	<a href="index.jsp">Sair</a>


	<div class="form-style-10">
		<h1>Cadastro de Produtos</h1>
		<div class="section"></div>
		<div class="inner-wrap">
			<form action="salvarProduto" method="post" id="formProduct"
				onsubmit="return validarCampos()? true : false;">
				<table>

					<tr>
						<td>Código</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${product.id}"></td>
					</tr>


					<tr>
						<td>Nome</td>
						<td><input type="text" id="nome" name="nome"
							value="${product.nome}"></td>
					</tr>

					<tr>
						<td>Quantidade</td>
						<td><input type="number" id="quantidade" name="quantidade"
							value="${product.quantidade}"></td>
					</tr>

					<tr>
						<td>Preço R$</td>
						<td><input type="text" id="preco" name="preco"
							data-thousands="." data-decimal=","
							value="${product.precoEmTexto}"></td>
					</tr>


					<tr>
						<td></td>

						<td><input type="submit" id="salvar" value="Salvar">
							<input type="submit" id="cancelar" value="Cancelar"
							onclick="document.getElementById('formProduct').action='salvarProduto?acao=reset'">
						</td>

					</tr>
				</table>
			</form>
		</div>

	</div>


	<br>

	<h3>${mensagem}</h3>


	<div class="form-style-10">
		<h1>Lista de Produtos</h1>
		<div class="section"></div>
		<div class="inner-wrap">

			<table>
				<tr>
					<td style="width: 150px">Código</td>
					<td style="width: 150px">Nome</td>
					<td style="width: 150px">Quantidade</td>
					<td style="width: 150px">Preço</td>

				</tr>

				<C:forEach items="${produtos}" var="product">

					<tr>
						<td style="width: 150px"><C:out value="${product.id}"></C:out></td>
						<td style="width: 150px"><C:out value="${product.nome}"></C:out></td>
						<td><C:out value="${product.quantidade}"></C:out></td>
						<td style="width: 150px"><fmt:formatNumber type="number" maxFractionDigits="2" value="${product.preco}"/></td> 

						<td><a href="salvarProduto?acao=editar&product=${product.id}"><img
								title="Editar" src="resources/img/editar.png" width="20px"
								height="20px;"> </a></td>
						
						
						<td><a href="salvarProduto?acao=delete&product=${product.id}"><img
								title="Excluir" src="resources/img/excluir.png" width="20px"
								height="20px;"> </a></td>
						
					</tr>
				</C:forEach>


			</table>
		</div>
	</div>

</body>

<script>
	$(function() {
		$('#preco').maskMoney();
	})
</script>

</html>