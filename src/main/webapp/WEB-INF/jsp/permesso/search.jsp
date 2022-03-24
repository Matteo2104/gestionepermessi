<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jqueryUI/jquery-ui.min.css" />
		<style>
			.ui-autocomplete-loading {
				background: white url("../assets/img/jqueryUI/anim_16x16.gif") right center no-repeat;
			}
			.error_field {
		        color: red; 
		    }
		</style> 
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
		
		
		
							<form method="post" action="${pageContext.request.contextPath}/permesso/list" class="row g-3" >
							
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
								
								<!-- FORM PER L'INSERIMENTO DEL DIPENDENTE -->
								<div class="col-md-6">
										<label for="dipendenteSearchInput" class="form-label">Dipendente:</label>
											<input class="form-control ${status.error ? 'is-invalid' : ''}" type="text" id="dipendenteSearchInput"
												name="dipendenteInput" value="${permesso_search_attr.dipendente.nome}${empty permesso_search_attr.dipendente.nome?'':' '}${permesso_search_attr.dipendente.cognome}">
										<input type="hidden" name="dipendente.id" id="dipendenteId" value="${permesso_search_attr.dipendente.id}">
								</div>
								
								
								<div class="col-12">
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									
									<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
								</div>
		
						</form>
  
				    			<%-- FUNZIONE JQUERY UI PER AUTOCOMPLETE --%>
								<script>
									$("#dipendenteSearchInput").autocomplete({
										 source: function(request, response) {
										        $.ajax({
										            url: "../dipendente/searchDipendentiAjax",
										            datatype: "json",
										            data: {
										                term: request.term,   
										            },
										            success: function(data) {
										                response($.map(data, function(item) {
										                    return {
											                    label: item.label,
											                    value: item.value
										                    }
										                }))
										            }
										        })
										    },
										//quando seleziono la voce nel campo deve valorizzarsi la descrizione
									    focus: function(event, ui) {
									        $("#dipendenteSearchInput").val(ui.item.label)
									        return false
									    },
									    minLength: 2,
									    //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
									    select: function( event, ui ) {
									    	$('#dipendenteId').val(ui.item.value);
									    	//console.log($('#registaId').val())
									        return false;
									    }
									});
								</script>
				    
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