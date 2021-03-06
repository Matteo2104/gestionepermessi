<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	
	   <title>Elimina Richiesta di Permesso</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Sei sicuro di voler rimuovere questa richiesta?</h5>
					    </div>
					    <form method="post" action="${pageContext.request.contextPath}/permesso/remove" class="row g-3" >
					    
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Id:</dt>
							  <dd class="col-sm-9">${delete_richiesta_attr.id}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Tipologia di Permesso:</dt>
							  <dd class="col-sm-9">${delete_richiesta_attr.tipoPermesso}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Codice Certificato:</dt>
							  <dd class="col-sm-9">${delete_richiesta_attr.codiceCertificato}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Dipendente:</dt>
							  <dd class="col-sm-9">${delete_richiesta_attr.dipendente.nome} ${delete_richiesta_attr.dipendente.cognome}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data Inizio:</dt>
							  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${delete_richiesta_attr.dataInizio}" /></dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data Fine:</dt>
							  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${delete_richiesta_attr.dataFine}" /></dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Approvato:</dt>
							  <dd class="col-sm-9">${delete_richiesta_attr.approvato?'Si':'No'}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Note:</dt>
							  <dd class="col-sm-9">${delete_richiesta_attr.note}</dd>
					    	</dl>
					    	
					    	<!-- info Attachment -->
					    	<!-- 
					    	<p>
							  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
							    Info Ruoli
							  </a>
							</p>
							<div class="collapse" id="collapseExample">
							  <div class="card card-body">
							  <c:forEach items="${show_ruoli_attr}" var="ruoloItem">
								  	<dl class="row">
									  <dt class="col-sm-3 text-right">Descrizione:</dt>
									  <dd class="col-sm-9">${ruoloItem.descrizione}</dd>
								   	</dl>
								   	<dl class="row">
									  <dt class="col-sm-3 text-right">Codice:</dt>
									  <dd class="col-sm-9">${ruoloItem.codice}</dd>
								   	</dl>
							   </c:forEach>
							  </div>
							   -->
							<!-- end info Ruoli -->
					    	
					    	
					    </div>
					    <!-- end card body -->
					    
					    <div class='card-footer'>
					        <a href="${pageContext.request.contextPath}/permesso/listAllPersonali" class='btn btn-outline-secondary' style='width:100px'>
					            <i class='fa fa-chevron-left'></i> Indietro
					        </a>
					        <button type="submit" name="idImpiegato" value="${delete_richiesta_attr.id}" id="submit" class="btn btn-danger">Rimuovi</button>
					    </div>
					    
					    
					    </form>
					<!-- end card -->
					
					</div>	
			  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>