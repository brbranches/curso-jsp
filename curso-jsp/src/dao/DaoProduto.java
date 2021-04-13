package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.BeanCategoria;
import beans.BeanProduto;
import connection.SingleConnection;

public class DaoProduto {

	private Connection connection;


	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}

	public void salvarProduto(BeanProduto produto) throws Exception {
		try {
			String sql = "insert into produto(nome, quantidade, preco, categoria_id) values(?,?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, produto.getNome());
			insert.setDouble(2, produto.getQuantidade());
			insert.setDouble(3, produto.getPreco());
			insert.setLong(4, produto.getCategoria_id());
			insert.execute();
			connection.commit();

		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

	}

	public ArrayList<BeanProduto> listarTodos() throws Exception {
		
		ArrayList<BeanProduto> listar = new ArrayList<BeanProduto>();

		String sql = "select * from produto";
		PreparedStatement list = connection.prepareStatement(sql);
		ResultSet resultSet = list.executeQuery();

		while (resultSet.next()) {
			BeanProduto beanProduto = new BeanProduto();

			beanProduto.setId(resultSet.getLong("id"));
			beanProduto.setNome(resultSet.getString("nome"));
			beanProduto.setQuantidade(resultSet.getDouble("quantidade"));
			beanProduto.setPreco(resultSet.getDouble("preco"));
			beanProduto.setCategoria_id(resultSet.getLong("categoria_id"));

			listar.add(beanProduto);
		}
		return listar;
	}
	
	
	public ArrayList<BeanCategoria> listaCategorias() throws Exception{
		
		ArrayList<BeanCategoria> listaCategorias = new ArrayList<BeanCategoria>();
		
		String sql = "select * from categoria";
		
		PreparedStatement list = connection.prepareStatement(sql);
		
		ResultSet resultSet = list.executeQuery();
		
		while (resultSet.next()) {
			BeanCategoria beanCategoria = new BeanCategoria();
			beanCategoria.setId(resultSet.getLong("id"));
			beanCategoria.setNome(resultSet.getString("nome"));
			
			listaCategorias.add(beanCategoria);
		}
		
		return listaCategorias;
	}

	public void delete(String id) {
		try {
			String sql = "delete from produto where id = '" + id + "'";
			PreparedStatement deletar = connection.prepareStatement(sql);
			deletar.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public BeanProduto consultar(String id) throws SQLException {
		String sql = "select * from produto where id = '" + id + "'";
		PreparedStatement consultar = connection.prepareStatement(sql);

		ResultSet resultSet = consultar.executeQuery();

		if (resultSet.next()) {
			BeanProduto beanProduto = new BeanProduto();

			beanProduto.setId(resultSet.getLong("id"));
			beanProduto.setNome(resultSet.getString("nome"));
			beanProduto.setQuantidade(resultSet.getDouble("quantidade"));
			beanProduto.setPreco(resultSet.getDouble("preco"));
			beanProduto.setCategoria_id(resultSet.getLong("categoria_id"));

			return beanProduto;

		}
		return null;

	}
	
	
	public boolean validarProduto(String nome) throws SQLException {
	String sql = "select count(1) as qtd from produto where nome = '" + nome + "'";
	PreparedStatement validar = connection.prepareStatement(sql);
	
	ResultSet resultSet = validar.executeQuery();
	
	if (resultSet.next()) {
		return resultSet.getInt("qtd") <=0;
	}
		return false;
		
	}

	
	public boolean validarProdutoUpdate(String nome, String id) throws SQLException {
		String sql = "select count(1) as qtd from produto where nome = '" + nome + "' and id <>" + id;
		
		PreparedStatement validarUpdateProduto = connection.prepareStatement(sql);
		ResultSet resultSet = validarUpdateProduto.executeQuery();
		
		if (resultSet.next()) {
			
			return resultSet.getInt("qtd") <= 0;
		}
		
		return false;
		
		
	}
	
	public void atualizar(BeanProduto produto) {
		try {
			String sql = "update produto set nome = ?, quantidade = ?, preco = ?, categoria_id = ? where id= '" + produto.getId() + "'";
			PreparedStatement update = connection.prepareStatement(sql);
			update.setString(1, produto.getNome());
			update.setDouble(2, produto.getQuantidade());
			update.setDouble(3, produto.getPreco());
			update.setLong(4, produto.getCategoria_id());
			update.executeUpdate();
			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();
				e.printStackTrace();	
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
	}
	
}
