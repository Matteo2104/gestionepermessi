<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	 <style>
		    .error_field {
		        color: red; 
		    }
		</style>
	   
	   <title>Richiesta Permesso</title>
	 </head>
	   <body class="d-flex flex-column h-100" onload="javascript:change()">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<%-- se l'attributo in request ha errori --%>
			  		<!--  
					<spring:hasBindErrors  name="insert_richiesta_attr">
						<%-- alert errori --%>
						<div class="alert alert-danger " role="alert">
							Attenzione!! Sono presenti errori di validazione
						</div>
					</spring:hasBindErrors>
					-->
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Richiesta Permesso</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
							
								
								
								
								
							<form enctype="multipart/form-data"  method="post" action="save"  class="row g-3">
							
									<div class="col-md-10" >
										<label for="tipoPermesso" class="form-label">Tipologia Permesso </label>
								    	<select class="form-select" id="tipoPermesso" name="tipoPermesso" onchange="javascript:change()">
								    		<option value="" selected> - Selezionare - </option>
								      		<option value="FERIE" >Ferie</option>
								      		<option value="MALATTIA">Malattia</option>
								    	</select>
									</div>
									
									
								
									<div class="col-md-6" id="codiceCertificato">
										<label for="descrizione" class="form-label">Codice Certificato <span class="text-danger">*</span></label>
											<input type="text" class="form-control " name="codiceCertificato" id="codiceCertificato" placeholder="Inserire codice certificato" >
									</div>
									
									<div class="col-md-6" id="attachment">
									  <label for="allegato" class="form-label">Allegato <span class="text-danger">*</span></label>
									  <input class="form-control" type="file" id="allegato" name="file" required>
									</div>
									
									
									<div class="form-check">
									  <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" >
									  <label class="form-check-label" for="flexCheckDefault">
									    Giorno singolo
									  </label>
									</div>
									
									
									<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='' />
								<div class="col-md-5">
									<label for="dataInizio" class="form-label">Data di Inizio <span class="text-danger">*</span></label>
                        			
	                        		<input class="form-control ${status.error ? 'is-invalid' : ''}" id="dataInizio" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataInizio" required 
	                            		value="${parsedDate}" >
		                            
	                            	
								</div>
								
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='' />
								<div class="col-md-5">
									<label for="dataFine" class="form-label">Data Fine <span class="text-danger">*</span></label>
	                        		<input class="form-control ${status.error ? 'is-invalid' : ''}" id="dataFine" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataFine" required 
	                            		value="${parsedDate}" >
								</div>
								
								
								
								<div class="col-md-10">
									<label for="note" class="form-label">Note </label>
										<textarea name="note" id="note" class="form-control w-50" placeholder="Inserire note"  ></textarea>
								</div>
								
									<div class="col-12">
										<button type="submit" name="confirmButton"  id="confirmButton" class="btn btn-primary"  >Conferma</button>
									</div>
			
							</form>
								
								
								
								
								
								
						
  
				    	<script>
				    		function change() {
				    			var selectBox = document.getElementById("tipoPermesso");
				    		    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
				    		    
				    		   
				    		    
				    		    if (selectedValue == "MALATTIA") {
				    		    	document.getElementById("codiceCertificato").style.display = "block";
				    		    	document.getElementById("attachment").style.display = "block";
				    		    	
				    		    } else {
				    		    	document.getElementById("codiceCertificato").style.display = "none";
				    		    	document.getElementById("attachment").style.display = "none";
				    		    }
				    			
				    			
				    		}
				    		
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