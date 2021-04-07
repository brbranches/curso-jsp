package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProduto;
import dao.DaoProduto;

@WebServlet("/salvarProduto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoProduto daoProduto = new DaoProduto();

	public ProdutoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			String acao = request.getParameter("acao");
			String product = request.getParameter("product");

			if (acao.equalsIgnoreCase("delete")) {
				daoProduto.delete(product);
				RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listarTodos());
				request.setAttribute("mensagem", "Produto deletado com sucesso");
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {

				BeanProduto beanProduto = daoProduto.consultar(product);
				RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
				request.setAttribute("product", beanProduto);
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("listar")) {
				RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listarTodos());
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
				RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listarTodos());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String preco = request.getParameter("preco");

			BeanProduto produto = new BeanProduto();
			produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			produto.setNome(nome);

			try {
				if (quantidade != null && !quantidade.isEmpty()) {
					produto.setQuantidade(Double.parseDouble(quantidade));
				}

				if (preco != null && !preco.isEmpty()) {
					String precoParse = preco.replaceAll("\\.", ""); //10.500,20
					precoParse = precoParse.replaceAll("\\,", "."); //10500.20
					produto.setPreco(Double.parseDouble(precoParse)); //10500.20
				}

				if (nome == null || nome.isEmpty()) {
					request.setAttribute("mensagem", "O campo nome do produto é obrigatório!");
					request.setAttribute("product", produto);
				}
				
				else if (quantidade == null || quantidade.isEmpty()) {
					request.setAttribute("mensagem", "O campo quantidade do produto é obrigatório!");
					request.setAttribute("product", produto);
				}
				
				else if (preco == null || preco.isEmpty()) {
					request.setAttribute("mensagem", "O campo preço do produto é obrigatório!");
					request.setAttribute("product", produto);
				}

				else if (id == null || id.isEmpty() && !daoProduto.validarProduto(nome)) {
					request.setAttribute("mensagem",
							"Produto já está cadastrado! Tente cadastrar um produto diferente");
					request.setAttribute("product", produto);

				} else if (id == null || id.isEmpty() && daoProduto.validarProduto(nome)) {
					daoProduto.salvarProduto(produto);
					request.setAttribute("mensagem", "Produto cadastrado com sucesso");

				} else if (id != null && !id.isEmpty() && !daoProduto.validarProdutoUpdate(nome, id)) {
					request.setAttribute("mensagem",
							"Já existe um produto com esse nome! Tente atualizar com um nome de produto diferente");
					request.setAttribute("product", produto);

				} else {
					daoProduto.atualizar(produto);
					request.setAttribute("mensagem", "Produto atualizado com sucesso");
				}

				RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listarTodos());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
