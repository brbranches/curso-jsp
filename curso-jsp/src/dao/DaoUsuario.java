package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.BeanCursoJsp;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {

		connection = SingleConnection.getConnection();
	}

	public void salvarUsuario(BeanCursoJsp usuario) throws Exception {

		try {
			String sql = "insert into usuario(login, senha, nome, telefone, cep, rua, bairro, cidade, uf, ibge, fotobase64, contenttype, curriculobase64, contenttypecurriculo, fotobase64miniatura, ativo, sexo) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getTelefone());
			insert.setString(5, usuario.getCep());
			insert.setString(6, usuario.getRua());
			insert.setString(7, usuario.getBairro());
			insert.setString(8, usuario.getCidade());
			insert.setString(9, usuario.getUf());
			insert.setString(10, usuario.getIbge());
			insert.setString(11, usuario.getFotoBase64());
			insert.setString(12, usuario.getContentType());
			insert.setString(13, usuario.getCurriculoBase64());
			insert.setString(14, usuario.getContentTypeCurriculo());
			insert.setString(15, usuario.getFotoBase64Miniatura());
			insert.setBoolean(16, usuario.isAtivo());
			insert.setString(17, usuario.getSexo());
			
			
			insert.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		}

	}

	public ArrayList<BeanCursoJsp> listarTodos() throws Exception {
		ArrayList<BeanCursoJsp> listar = new ArrayList<BeanCursoJsp>();// Instancio uma lista de objetos BeanCurso

		String sql = "select * from usuario where login <> 'admin'"; // Monto a sql

		PreparedStatement list = connection.prepareStatement(sql); // preparo a sql e mando para o "list";
		ResultSet resultSet = list.executeQuery();// ResultSet é o resultado da query; Executo o sql e guardo no
													// resultset;
		while (resultSet.next()) { // Enquanto o resultset tiver resultado ele faz;
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp(); // Instancio um objeto do tipo beanCurso;

			beanCursoJsp.setId(resultSet.getLong("id"));

			beanCursoJsp.setLogin(resultSet.getString("login"));// Pego o login vindo do resultset do sql e seto o login
																// no objeto;
			beanCursoJsp.setSenha(resultSet.getString("senha"));// Pego a senha vinda do resultset do sql e seto a senha
																// no objeto;
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setTelefone(resultSet.getString("telefone"));
			beanCursoJsp.setCep(resultSet.getString("cep"));
			beanCursoJsp.setRua(resultSet.getString("rua"));
			beanCursoJsp.setBairro(resultSet.getString("bairro"));
			beanCursoJsp.setCidade(resultSet.getString("cidade"));
			beanCursoJsp.setUf(resultSet.getString("uf"));
			beanCursoJsp.setIbge(resultSet.getString("ibge"));
//			beanCursoJsp.setFotoBase64(resultSet.getString("fotobase64")); Nao precisa mais listar na hora de carregar na tela, só na hr do download
			beanCursoJsp.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
			beanCursoJsp.setContentType(resultSet.getString("contenttype"));
			beanCursoJsp.setCurriculoBase64(resultSet.getString("curriculobase64"));
			beanCursoJsp.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			beanCursoJsp.setAtivo(resultSet.getBoolean("ativo"));
			beanCursoJsp.setSexo(resultSet.getString("sexo"));

			listar.add(beanCursoJsp); // Adiciono o objeto com os daodos já setados na lista.

		}
		return listar; // Retorno a lista com os dados;

	}

	public void delete(String id) {
		try {
			String sql = "delete from usuario where id = '" + id + "' and login <> 'admin'";
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

	public BeanCursoJsp consultar(String id) throws SQLException {
		String sql = "select * from usuario where id = '" + id + "' and login <> 'admin'"; // Verificar se o login que
																							// esta no banco é o
		// mesmo login que está vindo por parâmetro do
		// // getParameter do Servlet

		PreparedStatement consultar = connection.prepareStatement(sql); // Preparo a SQL e passo para o editar
		ResultSet resultSet = consultar.executeQuery(); // jogo o resultado da query para o resultset

		if (resultSet.next()) { // Se encontrar o login que está vindo no resultset ele entra na condição;

			BeanCursoJsp beanCursoJsp = new BeanCursoJsp(); // Instancia um novo objeto pois tenho que retornar ele para
															// o usuário;

			beanCursoJsp.setId(resultSet.getLong("id"));

			// O resultset da um get nos dados do banco vindos do resultado da query acima e
			// seta o atributo login no bean;
			beanCursoJsp.setLogin(resultSet.getString("login"));// O resultset da um get nos dados do banco vindos do
																// resultado da query acima e seta o atributo senha no
																// bean;
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setTelefone(resultSet.getString("telefone"));
			beanCursoJsp.setCep(resultSet.getString("cep"));
			beanCursoJsp.setRua(resultSet.getString("rua"));
			beanCursoJsp.setBairro(resultSet.getString("bairro"));
			beanCursoJsp.setCidade(resultSet.getString("cidade"));
			beanCursoJsp.setUf(resultSet.getString("uf"));
			beanCursoJsp.setIbge(resultSet.getString("ibge"));
			beanCursoJsp.setFotoBase64(resultSet.getString("fotobase64"));
			beanCursoJsp.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
			beanCursoJsp.setContentType(resultSet.getString("contenttype"));
			beanCursoJsp.setCurriculoBase64(resultSet.getString("curriculobase64"));
			beanCursoJsp.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			beanCursoJsp.setAtivo(resultSet.getBoolean("ativo"));
			beanCursoJsp.setSexo(resultSet.getString("sexo"));
			
			return beanCursoJsp; // Retorno o objeto setado
		}
		return null;

	}

	public boolean validarLogin(String login) throws SQLException {
		String sql = "select count(1) as qtd from usuario where login = '" + login + "'";

		PreparedStatement pstValidarLogin = connection.prepareStatement(sql); // Preparo a SQL e passo para o editar
		ResultSet resultSet = pstValidarLogin.executeQuery(); // jogo o resultado da query para o resultset

		if (resultSet.next()) { // Se encontrar o login que está vindo no resultset ele entra na condição;

			// Verificar se o resultado da sql retorna menor igual a 0, se for sim, nao tem
			// dados no banco ai deixa cadastrar o login vindo do parâmetro.
			return resultSet.getInt("qtd") <= 0;

		}
		return false; // Se nao, retorna falso e nao deixa cadastrar.

	}

	public boolean validarLoginUpdate(String login, String id) throws SQLException {
		String sql = "select count(1) as qtd from usuario where login = '" + login + "' and id <>" + id;

		PreparedStatement pstValidarLoginUpdate = connection.prepareStatement(sql); // Preparo a SQL e passo para o
																					// editar
		ResultSet resultSet = pstValidarLoginUpdate.executeQuery(); // jogo o resultado da query para o resultset

		if (resultSet.next()) { // Se encontrar o login que está vindo no resultset ele entra na condição;

			return resultSet.getInt("qtd") <= 0; // Verificar se o resultado da sql retorna menor igual a 0, se for sim,
													// nao tem dados no banco
													// ai deixa cadastrar o login vindo do parâmetro.

		}
		return false; // Se nao, retorna falso e nao deixa cadastrar.

	}

	public boolean validarSenha(String senha) throws SQLException {
		String sql = "select count(1) as qtd from usuario where senha = '" + senha + "'";

		PreparedStatement pstValidarSenha = connection.prepareStatement(sql); // Preparo a SQL e passo para o editar
		ResultSet resultSet = pstValidarSenha.executeQuery(); // jogo o resultado da query para o resultset

		if (resultSet.next()) { // Se encontrar o login que está vindo no resultset ele entra na condição;

			return resultSet.getInt("qtd") <= 0; // Verificar se o resultado da sql retorna menor igual a 0, se for sim,
													// nao tem dados no banco
													// ai deixa cadastrar o login vindo do parâmetro.

		}
		return false; // Se nao, retorna falso e nao deixa cadastrar.

	}

	public boolean validarSenhaUpdate(String senha, String id) throws SQLException {
		String sql = "select count(1) as qtd from usuario where senha = '" + senha + "' and id <>" + id;

		PreparedStatement pstValidarSenhaUpdate = connection.prepareStatement(sql); // Preparo a SQL e passo para o
																					// editar
		ResultSet resultSet = pstValidarSenhaUpdate.executeQuery(); // jogo o resultado da query para o resultset

		if (resultSet.next()) { // Se encontrar o login que está vindo no resultset ele entra na condição;

			return resultSet.getInt("qtd") <= 0; // Verificar se o resultado da sql retorna menor igual a 0, se for sim,
													// nao tem dados no banco
													// ai deixa cadastrar o login vindo do parâmetro.

		}
		return false; // Se nao, retorna falso e nao deixa cadastrar.

	}

	public void atualizar(BeanCursoJsp usuario) {

		try {
			
			StringBuilder sql = new StringBuilder();

			sql.append(" update usuario set login = ?, senha = ?, nome = ?, telefone = ? ");
			sql.append(" ,cep = ?, rua = ?, bairro = ?, cidade = ? ");
			sql.append(" ,uf = ?, ibge = ?, ativo = ? ");

			if (usuario.isAtualizarImagem()) {
				sql.append(" ,fotobase64 = ?, contenttype = ? ");
			}

			if (usuario.isAtualizarPdf()) {
				sql.append(" ,curriculobase64 = ?, contenttypecurriculo = ? ");
			}

			if (usuario.isAtualizarImagem()) {
				sql.append(" ,fotobase64miniatura = ? ");
			}

			sql.append(" where id= " + usuario.getId());

			PreparedStatement update = connection.prepareStatement(sql.toString());
			update.setString(1, usuario.getLogin());
			update.setString(2, usuario.getSenha());
			update.setString(3, usuario.getNome());
			update.setString(4, usuario.getTelefone());
			update.setString(5, usuario.getCep());
			update.setString(6, usuario.getRua());
			update.setString(7, usuario.getBairro());
			update.setString(8, usuario.getCidade());
			update.setString(9, usuario.getUf());
			update.setString(10, usuario.getIbge());
			update.setBoolean(11, usuario.isAtivo());
			update.setString(12, usuario.getSexo());

			if (usuario.isAtualizarImagem()) {
				update.setString(13, usuario.getFotoBase64());
				update.setString(14, usuario.getContentType());
			}

			if (usuario.isAtualizarPdf()) {
				
				if (usuario.isAtualizarPdf() && !usuario.isAtualizarImagem()) {
					
					update.setString(13, usuario.getCurriculoBase64());
					update.setString(14, usuario.getContentTypeCurriculo());
				}else {
					update.setString(15, usuario.getCurriculoBase64());
					update.setString(16, usuario.getContentTypeCurriculo());
				}
				
				
			} else {
				if (usuario.isAtualizarImagem()) {
					update.setString(15, usuario.getFotoBase64Miniatura());
				}
			}
			if (usuario.isAtualizarImagem() && usuario.isAtualizarPdf()) {
				update.setString(17, usuario.getFotoBase64Miniatura());
			}

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
