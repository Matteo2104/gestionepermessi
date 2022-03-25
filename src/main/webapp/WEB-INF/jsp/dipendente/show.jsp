<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<!-- Common imports in pages -->
	<jsp:include page="../header.jsp" />
	<title>Visualizza Utente</title>
	
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp" />
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  	<div class="container">
			
			<div class='card'>
			    <div class='card-header'>
			        Visualizza dettaglio Utente
			    </div>
			
			    <div class='card-body'>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Id:</dt>
					  <dd class="col-sm-9">${show_dipendente_attr.id}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Nome:</dt>
					  <dd class="col-sm-9">${show_dipendente_attr.nome}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Cognome:</dt>
					  <dd class="col-sm-9">${show_dipendente_attr.cognome}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Codice Fiscale:</dt>
					  <dd class="col-sm-9">${show_dipendente_attr.codFis}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Email:</dt>
					  <dd class="col-sm-9">${show_dipendente_attr.email}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Data Nascita:</dt>
					  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_dipendente_attr.dataNascita}" /></dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Data Assunzione:</dt>
					  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_dipendente_attr.dataAssunzione}" /></dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Data Dimissioni:</dt>
					  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_dipendente_attr.dataDimissioni}" /></dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Sesso:</dt>
					  <dd class="col-sm-9">${show_dipendente_attr.sesso}</dd>
			    	</dl>
			    	
			    	
			    	<!-- info Richieste Permesso -->
			    	<sec:authorize access="hasAnyRole('ROLE_DIPENDENTE_USER', 'ROLE_BO_USER')">
			    	<p>
					  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
					    Richieste Permesso
					  </a>
					</p>
					<div class="collapse" id="collapseExample">
					  <div class="card card-body">
					  <c:forEach items="${show_richieste_attr}" var="richiestaItem">
						  	<dl class="row">
							  <dt class="col-sm-3 text-right">Tipologia Permesso:</dt>
							  <dd class="col-sm-9">${richiestaItem.tipoPermesso}</dd>
						   	</dl>
						   	<dl class="row">
					  			<dt class="col-sm-3 text-right">Data Inizio:</dt>
					  			<dd class="col-sm-9"><fmt:formatDate type = "date" value = "${richiestaItem.dataInizio}" /></dd>
			    				</dl>
			    			<dl class="row">
					  			<dt class="col-sm-3 text-right">Data Fine:</dt>
					  			<dd class="col-sm-9"><fmt:formatDate type = "date" value = "${richiestaItem.dataFine}" /></dd>
			    			</dl>
			    			
						   	<dl class="row">
							  <dt class="col-sm-3 text-right">Approvato:</dt>
							  <dd class="col-sm-9">${richiestaItem.approvato?'Si':'No'}</dd>
						   	</dl>
						   	
						   	<dl class="row">
							  <dt class="col-sm-3 text-right">Codice Certificato:</dt>
							  <dd class="col-sm-9">${richiestaItem.codiceCertificato}</dd>
						   	</dl>
						   	
						   	<dl class="row">
							  <dt class="col-sm-3 text-right">Note:</dt>
							  <dd class="col-sm-9">${richiestaItem.note}</dd>
						   	</dl>
					   </c:forEach>
					  </div>
					<!-- end info Ruoli -->
					</div>
			    	</sec:authorize>
			    <!-- end card body -->
			    </div>
			    
			    <div class='card-footer'>
			        <a href="${pageContext.request.contextPath }/dipendente" class='btn btn-outline-secondary' style='width:80px'>
			            <i class='fa fa-chevron-left'></i> Back
			        </a>
			    </div>
			<!-- end card -->
			</div>	
	
		<!-- end container -->  
		</div>
		
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>