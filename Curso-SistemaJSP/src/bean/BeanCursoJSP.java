package bean;

public class BeanCursoJSP {
	
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	private String login;
	private String senha;

	
//	public boolean validar(String login, String senha) {
//		if(login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("admin")) {
//			return true;
//		}else {
//			return false;
//		}
//	}
	
	public void inserir(String login, String senha) {
		String sql = "INSERT INTO usuario(login, senha) VALUES (?, ?)";
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
