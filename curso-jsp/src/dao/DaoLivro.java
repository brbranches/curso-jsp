package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.BeanLivro;
import connection.SingleConnection;

public class DaoLivro {

	private Connection connection;

	public DaoLivro() {

		connection = SingleConnection.getConnection();
	}

	public void salvarLivro(BeanLivro livro) throws Exception {

		try {
			String sql = "insert into livro(titulo, autor, anopublicacao, qntpaginas) values(?,?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, livro.getTitulo());
			insert.setString(2, livro.getAutor());
			insert.setString(3, livro.getAnoPublicacao());
			insert.setInt(4, livro.getQntPaginas());
			insert.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		}

	}

	public ArrayList<BeanLivro> listarTodos() throws Exception {
		ArrayList<BeanLivro> listar = new ArrayList<BeanLivro>();// Instancio uma lista de objetos BeanLivro

		String sql = "select * from livro"; // Monto a sql

		PreparedStatement list = connection.prepareStatement(sql); // preparo a sql e mando para o "list";
		ResultSet resultSet = list.executeQuery();// ResultSet é o resultado da query; Executo o sql e guardo no
													// resultset;
		while (resultSet.next()) { // Enquanto o resultset tiver resultado ele faz;
			BeanLivro beanLivro = new BeanLivro();// Instancio um objeto do tipo beanLivro
			
			beanLivro.setId(resultSet.getLong("id"));
			beanLivro.setTitulo(resultSet.getString("titulo"));
			beanLivro.setAutor(resultSet.getString("autor"));
			beanLivro.setAnoPublicacao(resultSet.getString("anopublicacao"));
			beanLivro.setQntPaginas(resultSet.getInt("qntpaginas"));

			listar.add(beanLivro); // Adiciono o objeto com os daodos já setados na lista.

		}
		return listar; // Retorno a lista com os dados;

	}

	public void delete(String id) {
		try {
			String sql = "delete from livro where id = '" + id + "'";
			PreparedStatement deletar = connection.prepareStatement(sql);
			deletar.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public BeanLivro consultar(String id) throws SQLException {
		String sql = "select * from livro where id = '" + id + "'"; // Verificar se o titulo que esta no banco é
																			// o mesmo titulo
																			// que está vindo por parâmetro do
																			// getParameter do Servlet

		PreparedStatement editar = connection.prepareStatement(sql); // Preparo a SQL e passo para o editar
		ResultSet resultSet = editar.executeQuery(); // jogo o resultado da query para o resultset

		if (resultSet.next()) { // Se encontrar o login que está vindo no resultset ele entra na condição;

			BeanLivro beanLivro = new BeanLivro();// Instancia um novo objeto pois tenho que retornar ele para
															// o usuário;

			//O resultset da um get nos dados do banco vindos do resultado da query acima e seta o atributo id no bean;
			beanLivro.setId(resultSet.getLong("id"));
			beanLivro.setTitulo(resultSet.getString("titulo"));
			beanLivro.setAutor(resultSet.getString("autor"));
			beanLivro.setAnoPublicacao(resultSet.getString("anopublicacao"));
			beanLivro.setQntPaginas(resultSet.getInt("qntPaginas"));
			
			return beanLivro; // Retorno o objeto setado
		}
		return null;

	}

	
	
	public void atualizar(BeanLivro livro) {
		try {
			String sql = "update livro set titulo = ?, autor = ?, anopublicacao = ?, qntpaginas = ? where id= '" + livro.getId() + "'";
			PreparedStatement update = connection.prepareStatement(sql);
			update.setString(1, livro.getTitulo());
			update.setString(2, livro.getAutor());
			update.setString(3, livro.getAnoPublicacao());
			update.setInt(4, livro.getQntPaginas());
			
			update.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

}
