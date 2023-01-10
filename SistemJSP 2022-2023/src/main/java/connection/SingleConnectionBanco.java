package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {

	private static String banco = "jdbc:postgresql://localhost:5433/curso-jsp?autoReconect=true";
	private static String user = "postgres";
	private static String senha = "admin";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public SingleConnectionBanco() {
		conectar();
	}
	
	private static void conectar() {
		try {
			if(connection == null) {
				Class.forName("org.postgresql.Driver"); //Carrega o driver de conex�o do banco
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false);//Para n�o efetuar altera��es no banco sem nosso comando
			}
		}catch (Exception e) {
			e.printStackTrace();//Mostrar qualquer erro no momento de conectar
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
}
