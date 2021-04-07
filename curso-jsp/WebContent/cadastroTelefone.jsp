<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CADASTRO DE TELEFONES</title>
<link rel="stylesheet" href="resources/css/cadastro.css">

<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>

</head>
<body>

	<center> <a href="acessoliberado.jsp">Início</a>
	<tr> | </tr>
	<a href="index.jsp">Sair</a></center>

	<div class="form-style-10">

		<center><h1>Cadastro de Telefones</h1></center>
		<div class="section"></div>
		<div class="inner-wrap">

			
			<form action="salvarTelefone" method="post" id="formTelefone"
				onsubmit="return validarCampos()? true : false;">

			<center>	<table>

					<tr>
						<td>User</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${userEscolhido.id}"></td>
					</tr>

					<tr>
						<td>Nome</td>
						<td><input type="text" readonly="readonly" id="nome"
							name="nome" value="${userEscolhido.nome}"></td>
					</tr>

					<tr>
						<td>Número</td>
						<td><input type="text" id="numero" name="numero"></td>
					</tr>

					<tr>
						<td>Tipo</td>
						<td><select id="tipo" name="tipo">
								<option>---</option>
								<option>Celular</option>
								<option>Fixo</option>
								<option>Contato</option>
						</select></td>
					</tr>


					<tr>
						<td></td>

						<td><input type="submit" id="salvar" value="Salvar">
						 
						 	<input type="submit" id="cancelar" value="Cancelar"
							onclick="document.getElementById('formTelefone').action='salvarTelefone?acao=reset'">
						</td>

					</tr>
				</table></center>
		</div>
	</div>
	</form>

	<br>

	<h3>${mensagem}</h3>


	<div class="form-style-10">
		<center><h1>Lista de Telefones</h1></center>
		<div class="section"></div>
		<div class="inner-wrap">

			<center><table>
				<tr>
					<td style="width: 200px">Código</td>
					<td style="width: 200px">Número</td>
					<td style="width: 200px">Tipo</td>

				</tr>

				<C:forEach items="${telefones}" var="fone">

					<tr>
						<td style="width: 80px"><C:out value="${fone.id}"></C:out></td>
						<td style="width: 80px"><C:out value="${fone.numero}"></C:out></td>
						<td style="width: 80px"><C:out value="${fone.tipo}"></C:out></td>

						<td><a href="salvarTelefone?user=${fone.usuario}&acao=delete&idFone=${fone.id}"><img
								title="Excluir" src="resources/img/excluir.png" width="20px"
								height="20px;"> </a></td>
						</a>
						</td>
					</tr>
				</C:forEach>


			</table>
			</center>
		</div>
	</div>

	<script type="text/javascript">
		function validarCampos() {
			if ((document.getElementById("numero").value == '') && (document.getElementByid("salvar"))) {
				alert('Informe o numero!');
				return false
			}
			
			else if (document.getElementById("tipo").value == '---') && (document.getElementByid("salvar"))) {
				alert('Informe o tipo!');
				return false
			}
			return true;
		}
	</script>


</body>
</html>