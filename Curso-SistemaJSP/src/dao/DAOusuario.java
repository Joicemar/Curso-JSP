package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BeanCursoJSP;
import connection.SingleConnection;

public class DAOusuario {

	private Connection conn;

	public DAOusuario() {
		conn = SingleConnection.getConnection();
	}

	public void salvar(BeanCursoJSP usuario) {
		String sql = "insert into usuario(login, senha) values (?, ?)";
		try {
			PreparedStatement insert = conn.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.execute();
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	//Metodo de consulta e usado para listar os usuarios
	public List<BeanCursoJSP> listar() throws Exception { 
		List<BeanCursoJSP> listar = new ArrayList<BeanCursoJSP>();
		String sql = "select * from usuario";
		PreparedStatement statement = conn.prepareStatement(sql); 
		ResultSet result = statement.executeQuery();
		
		while(result.next()) {
			BeanCursoJSP bean = new BeanCursoJSP();
			bean.setLogin(result.getString("login"));
			bean.setSenha(result.getString("senha"));
			listar.add(bean);
		}
		return listar;
	}

	
	public void deletar(String login) {
		String sql = "delete from usuario where login = '"+login+"'";
		
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}
	/*
	 * 1 - Pega o sql e passa o nome do login
	 * 2 - Prepara um objeto para executar o sql
	 * 3 - Prepara um objeto para pegar o resultado da exeção
	 * 4 - verifica se existe no banco de dados usando o next
	 * 5 - Prepara um objeto para pegar os valores do banco de dados
	 * 6 - Seta os valores do banco de dados para esse objeto representante
	 * 7 - retorna esse objeto com os valores.   
	 */
	public BeanCursoJSP consultar(String login) throws SQLException {
		String sql = "select * from usuario where login = '"+login+"'";
		PreparedStatement stm = conn.prepareStatement(sql);
		ResultSet result = stm.executeQuery();
		
		if(result.next()) {
			BeanCursoJSP bean = new BeanCursoJSP();
			bean.setId(result.getLong("id"));
			bean.setLogin(result.getString("login"));
			bean.setSenha(result.getString("senha"));
			return bean;
		}
		return null;
	}

	public void atualizar(BeanCursoJSP usuario) {
		String sql = "update usuario set login = ?, senha = ? where id = "+ usuario.getId();
		PreparedStatement stm;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, usuario.getLogin());
			stm.setString(2, usuario.getSenha());
			stm.executeUpdate();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		
		
	}
	
}


