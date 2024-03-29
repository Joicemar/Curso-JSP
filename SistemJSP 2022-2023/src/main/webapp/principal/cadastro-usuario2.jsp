<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

<body>
	<!-- Pre-loader start -->
	<jsp:include page="theme-loader.jsp"></jsp:include>
	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="navbarmainmenu.jsp"></jsp:include>

					<div class="pcoded-content">
						<!-- Page-header start -->
						<jsp:include page="page-header.jsp"></jsp:include>
						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">
										<!-- task, page, download counter  start -->
										<!--  project and team member end -->

										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">
														<h4 class="sub-title">Cadastro de usu�rio</h4>

														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="post" id="formUser">
															
															<input type="hidden" name="action" id="action" value="">
															
															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id" class="form-control" readonly="readonly"
																	value="${modelLogin.id }"> 
																	<span class="form-bar"></span> 
																	<label class="float-label">ID:</label>
															</div>
															<div class="form-group form-default">
																<input type="text" name="nome" id="nome" class="form-control" required="required"
																	value="${modelLogin.nome }">
																	 <span class="form-bar"></span>
																	 <label class="float-label">Nome:</label>
															</div>
															<div class="form-group form-default">
																<input type="text" name="login" id="login" class="form-control" required="required"	value="${modelLogin.login }">
																 <span class="form-bar"></span> <label class="float-label">Login:</label>
															</div>
															<div class="form-group form-default">
																<input type="email" name="email" id="email"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.email }">
																<span class="form-bar"></span> <label
																	class="float-label">Email (exemplo@gmail.com)</label>
															</div>
															<div class="form-group form-default">
																<input type="password" name="senha" id="senha" class="form-control" required="required" autocomplete="off"> 
																<span class="form-bar"></span>
																<label class="float-label">Senha:</label>
															</div>
															<button type="button" class="btn btn-success waves-effect waves-light" onclick="limparFormulario();">Novo</button>
															<button class="btn btn-primary waves-effect waves-light">Salvar</button>
															<button type="button" class="btn btn-danger" onclick="criarDelete();">Excluir</button>
															
															<button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#ModalUsuario" >
															 Pesquisar</button>
 														
														
														</form>

													</div>
												</div>
											</div>
										</div>
									</div>
									<span id="msg">${msg}</span>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Warning Section Ends -->

	<jsp:include page="javascriptfile.jsp"></jsp:include>
	
	<!-- Modal -->
<div class="modal fade" id="ModalUsuario" tabindex="-1" role="dialog" aria-labelledby=exampleModalLabel aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Pesquisa de Usu�rio</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
   		<!-- Corpo do Modal -->
	      <div class="modal-body">
	      	<!-- Button do Modal -->
				<div class="input-group mb-3">
			  <input type="text" class="form-control" placeholder="Nome" id="buscarNome" aria-label="nome" aria-describedby="basic-addon2">
			  <div class="input-group-append">
			    <button class="btn btn-success" type="button" onclick="buscarUsuario();">Buscar</button>
			  </div>
			</div>
			
			<!-- Tabela de informa��es do usu�rio -->
				<table class="table table-dark">
			  <thead>
			    <tr>
			      <th scope="col">ID</th>
			      <th scope="col">Nome</th>
			      <th scope="col">Ver</th>
			    </tr>
			  </thead>
			  <tbody>

			  </tbody>
			</table>
			
			
			</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
      </div>
      </div>
    </div>
  </div>

	<script type="text/javascript">
	
	function buscarUsuario(){
		 var bucarNome = document.getElementById('buscarNome').value;
		 if(bucarNome != null && bucarNome != '' && bucarNome.trim() != ''){
			 
			 var urlAction = document.getElementById('formUser').action;
			 $.ajax({
			     
			     method: "get",
			     url : urlAction,
			     data : "nomeBusca=" + idUser + '&action=buscarUserAjax',
			     success: function (response) {
				 
			     }
			     
			 }).fail(function(xhr, status, errorThrown){
			    alert('Erro ao busar usu�rio por nome: ' + xhr.responseText);
			 });
		 }
	}
	
	function criarDelete() {
	    
	    if(confirm('Deseja realmente excluir os dados?')) {
		
		    document.getElementById("formUser").method = 'get';
		    document.getElementById("action").value = 'deletar';
		    document.getElementById("formUser").submit();
	    }
	    
	}
	function limparFormulario() {

		var elementos = document.getElementById("formUser").elements; /*Retorna os elementos html dentro do form*/

		for (p = 0; p < elementos.length; p++) {
			elementos[p].value = '';
		}
	}
	
	function criarDeleteComAjax() {
	    
	    if (confirm('Deseja realmente excluir os dados?')){
		
		 var urlAction = document.getElementById('formUser').action;
		 var idUser = document.getElementById('id').value;
		 
		 $.ajax({
		     
		     method: "get",
		     url : urlAction,
		     data : "id=" + idUser + '&action=deletarajax',
		     success: function (response) {
			 
			  limparForm();
			  document.getElementById('msg').textContent = response;
		     }
		     
		 }).fail(function(xhr, status, errorThrown){
		    alert('Erro ao deletar usu�rio por id: ' + xhr.responseText);
		 });
		 
		  
	    }
	    
	}
	</script>
</body>

</html>
