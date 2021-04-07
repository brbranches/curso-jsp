package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.BeanTelefone;
import connection.SingleConnection;

public class DaoTelefone {

	private Connection connection;


	public DaoTelefone() {
		connection = SingleConnection.getConnection();
	}

	public void salvarTelefone(BeanTelefone telefone) throws Exception {
		try {
			String sql = "insert into telefone(numero, tipo, usuario) values(?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuario());
			insert.execute();
			connection.commit();

		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

	}

	public ArrayList<BeanTelefone> listarTodos(Long user) throws Exception {
		
		ArrayList<BeanTelefone> listar = new ArrayList<BeanTelefone>();

		String sql = "select * from telefone where usuario = " + user;
		PreparedStatement list = connection.prepareStatement(sql);
		ResultSet resultSet = list.executeQuery();

		while (resultSet.next()) {
			BeanTelefone beanTelefone= new BeanTelefone();

			beanTelefone.setId(resultSet.getLong("id"));
			beanTelefone.setNumero(resultSet.getString("numero"));
			beanTelefone.setTipo(resultSet.getString("tipo"));
			beanTelefone.setUsuario(resultSet.getLong("usuario"));

			listar.add(beanTelefone);
		}
		return listar;
	}

	public void delete(String id) {
		try {
			String sql = "delete from telefone where id = '" + id + "'";
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

	
}
