package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnection;

public class DAOlogin {

	private Connection conn;

	public DAOlogin() {
		conn = SingleConnection.getConnection();
	}

	public boolean validarLogin(String login, String senha) throws Exception {
		String sql = "select * from usuario where login = '" + login + "' and senha = '" + senha + "'";
		String sql2 = "select * from usuario2 where login = '" +login+ "' and senha = '" +senha+ "'";
		/*
		 * Responsavel por preparar o objeto para que possa ser aceito para conectar ao banco. 
		 */
		PreparedStatement statemant = conn.prepareStatement(sql);
		/*
		 * Responsavel por consultar o banco de dados
		 */
		ResultSet resultSet = statemant.executeQuery();
		
		if (resultSet.next()) {

			System.out.println("Cadastro encontrado");
			return true;
		} else {
			System.out.println("Cadastro não encontrado");
			return false;

		}

	}
}
