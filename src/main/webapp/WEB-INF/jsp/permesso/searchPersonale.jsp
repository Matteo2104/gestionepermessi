<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
		
	   <title>Ricerca Richieste di Permesso</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Ricerca Richieste di Permesso</h5> 
				    </div>
				    <div class='card-body'>
		
		
		
							<form method="post" action="${pageContext.request.contextPath}/permesso/listPersonale" class="row g-3" >
							
								<div class="col-md-3">
									<label for="tipoPermesso" class="form-label">Tipologia Permesso </label>
								    <select class="form-select" id="tipoPermesso" name="tipoPermesso" >
								    	<option value="" selected> - Selezionare - </option>
								      	<option value="FERIE" >Ferie</option>
								      	<option value="MALATTIA" >Malattia</option>
								    </select>
								</div>
								
								<div class="col-md-3">
									<label for="approvato" class="form-label">Approvazione </label>
								    <select class="form-select" id="approvato" name="approvato" >
								    	<option value="" selected> - Selezionare - </option>
								      	<option value="${true }" >Approvato</option>
								      	<option value="${false }" >Non Approvato</option>
								    </select>
								</div>
							
								<div class="col-md-6">
									<label for="codiceCertificato" class="form-label">Codice Certificato </label>
									<input type="text" name="codiceCertificato" id="codiceCertificato" class="form-control" placeholder="Inserire codice certificato"  >
								</div>
								
								
							
								
								
								<div class="col-md-3">
									<label for="dataInizio" class="form-label">Data di Inizio </label>
                        			<input class="form-control" id="dataInizio" type="date" placeholder="dd/MM/yy"
                            			title="formato : gg/mm/aaaa"  name="dataInizio"   >
								</div>
								
								<div class="col-md-3">
									<label for="dataFine" class="form-label">Data Fine </label>
                        			<input class="form-control" id="dataFine" type="date" placeholder="dd/MM/yy"
                            			title="formato : gg/mm/aaaa"  name="dataFine"   >
								</div>
								
								<div class="col-md-6">
									<label for="note" class="form-label">Note </label>
									<input type="text" name="note" id="note" class="form-control" placeholder="Inserire note"  >
								</div>
								
								
								
								
								<div class="col-12">
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									<a class="btn btn-outline-primary ml-2" href="${pageContext.request.contextPath}/permesso/insert">Richiedi Permesso</a>
									<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
								</div>
		
						</form>
  
				    			
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>