<jsp:useBean id="bean" class="bean.BeanCursoJSP" type="bean.BeanCursoJSP"  scope="page"></jsp:useBean>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!--  action representa o nome da classe Java que está sendo enviado a informação, 
	e o post declara o método que existe na classe -->
	
	<form action="LoginServlet" method="post">
		<h2>Login:</h2>
		<input type="text" id="login" name="login">
		<br>
		<h3>Senha:</h3>	
		<input type="text" id="senha" name="senha">
		<br>
		<input type="submit" value="Logar">
	
	</form>

</body>
</html>