package model;

import java.io.Serializable;

public class ModelLogin implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String email;
	private String login;
	private String senha;
	
	public boolean isNovo() {
		
		if (this.id == null) {
			return true; /*Inserir novo*/
		}else if (this.id != null && this.id > 0) {
			return false; /*Atualizar*/
		}
			
		
		return id == null;
	}
	
	public ModelLogin(String login, String senha) {
		setLogin(login);
		setSenha(senha);
	}
	public ModelLogin() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id2) {
		this.id = id2;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
