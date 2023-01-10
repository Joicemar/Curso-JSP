package servlets;

import java.io.IOException;
import java.sql.SQLException;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

/*Se comunica com a classe DAOUsuarioRepository responsável por salvar e devolver os registros
 * Se comunica com a tela e recebe as requisições */
@WebServlet("/ServletUsuarioController")
public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	public ServletUsuarioController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String deletar = request.getParameter("action");

			if (deletar != null && !deletar.isEmpty() && deletar.equalsIgnoreCase("deletar")) {
				String idUser = request.getParameter("id");
				daoUsuarioRepository.deletarUser(idUser);
				request.setAttribute("msg", "Usuário Deletado com sucesso!");
				request.getRequestDispatcher("principal/cadastro-usuario.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String msg = "Operação realizada com sucesso!";

			String id = (request.getParameter("id")); // Pegando os parâmetros do html pelo nome do input
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);

			if (daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				msg = "Já existe usuário com o mesmo login, informe outro login;";
			} else {
				if (modelLogin.isNovo()) {
					msg = "Gravdo com sucesso!";
				} else {
					msg = "Atualizado com sucesso!";
				}

				modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin);
			}

			request.setAttribute("msg", msg);
			request.setAttribute("modelLogin", modelLogin);
			request.getRequestDispatcher("principal/cadastro-usuario.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
//		RequestDispatcher redireciona = request.getRequestDispatcher("principal/cadastro-usuario.jsp");
//		request.setAttribute("modelLogin", modelLogin);
//		redireciona.forward(request, response);

	}

}
