package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import beans.BeanTelefone;
import dao.DaoTelefone;
import dao.DaoUsuario;

@WebServlet("/salvarTelefone")
public class TelefoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();
	private DaoTelefone daoTelefone = new DaoTelefone();

	public TelefoneServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		try {

			// Quando clica em telefone, chama essa servlet salvarTelefone, passando o
			// parâmetro o user + id que ta na setado na tela.
			String user = request.getParameter("user");
			String acao = request.getParameter("acao");

			// Instancio um objeto usuário. Faço uma consulta no banco passando o objeto
			// user inteiro. La na consulta ele consulta pelo ID.
			// Ai guardo o resultado da consulta no objeto usuario.
			BeanCursoJsp usuario = daoUsuario.consultar(user);

			if (acao.equalsIgnoreCase("addFone")) {

				// Aqui eu abro uma sessão e guardo nela as informações do objeto usuario,
				// passando o parâmetro "userEscolhido" para ser usado na tela.

				request.getSession().setAttribute("userEscolhido", usuario);

				// Aqui eu passo os atributos pra serem setados na tela;

				request.setAttribute("userEscolhido", usuario);

				RequestDispatcher view = request.getRequestDispatcher("cadastroTelefone.jsp");
				request.setAttribute("telefones", daoTelefone.listarTodos(usuario.getId()));
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("delete")) {
				String idFone = request.getParameter("idFone");
				daoTelefone.delete(idFone);

				BeanCursoJsp beanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");

				RequestDispatcher view = request.getRequestDispatcher("cadastroTelefone.jsp");
				request.setAttribute("telefones", daoTelefone.listarTodos(beanCursoJsp.getId()));
				request.setAttribute("mensagem", "Telefone removido com sucesso!");
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String numero = request.getParameter("numero");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarTodos());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (numero == null || (numero != null && numero.isEmpty())) {
			try {
				BeanCursoJsp beanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");
				RequestDispatcher view = request.getRequestDispatcher("cadastroTelefone.jsp");
				request.setAttribute("telefones", daoTelefone.listarTodos(beanCursoJsp.getId()));
				request.setAttribute("mensagem", "Informe um número de telefone!");
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			try {

				// Aqui eu instancio um novo objeto e passo os parâmetros que estão guardados na
				// sessão.
				BeanCursoJsp beanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");

				numero = request.getParameter("numero");
				String tipo = request.getParameter("tipo");

				BeanTelefone beanTelefone = new BeanTelefone();

				beanTelefone.setNumero(numero);
				beanTelefone.setTipo(tipo);
				beanTelefone.setUsuario(beanCursoJsp.getId());

				daoTelefone.salvarTelefone(beanTelefone);

				request.getSession().setAttribute("userEscolhido", beanCursoJsp);
				request.setAttribute("userEscolhido", beanCursoJsp);

				RequestDispatcher view = request.getRequestDispatcher("cadastroTelefone.jsp");
				request.setAttribute("telefones", daoTelefone.listarTodos(beanCursoJsp.getId()));
				request.setAttribute("mensagem", "Telefone salvo com sucesso!");
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}