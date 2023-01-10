package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {

	private Connection connection;
	
	
	public DAOLoginRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public boolean validarAutenticacao( ModelLogin modelLogin) {
		
		String sql = "select * from model_login where upper(login) = "
				+ "upper(?) and upper(senha) = upper(?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getSenha());
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				return true; //autenticado
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; //N�o autenticado
	}
	
}
