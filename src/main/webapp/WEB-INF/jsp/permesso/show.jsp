<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="it" class="h-100">
<head>
	<!-- Common imports in pages -->
	<jsp:include page="../header.jsp" />
	<title>Visualizza Dettaglio Richiesta Permesso</title>
	
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp" />
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	
	  	<div class="container">
			
			
			<div class='card'>
			
			    <div class='card-header'>
			        Visualizza Dettaglio Richiesta Permesso
			    </div>
			    
			    
				<form method="post" action="${pageContext.request.contextPath}/permesso/approva">
			    <div class='card-body'>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Id:</dt>
					  <dd class="col-sm-9">${show_richiesta_attr.id}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Tipologia di Permesso:</dt>
					  <dd class="col-sm-9">${show_richiesta_attr.tipoPermesso}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Codice Certificato:</dt>
					  <dd class="col-sm-9">${show_richiesta_attr.codiceCertificato}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Dipendente:</dt>
					  <dd class="col-sm-9">${show_richiesta_attr.dipendente.nome} ${show_richiesta_attr.dipendente.cognome}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Data Inizio:</dt>
					  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_richiesta_attr.dataInizio}" /></dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Data Fine:</dt>
					  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_richiesta_attr.dataFine}" /></dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Approvato:</dt>
					  <dd class="col-sm-9">${show_richiesta_attr.approvato?'Si':'No'}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Note:</dt>
					  <dd class="col-sm-9">${show_richiesta_attr.note}</dd>
			    	</dl>
			    	
			    	
			    	
			    	 <p>
					  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
					    Info Allegato
					  </a>
					</p>
					<div class="collapse" id="collapseExample">
					  <div class="card card-body">
					  	<dl class="row">
					  		<dt class="col-sm-3 text-right">Id: </dt>
					  		<dd class="col-sm-9" >${show_richiesta_attr.attachment.id}</dd>
					  	</dl>
					  	<dl class="row">
					  		<dt class="col-sm-3 text-right">Nome File: </dt>
					  		<dd class="col-sm-9">${show_richiesta_attr.attachment.nomeFile}</dd>
					  	</dl>
					  	<dl class="row">
					  		<dt class="col-sm-3 text-right">Tipo File: </dt>
					  		<dd class="col-sm-9">${show_richiesta_attr.attachment.contentType }</dd>
					  	</dl>
					  	
					  	<a class="btn  btn-sm btn-outline-secondary col-md-3" href="${pageContext.request.contextPath}/permesso/showAttachment/${show_richiesta_attr.attachment.id }">Visualizza Allegato</a>
					  	
					  	
					  </div>
					  
			
					</div>
					 <!-- end info Attachment -->
					
					
			    	
			    	<div class='card-footer'>
			       
			    
					
					
					<sec:authorize access="hasAnyRole({'BO_USER', 'ADMIN_USER'})">
			        	<c:if test="${not show_richiesta_attr.approvato}">
			        		<button class="btn btn-primary" type="submit" >Approva</button>
			        		<input type="hidden" id="idRichiesta" name="idRichiesta" value="${show_richiesta_attr.id}">
			        	</c:if>
			        	
				    	<a href="${pageContext.request.contextPath}/permesso" class='btn btn-outline-secondary' >
				            <i class='fa fa-chevron-left'></i> Indietro
				        </a>
				    </sec:authorize>
				    
				    
			        <sec:authorize access="hasRole('DIPENDENTE_USER')">
				    		<a href="${pageContext.request.contextPath }/permesso/listAllPersonali" class='btn btn-outline-secondary' >
				            	<i class='fa fa-chevron-left'></i> Indietro
				           	</a>
				    </sec:authorize>
				        
			    </div>
			    	
			    	<!-- info Attachment -->
			    	
			    	
			    	</form>  
			    <!-- end card body -->
			    </div>
			    
			    
			<!-- end card -->
			</div>	
	
		<!-- end container -->  
		</div>
		
		
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>