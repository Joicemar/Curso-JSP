package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BeanCursoJSP;
import dao.DAOlogin;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOlogin daoLogin = new DAOlogin();

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

		// Colocando o bloco dentro de uma excessão
		try {
			// Iniciando um objeto do tipo Bean ou criando uma instancia.
			BeanCursoJSP beanCursoJsp = new BeanCursoJSP();

			String login = request.getParameter("login");
			String senha = request.getParameter("senha");

			// If de validação do login
			if (daoLogin.validarLogin (login, senha)) {
				// Acesso ok
				RequestDispatcher dispatcher = request.getRequestDispatcher("acessoLiberado.jsp");
				dispatcher.forward(request, response);
			} else {
				// acesso negado
				RequestDispatcher dispatcher = request.getRequestDispatcher("acessoNegado.jsp");
				dispatcher.forward(request, response);

			}
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
