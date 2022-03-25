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
	   
	   <title>Inserisci Nuovo Dipendente</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<%-- se l'attributo in request ha errori --%>
					<spring:hasBindErrors  name="insert_dipendente_attr">
						<%-- alert errori --%>
						<div class="alert alert-danger " role="alert">
							Attenzione!! Sono presenti errori di validazione
						</div>
					</spring:hasBindErrors>
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Inserisci nuovo dipendente</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form:form modelAttribute="insert_dipendente_attr" method="post" action="save" novalidate="novalidate" class="row g-3">
					
							
								<div class="col-md-6">
									<label for="nome" class="form-label">Nome <span class="text-danger">*</span></label>
									<spring:bind path="nome">
										<input type="text" name="nome" id="nome" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il nome" value="${insert_dipendente_attr.nome }" required>
									</spring:bind>
									<form:errors  path="nome" cssClass="error_field" />
								</div>
								
								<div class="col-md-6">
									<label for="cognome" class="form-label">Cognome <span class="text-danger">*</span></label>
									<spring:bind path="cognome">
										<input type="text" name="cognome" id="cognome" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il cognome" value="${insert_dipendente_attr.cognome }" required>
									</spring:bind>
									<form:errors  path="cognome" cssClass="error_field" />
								</div>
								
								<div class="col-md-6">
									<label for="codFis" class="form-label">Codice Fiscale <span class="text-danger">*</span></label>
									<spring:bind path="codFis">
										<input type="text" name="codFis" id="codFis" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il codice fiscale" value="${insert_dipendente_attr.codFis }" required>
									</spring:bind>
									<form:errors  path="codFis" cssClass="error_field" />
								</div>
							
								 <div class="col-md-6">
								<label for="dataNascita" class="form-label">Data di Nascita</label>
                        		<input class="form-control" id="dataNascita" type="date" placeholder="dd/MM/yy"
                            		title="formato : gg/mm/aaaa"  name="dataNascita" >
								</div>
								
								<div class="col-md-6">
									<label for="dataAssunzione" class="form-label">Data di Assunzione</label>
	                        		<input class="form-control" id="dataAssunzione" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataAssunzione" >
								</div>
								
								<div class="col-md-6">
									<label for="dataDimissioni" class="form-label">Data Dimissioni</label>
	                        		<input class="form-control" id="dataDimissioni" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataDimissioni" >
								</div>
								
								<div class="col-md-3">
									<label for="sesso" class="form-label">Sesso</label>
									    <select class="form-select " id="sesso" name="sesso" >
									    	<option value="" selected> - Selezionare - </option>
									      	<option value="MASCHIO" >Maschio</option>
									    	<option value="FEMMINA">Femmina</option>
								    	</select>
								</div>
								
								
								
								<%--  checkbox ruoli 	--%>
								<%-- facendolo con i tag di spring purtroppo viene un po' spaginato quindi aggiungo class 'a mano'	--%>
								<div class="col-md-6 form-check" id="ruoliDivId">
									<p>Ruoli:</p>
									<form:checkboxes itemValue="id" itemLabel="codice"  element="div class='form-check'" items="${ruoli_totali_attr}" path="utente" />
								</div>
								<script>
									$(document).ready(function(){
										
										$("#ruoliDivId :input").each(function () {
											$(this).addClass('form-check-input'); 
										});
										$("#ruoliDivId label").each(function () {
											$(this).addClass('form-check-label'); 
										});
										
									});
								</script>
								<%-- fine checkbox ruoli 	--%>
								
								
								<div class="col-12">
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
								</div>
		
						</form:form>
  
				    
				    
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