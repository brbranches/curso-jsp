package servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanCursoJsp;
import dao.DaoUsuario;

@WebServlet("/salvarUsuario")
@MultipartConfig

public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public UsuarioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* Se eu chamo o outputstream, nao posso chamar o getwriter junto */
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao != null && acao.equalsIgnoreCase("delete") && user != null) {
				daoUsuario.delete(user);
				RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarTodos());
				request.setAttribute("mensagem", "Usuario deletado com sucesso");
				view.forward(request, response);

			} else if (acao != null && acao.equalsIgnoreCase("editar") && user != null) {

				BeanCursoJsp beanCursoJsp = daoUsuario.consultar(user);
				RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("user", beanCursoJsp);
				view.forward(request, response);

			} else if (acao != null && acao.equalsIgnoreCase("listar") && user != null) {
				RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarTodos());
				view.forward(request, response);

			} else if (acao != null && acao.equalsIgnoreCase("download") && user != null) {
				BeanCursoJsp usuario = daoUsuario.consultar(user); // COnsulta o id do usuario no banco
				if (usuario != null) { // verifica se nao está null

					String contentType = "";
					byte[] fileBytes = null;

					String tipo = request.getParameter("tipo");

					if (tipo.equalsIgnoreCase("imagem")) {
						contentType = usuario.getContentType();
						fileBytes = new Base64().decodeBase64(usuario.getFotoBase64());

					} else if (tipo.equalsIgnoreCase("curriculo")) {
						contentType = usuario.getContentTypeCurriculo();
						fileBytes = new Base64().decodeBase64(usuario.getCurriculoBase64());
					}

					// seta o cabeçalho e pega o content type;No split ele pega o 2 endereço do
					// array "image/png", pega o png, por exemplo.
					response.setHeader("Content-Disposition",
							"attachment;filename=arquivo." + contentType.split("\\/")[1]);

					// Converte os bytes para fluxo de entrada para processar.
					InputStream is = new ByteArrayInputStream(fileBytes);

					// Inicio da resposta para o navegador
					int read = 0;
					byte[] bytes = new byte[1024];

					OutputStream opst = response.getOutputStream();

					while ((read = is.read(bytes)) != -1) {
						opst.write(bytes, 0, read);
					}
					opst.flush();
					opst.close();
				}
			} else {
				RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarTodos());
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarTodos());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String uf = request.getParameter("uf");
			String ibge = request.getParameter("ibge");
			String sexo = request.getParameter("sexo");
			String perfil = request.getParameter("perfil");
			

			BeanCursoJsp usuario = new BeanCursoJsp();
			usuario.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setTelefone(telefone);
			usuario.setCep(cep);
			usuario.setRua(rua);
			usuario.setBairro(bairro);
			usuario.setCidade(cidade);
			usuario.setUf(uf);
			usuario.setIbge(ibge);
			usuario.setSexo(sexo);
			usuario.setPerfil(perfil);
			
			if (request.getParameter("ativo") != null && request.getParameter("ativo").equalsIgnoreCase("on")) {
				usuario.setAtivo(true);
			}else {
				usuario.setAtivo(false);
			}

			try {

				/*
				 * --------------- Início do File Upload de imagens e PDF ----------------------
				 */

				if (ServletFileUpload.isMultipartContent(request)) {

					/*---------------------- Pega o parâmetro foto na tela pelo request. Ele busca o atributo name --------------*/
					Part imagemFoto = request.getPart("foto");

					/*---------------- Veririca se o campo foto tem o arquivo, se tiver, ele entra no IF--------------*/
					if (imagemFoto != null && imagemFoto.getInputStream().available() > 0) {

						/* Convertendo a imagem em bytes */

						/* Atribuindo os bytes para a variavel String fotoBase64 */
						String fotoBase64 = new Base64()
								.encodeBase64String(converteStreamParaByte(imagemFoto.getInputStream()));

						/*--------------- Salvando as informações nos atributos bean. Dados salvos em String-----------------*/
						/*-----------------Salvando o "nome" da imagem-----------------*/
						usuario.setFotoBase64(fotoBase64);

						/*-----------------Salvando o "tipo de conteúdo" da imagem (JPEG, PNG, etc)-----------------*/
						usuario.setContentType(imagemFoto.getContentType());

						/*-----------------Convertendo imagem em miniatura
						  -----------------Transformando a imagem em um bufferImage-----------------*/

						byte[] imageBytesDecode = new Base64().decodeBase64(fotoBase64);

						BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytesDecode));

						/*
						 * -----------------Pega o tipo da imagem -----------------o gettype retora um
						 * inteiro. Se for igual a 0 ele atribui um valor padrão o ARGB, se não, ele
						 * atribui -----------------o valor do gettype da imagem. -----------------
						 */

						int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();

						/*-----------------Cria imagem em miniatura-----------------*/
						BufferedImage resizadeImage = new BufferedImage(100, 100, type);
						Graphics2D g = resizadeImage.createGraphics();
						g.drawImage(bufferedImage, 0, 0, 100, 100, null);
						g.dispose();

						/*-----------------Escrever a imagem novamente-----------------*/
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(resizadeImage, "png", baos);

						String miniaturaBase64 = "data:image/png;base64,"
								+ DatatypeConverter.printBase64Binary(baos.toByteArray());

						usuario.setFotoBase64Miniatura(miniaturaBase64);

						/*---------------------------------- Fim da conversão da imagem em miniatura----------------------------------*/

					} else {
						usuario.setAtualizarImagem(false);
					}

					/* PROCESSA PDF */
					Part curriculoPdf = request.getPart("curriculo");

					if (curriculoPdf != null && curriculoPdf.getInputStream().available() > 0) {

						String curriculoBase64 = new Base64()
								.encodeBase64String(converteStreamParaByte(curriculoPdf.getInputStream()));

						usuario.setCurriculoBase64(curriculoBase64);
						usuario.setContentTypeCurriculo(curriculoPdf.getContentType());
					} else {
						usuario.setAtualizarPdf(false);
					}
				}

				/* Fim do File Upload de imagens e PDF */

				if (login == null || login.isEmpty()) {
					request.setAttribute("mensagem", "O campo login é obrigatório e deve ser informado!");
					request.setAttribute("user", usuario);

				} else if (senha == null || senha.isEmpty()) {
					request.setAttribute("mensagem", "O campo senha é obrigatório e deve ser informado!");
					request.setAttribute("user", usuario);

				} else if (nome == null || nome.isEmpty()) {
					request.setAttribute("mensagem", "O campo nome é obrigatório e deve ser informado!");
					request.setAttribute("user", usuario);

				} else if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
					request.setAttribute("mensagem",
							"Login já está sendo utilizado por outro usuário! Tente cadastrar com um login diferente");
					request.setAttribute("user", usuario);

				} else if (id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
					request.setAttribute("mensagem",
							"Senha já está sendo utilizada por outro usuário! Tente cadastrar com uma senha diferente");
					request.setAttribute("user", usuario);

				} else if (id == null
						|| id.isEmpty() && daoUsuario.validarLogin(login) && daoUsuario.validarSenha(senha)) {
					daoUsuario.salvarUsuario(usuario);
					request.setAttribute("mensagem", "Usuário cadastrado com sucesso");

				} else if (id != null && !id.isEmpty() && !daoUsuario.validarLoginUpdate(login, id)) {
					request.setAttribute("mensagem",
							"Login já está sendo utilizado por outro usuário! Tente atualizar com um login diferente");
					request.setAttribute("user", usuario);

				} else if (id != null && !id.isEmpty() && !daoUsuario.validarSenhaUpdate(senha, id)) {
					request.setAttribute("mensagem",
							"Senha já está sendo utilizada	 por outro usuário! Tente atualizar com uma senha diferente");
					request.setAttribute("user", usuario);

				} else {
					daoUsuario.atualizar(usuario);
					request.setAttribute("mensagem", "Usuário atualizado com sucesso");
				}

				RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarTodos());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	/* Converte a entrada de fluxo de dados da imagem para byte */

	private byte[] converteStreamParaByte(InputStream imagem) throws Exception {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int reads = imagem.read();
		while (reads != -1) {

			baos.write(reads);
			reads = imagem.read();
		}
		return baos.toByteArray();

	}

}
