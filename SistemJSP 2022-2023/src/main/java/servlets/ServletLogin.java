package servlets;

import java.io.IOException;

import dao.DAOLoginRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;
/*O chamado Controller são as servlets ou ServletLoginControler*/
@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"}) /*Endereços que o form do html precisa usar para encontralos*/
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();

    public ServletLogin() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate(); // invalida a cessão
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			redirecionar.forward(request, response);
		}else { // Se o usuário não solicitou deslogar - logout
			doPost(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login"); /*Pega o name do input no html*/
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		try {
		if(login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			
			if(daoLoginRepository.validarAutenticacao(modelLogin)) {/*simulando login*/
				request.getSession().setAttribute("usuario", modelLogin.getLogin());
				
				if(url == null || url.equals("null")) {
					url = "principal/principal.jsp";
				}
				RequestDispatcher redirecionar = request.getRequestDispatcher(url);
				redirecionar.forward(request, response);
			}else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg", "Informe o login e senha corretamente!");
				redirecionar.forward(request, response);
			}
		}
		else {
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Informe o login e senha corretamente!");
			redirecionar.forward(request, response);
		}
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", "Informe o login e senha corretamente!");
			redirecionar.forward(request, response);
		}
	}

}
