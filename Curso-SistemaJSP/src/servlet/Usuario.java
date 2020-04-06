package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BeanCursoJSP;
import dao.DAOusuario;

/**
 * Servlet implementation class Usuario
 */
/*
 * Proxima linha é o comando reconhecido na pagina jsp que encontrara esta
 * classe
 */
@WebServlet("/salvarUsuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAOusuario daoUsuario = new DAOusuario();

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.deletar(user);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}
			else if(acao.equalsIgnoreCase("editar")) {
				
				BeanCursoJSP usuario = daoUsuario.consultar(user);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", usuario);
				view.forward(request, response);
			}
			else {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		doGet(request, response);
		// Recebendo os parametros da página JSP
		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		BeanCursoJSP usuario = new BeanCursoJSP();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		// Este '?' é operador ternário, serve como um if, e caso não de certo o ':' executa a outra operação
		usuario.setId(!id.isEmpty()? Long.parseLong(id): 0);
		
		if(id == null || id.isEmpty()) {
			daoUsuario.salvar(usuario);			
		}else {
			daoUsuario.atualizar(usuario);
		}

		try {
			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			// "usuarios" é a variavel que pegara a lista de usuarios que será visto na
			// página.
			request.setAttribute("usuarios", daoUsuario.listar());
			// forward redireciona a
			view.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
