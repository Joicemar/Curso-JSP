package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
/*
 *    @param url a database url of the form
 *    <code>jdbc:<em>subprotocol</em>:<em>subname</em></code>
 */
	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection conn = null;
	
	static {
		conectar();
	}
	
	public SingleConnection() {

	}
	
	private static void conectar() {
		try {
			if(conn == null) {
				System.out.println("Conectando ao banco");
				Class.forName("org.postgresql.Driver");//Driver do banco PostgreSQL
		
				System.out.println("Erro no getConnection");
				conn.setAutoCommit(false);
				conn = DriverManager.getConnection(banco, user, password);
				System.out.println("Erro no setAutoCommit");
				
			}
			
		} catch (Exception e) {
			throw new RuntimeException("Erro ao conectar ao banco de dados");
		}
		if(conn != null) {
			System.out.println("há conexão");
		}
	}
	
	public static Connection getConnection() {
		return conn;
	}

}
