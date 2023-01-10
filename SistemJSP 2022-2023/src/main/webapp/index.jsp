<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">

<title>Página de Login</title>
<!-- 
<link rel="stylesheet" href="style.css">
 -->
<style type="text/css">
body {
	background-color: #B2BFFF;
}

form {
	position: absolute;
	top: 30%;
	left: 33%;
	right: 33%;
}

h1 {
	position: absolute;
	top: 15%;
	left: 35%;
}

.msg {
	position: absolute;
	top: 60%;
	left: 41%;
	font-size: 15px;
	color: red;
}
</style>

</head>
<body>
	<h1>Bem Vindo ao Curso JSP</h1>
	<form action="<%= request.getContextPath() %>/ServletLogin" method="post"
		class="row g-3 needs-validation" novalidate>
		<!-- hidden quer dizer que é um input oculto do usuario -->
		<!-- pega o da classe java responsável que vai passar a url -->
		<input type="hidden" value="<%=request.getParameter("url")%>"
			name="url">
		<div class="input-group mb-3">
			<input name="login" id="login" type="text" class="form-control" 
				placeholder="Seu Login..." aria-label="Username"
				aria-describedby="basic-addon1" autocomplete="off">
			<div class="invalid-feedback">Obrigatório</div>
		</div>
		<div class="input-group mb-3">
			<input name="senha" id="senha" type="password" class="form-control"
				placeholder="Sua Senha..." aria-label="Username"
				aria-describedby="basic-addon1">
			<div class="invalid-feedback">Obrigatório</div>
		</div>
		<input type="submit" value="Acessar" class="btn btn-primary">
	</form>

	<h5 class="msg">${msg}</h5>

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>


	<script type="text/javascript">
		//Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
			'use strict'

			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.querySelectorAll('.needs-validation')

			// Loop over them and prevent submission
			Array.prototype.slice.call(forms).forEach(function(form) {
				form.addEventListener('submit', function(event) {
					if (!form.checkValidity()) {
						event.preventDefault()
						event.stopPropagation()
					}

					form.classList.add('was-validated')
				}, false)
			})
		})()
	</script>


</body>
</html>