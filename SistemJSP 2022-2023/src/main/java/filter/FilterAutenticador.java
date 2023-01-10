package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/principal/*"})/*Intercepta todas as requisições que vierem do projeto ou mapeamento*/
public class FilterAutenticador implements Filter {

	private static Connection connection;
	
    public FilterAutenticador() {
    	
    }
    /*Encerra os processos quando o servidor é parado*/
    /*Mataria os processos de conexão com o banco*/
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*Intercepta todas as requisilões e as respostas no sistema. Tudo que fizer no sistema vai ser feito por aqui.
	 * validação de autenticação. Dar commit e rolback de transações no banco. 
	 * Validar e fazer direcionamento de páginas*/
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try {
		HttpServletRequest req = (HttpServletRequest) request; //Preparando interceptador de requisições
		HttpSession session = req.getSession(); //Passando a seção para o objeto que lida com elas
		
		//Pegando o atributo atual setado na servlet noutra classe
		String usuarioLogado = (String) session.getAttribute("usuario");
		//Pode pegar qualquer url
		String urlParaAutenticar = req.getServletPath();/*url que está sendo acessado*/
		
		/*Valida se está logado senão redireciona para a tela de login*/
		if(usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {/*Não está logado*/
			
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url="+urlParaAutenticar);
			request.setAttribute("msg", "Por favor realize o login");
			redireciona.forward(request, response);//Comando de redirecionamento
			return; //para parar e execução e redirecioar para o login do sistema
		}else {
			chain.doFilter(request, response);
		}
		connection.commit();/*Deu tudo certo, então comita as alterações no banco de dados*/
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", "Informe o login e senha corretamente!");
			redirecionar.forward(request, response);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	/*Inicia os processos ou recursos quando o servidor sobe o projeto */
	/*Inicia conexão com o banco*/
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
	}

}
