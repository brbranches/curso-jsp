<%@page import="beans.BeanCursoJsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CADASTRO DE USUÁRIOS</title>
<link rel="stylesheet" href="resources/css/cadastro.css">

<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>

</head>
<body>

	<a href="acessoliberado.jsp">Início</a>
	<a href="index.jsp">Sair</a>

	<div class="form-style-10">

		<h1 align="center">Cadastro de Usuários</h1>

		<div class="section"></div>
		<div class="inner-wrap">
			<form action="salvarUsuario" method="post" id="formUser"
				onsubmit="return validarCampos()? true : false;"
				enctype="multipart/form-data">
				<table class="table">
					<tr>
						<td>Código</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${user.id}" placeholder="Código"></td>


						<td>CEP</td>
						<td><input type="text" id="cep" name="cep"
							onblur="consultaCep();" value="${user.cep}" placeholder="CEP"></td>

					</tr>


					<tr>

						<td>Rua</td>
						<td><input type="text" id="rua" name="rua"
							value="${user.rua}" placeholder="Rua"></td>


						<td>Bairro</td>
						<td><input type="text" id="bairro" name="bairro"
							value="${user.bairro}" placeholder="Bairro"></td>

					</tr>

					<tr>
						<td>Cidade</td>
						<td><input type="text" id="cidade" name="cidade"
							value="${user.cidade}" placeholder="Cidade"></td>

						<td>UF</td>
						<td><input type="text" id="uf" name="uf" value="${user.uf}"
							placeholder="UF - Estado"></td>

					</tr>

					<tr>
						<td>IBGE</td>
						<td><input type="text" id="ibge" name="ibge"
							value="${user.ibge}" placeholder="Código IBGE"></td>

						<td>Login</td>
						<td><input type="text" id="login" name="login"
							value="${user.login}" placeholder="Login"></td>


					</tr>

					<tr>

						<td>Nome</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome}" placeholder="Nome"></td>


						<td>Senha</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha}" placeholder="Senha"></td>

					</tr>

					<tr>
						<td>Foto:</td>
						<td><input type="file" name="foto" id="foto"></td>

						<td>Currículo:</td>
						<td><input type="file" name="curriculo" id="curriculo"
							value="Curriculo"></td>


					</tr>

					<tr>
						<td>Sexo:</td>
						<td><input type="radio" name="sexo" id="sexo"
							<%if (request.getAttribute("user") != null) {

	BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");

	if (user.getSexo().equalsIgnoreCase("masculino")) {
		out.print(" ");
		out.print("checked=\"checked\"");
		out.print(" ");
	}
}%>
							value="masculino">Masculino </input> <input type="radio"
							name="sexo" id="sexo"
							<%if (request.getAttribute("user") != null) {

	BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");

	if (user.getSexo().equalsIgnoreCase("feminino")) {
		out.print(" ");
		out.print("checked=\"checked\"");
		out.print(" ");
	}
}%>
							value="feminino">Feminino</input></td>


						<td>Perfil</td>
						<td><select id="perfil" name="perfil">

								<option value="nao_informado">[--- SELECIONE --- ]</option>
								
								<option value="administrador"
										<%if (request.getAttribute("user") != null) {

											BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
										
											if (user.getPerfil().equalsIgnoreCase("administrador")) {
												out.print(" ");
												out.print("selected=\"selected\"");
												out.print(" ");
											}
										}%>
								
								>Administrador(a) </option>
								
								
								
								<option value="secretario"
								<%if (request.getAttribute("user") != null) {

											BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
										
											if (user.getPerfil().equalsIgnoreCase("secretario")) {
												out.print(" ");
												out.print("selected=\"selected\"");
												out.print(" ");
											}
										}%>
								
								
								> Secretário(a) </option>
								
								
								
								<option value="gerente"
									<%if (request.getAttribute("user") != null) {

									BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
										
									if (user.getPerfil().equalsIgnoreCase("gerente")) {
										out.print(" ");
										out.print("selected=\"selected\"");
										out.print(" ");
										}
									}%>
								
								> Gerente </option>
								


								<option value="funcionario"
								
								<%if (request.getAttribute("user") != null) {
									
									BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
										
									if (user.getPerfil().equalsIgnoreCase("funcionario")) {
										out.print(" ");
										out.print("selected=\"selected\"");
										out.print(" ");
									}
								}%>
								> Funcionário </option>

						</select></td>


					</tr>

					<tr>

						<td>Ativo</td>
						<td><input type="checkbox" name="ativo" id="ativo"
							<%if (request.getAttribute("user") != null) {
	BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");

	if (user.isAtivo()) {
		out.print(" ");
		out.print("checked=\"checked\"");
		out.print(" ");
	}
}%>></td>

					</tr>

					<tr>
						<td></td>
						<td><input type="submit" value="Salvar" id="salvar">
							<input type="submit" value="Cancelar" id="cancelar"
							onclick="document.getElementById('formUser').action='salvarUsuario?acao=reset'">
						</td>

					</tr>
				</table>
			</form>
		</div>
		
		<div class="form-style-11">
		<h3>Pesquisa de usuários</h3>
				<form action="servletPesquisa" method="post" id="formPesquisaUser">
					<table>
						<tr>
							<td>Descrição</td>
							<td><input type="text" id="descricao_consulta" name="descricaoConsulta"></td>
							<td><input type="submit" value="pesquisar"></td>
						</tr>
					</table>
				</form>
			</div>
	</div>


	<br>

	<h3>${mensagem}</h3>


	<div class="form-style-10">
		<h1>Lista de Usuários</h1>
		<div class="section"></div>
		<div class="inner-wrap">

			<table>
				<tr>
					<td style="width: 80px">Código</td>
					<td style="width: 80px">Login</td>
					<td style="width: 50px">Foto</td>
					<td style="width: 80px">Currículo</td>
					<td style="width: 80px">Nome</td>
					<td style="width: 80px">Cep</td>
					<td style="width: 80px">Rua</td>
					<td style="width: 80px">Bairro</td>
					<td style="width: 80px">Cidade</td>
					<td style="width: 80px">UF</td>
					<td style="width: 80px">IBGE</td>

				</tr>

				<C:forEach items="${usuarios}" var="user">

					<tr>
						<td style="width: 80px"><C:out value="${user.id}"></C:out></td>
						<td style="width: 80px"><C:out value="${user.login}"></C:out></td>

						<C:if test="${user.fotoBase64Miniatura.isEmpty() == false}">
							<td style="width: 80px"><a
								href="salvarUsuario?acao=download&tipo=imagem&user=${user.id}">
									<img src='<C:out value="${user.fotoBase64Miniatura}"/>'
									width="25px" height="25px;" />
							</a></td>
						</C:if>

						<C:if test="${user.fotoBase64Miniatura == null}">
							<td style="width: 80px"><img
								src="resources/img/userpadrao_erro.png" width="23px"
								height="23px;" /></td>
						</C:if>


						<C:if test="${user.curriculoBase64.isEmpty() == false}">
							<td style="width: 80px"><a
								href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}">
									<img src="resources/img/pdf.png" width="25px" height="25px;" />
							</a></td>
						</C:if>

						<C:if test="${user.curriculoBase64 == null}">
							<td style="width: 80px"><img
								src="resources/img/pdf_erro.png" width="25px" height="25px;" /></td>
						</C:if>


						<td style="width: 80px"><C:out value="${user.nome}"></C:out></td>
						<td style="width: 100px"><C:out value="${user.cep}"></C:out></td>
						<td style="width: 250px"><C:out value="${user.rua}"></C:out></td>
						<td style="width: 120px"><C:out value="${user.bairro}"></C:out></td>
						<td style="width: 80px"><C:out value="${user.cidade}"></C:out></td>
						<td style="width: 40px"><C:out value="${user.uf}"></C:out></td>
						<td style="width: 80px"><C:out value="${user.ibge}"></C:out></td>

						<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img
								title="Editar" src="resources/img/editar.png" width="20px"
								height="20px;"> </a></td>

						<td><a href="salvarUsuario?acao=delete&user=${user.id}"
							onclick=" return confirm('Deseja mesmo remover?')"><img
								title="Excluir" src="resources/img/excluir.png" width="20px"
								height="20px;"> </a></td>

						<td><a href="salvarTelefone?acao=addFone&user=${user.id}"><img
								title="Telefone" src="resources/img/telefone.png" width="20px"
								height="20px;"> </a></td>
					</tr>

				</C:forEach>
			</table>
		</div>
	</div>

	<script type="text/javascript">
		// 		function validarCampos() {
		// 			if (document.getElementById("login").value == '') {
		// 				alert('Informe o login!');
		// 				return false
		// 			}

		// 			else if (document.getElementById("senha").value == ''){
		// 				alert('Informe a senha!');
		// 				return false
		// 			}

		// 			else if (document.getElementById("nome").value == '') {
		// 				alert('Informe o nome!');
		// 				return false
		// 			}

		// 			return true;
		// 		}

		function consultaCep() {
			var cep = $("#cep").val();

			//Consulta o webservice viacep.com.br/
			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {

						if (!("erro" in dados)) {
							$("#rua").val(dados.logradouro);
							$("#bairro").val(dados.bairro);
							$("#cidade").val(dados.localidade);
							$("#uf").val(dados.uf);
							$("#ibge").val(dados.ibge);

						} //end if.
						else {
							//CEP pesquisado não foi encontrado.
							alert("CEP não encontrado.");
							$("#cep").val('');
							$("#rua").val('');
							$("#bairro").val('');
							$("#cidade").val('');
							$("#uf").val('');
							$("#ibge").val('');
						}
					});
		}
	</script>


</body>
</html>