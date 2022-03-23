<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="it" class="h-100">
<head>
	<jsp:include page="../header.jsp" />
	<title>Pagina dei risultati</title>
	
</head>
<body class="d-flex flex-column h-100">
	<jsp:include page="../navbar.jsp" />
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  <div class="container">
	
			<div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
			  ${successMessage}
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
			</div>
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
			  ${errorMessage}
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
			</div>
			
			<div class='card'>
			    <div class='card-header'>
			        <h5>Lista dei risultati</h5> 
			    </div>
			    <div class='card-body'>
			    	<a class="btn btn-primary " href="${pageContext.request.contextPath}/dipendente/insert">Add New</a>
			    	<a href="${pageContext.request.contextPath}/dipendente/search" class='btn btn-outline-secondary' >
				            <i class='fa fa-chevron-left'></i> Torna alla Ricerca
				        </a>
			    
			        <div class='table-responsive'>
			            <table class='table table-striped ' >
			                <thead>
			                    <tr>
			                        <th>Nome</th>
			                        <th>Cognome</th>
			                        <th>Codice Fiscale</th>
			                        <th>Azioni</th>
			                    </tr>
			                </thead>
			                <tbody>
			                	<c:forEach items="${dipendente_list_attribute}" var="dipendenteItem">
									<tr>
										<td>${dipendenteItem.nome }</td>
										<td>${dipendenteItem.cognome }</td>
										<td>${dipendenteItem.codFis }</td>
										
										<td>
											<a class="btn btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/dipendente/show/${dipendenteItem.id }">Visualizza</a>
											
											<sec:authorize access="hasRole('BO_USER')">
												<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="${pageContext.request.contextPath}/utente/edit/${utenteItem.id }">Edit</a>
												<a id="changeStatoLink_#_${utenteItem.id }" class="btn btn-outline-${utenteItem.isAttivo()?'danger':'success'} btn-sm link-for-modal" data-bs-toggle="modal" data-bs-target="#confirmOperationModal"  >${utenteItem.isAttivo()?'Disabilita':'Abilita'}</a>
											</sec:authorize>
											
											<!-- 
											<c:if test="${userAdmin}">
												<a id="resetPasswordLink_#_${utenteItem.id }" class="btn btn-sm btn-warning btn-sm link-for-modal" data-bs-toggle="modal" data-bs-target="#confirmOperationModal2">Reset Password</a>
											</c:if>
											 -->
										</td>
										
										
										
									</tr>
								</c:forEach>
			                </tbody>
			            </table>
			        </div>
			   
				<!-- end card-body -->			   
			    </div>
			</div>	
	
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
	
	
	<!-- Modal -->
	<div class="modal fade" id="confirmOperationModal" tabindex="-1"  aria-labelledby="confirmOperationModalLabel"
	    aria-hidden="true">
	    <div class="modal-dialog" >
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="confirmOperationModalLabel">Conferma Operazione</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div>
	            <div class="modal-body">
	                Continuare con l'operazione?
	            </div>
	            <form method="post" action="${pageContext.request.contextPath}/utente/cambiaStato" >
		            <div class="modal-footer">
		            	<input type="hidden" name="idUtenteForChangingStato" id="idUtenteForChangingStato">
		                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Chiudi</button>
		                <input type="submit" value="Continua"  class="btn btn-primary">
		            </div>
	            </form>
	        </div>
	    </div>
	</div>
	<!-- end Modal -->
	<script type="text/javascript">
		<!-- aggancio evento click al conferma del modal  -->
		$(".link-for-modal").click(function() {
			<!-- mi prendo il numero che poi sarà l'id. Il 18 è perché 'changeStatoLink_#_' è appunto lungo 18  -->
			var callerId = $(this).attr('id').substring(18);
			<!-- imposto nell'hidden del modal l'id da postare alla servlet -->
			$('#idUtenteForChangingStato').val(callerId);
		});
	</script>
	
	
	<!-- Modal -->
	<div class="modal fade" id="confirmOperationModal2" tabindex="-1"  aria-labelledby="confirmOperationModalLabel2"
	    aria-hidden="true">
	    <div class="modal-dialog" >
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="confirmOperationModalLabel2">Conferma Operazione</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div>
	            <div class="modal-body">
	                Sei sicuro di voler Resettare la password a questo utente?
	            </div>
	            <form method="post" action="${pageContext.request.contextPath}/utente/adminReset" >
		            <div class="modal-footer">
		            	<input type="hidden" name="idUtenteForResetPassword" id="idUtenteForResetPassword">
		                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Chiudi</button>
		                <input type="submit" value="Continua"  class="btn btn-primary">
		            </div>
	            </form>
	        </div>
	    </div>
	</div>
	<!-- end Modal -->
	<script type="text/javascript">
		<!-- aggancio evento click al conferma del modal  -->
		$(".link-for-modal").click(function(){
			<!-- mi prendo il numero che poi sarà l'id. Il 20 è perché 'resetPasswordLink_#_' è appunto lungo 20  -->
			var callerId = $(this).attr('id').substring(20);
			<!-- imposto nell'hidden del modal l'id da postare alla servlet -->
			$('#idUtenteForResetPassword').val(callerId);
		});
	</script>
	
</body>
</html>