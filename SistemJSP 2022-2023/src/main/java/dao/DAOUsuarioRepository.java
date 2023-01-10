package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;
/*Se comunica com a classe que pega da tela (ServletUsuarioController) e faz a mediação e processos com o banco de dados*/
public class DAOUsuarioRepository {
	
	private Connection connection;

	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin obj) throws SQLException {
		
		if( obj.isNovo()) {
			
		String sql = "INSERT INTO model_login(login, nome, email, senha) VALUES (?,?,?,?);";
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		
		preparedSql.setString(1, obj.getLogin());
		preparedSql.setString(2, obj.getNome());
		preparedSql.setString(3, obj.getEmail());
		preparedSql.setString(4, obj.getSenha());
		
		preparedSql.execute();
		connection.commit();
		}else {
			String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=? WHERE id =  "+obj.getId()+";";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString(1, obj.getLogin());
			prepareSql.setString(2, obj.getSenha());
			prepareSql.setString(3, obj.getNome());
			prepareSql.setString(4, obj.getEmail());
			
			prepareSql.executeUpdate();
			
			connection.commit();
		}
		return this.cosultaUsuario(obj.getLogin());
	}
	
	public ModelLogin cosultaUsuario(String login) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "SELECT * FROM model_login where upper(login) = upper('"+login+"');";
	
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			if(resultado.getString("login").equals(login)) {
				modelLogin.setId(resultado.getLong("id"));
				modelLogin.setNome(resultado.getString("nome"));
				modelLogin.setEmail(resultado.getString("email"));
				modelLogin.setLogin(resultado.getString("login"));
				return modelLogin;
			}
		}
		
		return modelLogin;
	}

	public boolean validarLogin(String login) throws SQLException {
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper('"+login+"')";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		if(resultado.next()) {
			return resultado.getBoolean("existe");
		}
//		if(resultado.next()) /*Simplificado*/
//		return resultado.getBoolean("existe");
		
		return false;
	}
	
	public void deletarUser(String idUser) throws SQLException {
		String sql = "DELETE FROM model_login WHERE id = ?";
		PreparedStatement prepareSQL = connection.prepareStatement(sql);
		prepareSQL.setLong(1, Long.parseLong(idUser)); /*Setar o parâmetro. Conver para long para o banco de dados */
		prepareSQL.executeUpdate();
		
		connection.commit();
	}
}
