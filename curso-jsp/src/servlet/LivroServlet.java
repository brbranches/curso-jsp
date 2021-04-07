package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanLivro;
import dao.DaoLivro;

/**
 * Servlet implementation class Livro
 */
@WebServlet("/salvarLivro")
public class LivroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoLivro daoLivro = new DaoLivro();

	public LivroServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			String acao = request.getParameter("acao");
			String livro = request.getParameter("livro");

			if (acao.equalsIgnoreCase("delete")) {
				daoLivro.delete(livro);
				RequestDispatcher view = request.getRequestDispatcher("cadastroLivro.jsp");
				request.setAttribute("livros", daoLivro.listarTodos());
				request.setAttribute("mensagem2", "Livro deletado com sucesso");
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {

				BeanLivro beanLivro = daoLivro.consultar(livro);
				RequestDispatcher view = request.getRequestDispatcher("cadastroLivro.jsp");
				request.setAttribute("livro", beanLivro);
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("listar")) {
				RequestDispatcher view = request.getRequestDispatcher("cadastroLivro.jsp");
				request.setAttribute("livros", daoLivro.listarTodos());
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
				RequestDispatcher view = request.getRequestDispatcher("cadastroLivro.jsp");
				request.setAttribute("livros", daoLivro.listarTodos());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String titulo = request.getParameter("titulo");
			String autor = request.getParameter("autor");
			String anoPublicacao = request.getParameter("anoPublicacao");
			String qntPaginas = request.getParameter("qntPaginas");

			BeanLivro livro = new BeanLivro();
			livro.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
			livro.setTitulo(titulo);
			livro.setAutor(autor);
			livro.setAnoPublicacao(anoPublicacao);

			try {
				if (qntPaginas != null && !qntPaginas.isEmpty()) {
					livro.setQntPaginas((Integer.parseInt(qntPaginas)));
				}

				if (titulo == null || titulo.isEmpty()) {
					request.setAttribute("mensagem", "O campo título do livro é obrigatório!");
					request.setAttribute("livro", livro);
				}

				else if (autor == null || autor.isEmpty()) {
					request.setAttribute("mensagem", "O campo autor do livro é obrigatório!");
					request.setAttribute("livro", livro);
				}

				else if (anoPublicacao == null || anoPublicacao.isEmpty()) {
					request.setAttribute("mensagem", "O campo publicação do livro é obrigatório!");
					request.setAttribute("livro", livro);
				}

				else if (id == null || id.isEmpty()) {
					daoLivro.salvarLivro(livro);
					request.setAttribute("mensagem", "Livro salvo com sucesso");
				} else {
					daoLivro.atualizar(livro);
					request.setAttribute("mensagem", "Livro atualizado com sucesso");
				}

				RequestDispatcher view = request.getRequestDispatcher("cadastroLivro.jsp");
				request.setAttribute("livros", daoLivro.listarTodos());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
