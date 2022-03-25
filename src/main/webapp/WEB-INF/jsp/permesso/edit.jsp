<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100">
	<head>
		<jsp:include page="../header.jsp" />
		
	    <style>
		    .error_field {
		        color: red; 
		    }
		</style>
		<title>Modifica Richiesta di Permesso</title>
	</head>
	<body class="d-flex flex-column h-100">
		<jsp:include page="../navbar.jsp" />
		
			<!-- Begin page content -->
			<main class="flex-shrink-0">
				<div class="container">
		
					<%-- se l'attributo in request ha errori --%>
					<spring:hasBindErrors  name="edit_richiesta_attr">
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
					        <h5>Modifica Richiesta di Permesso</h5> 
					    </div>
					    <div class='card-body'>
				    
				    		<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
							<form:form modelAttribute="edit_richiesta_attr"  method="post" action="${pageContext.request.contextPath }/permesso/update" novalidate="novalidate" class="row g-3">
								<form:hidden path="id"/>
								
								<div class="col-md-3">
									<label for="tipoPermesso" class="form-label">Tipologia Permesso </label>
								    <select class="form-select" id="tipoPermesso" name="tipoPermesso" >
								    	<option value="${edit_richiesta_attr.tipoPermesso }" selected> ${edit_richiesta_attr.tipoPermesso } </option>
								      	<option value="FERIE" >Ferie</option>
								      	<option value="MALATTIA" >Malattia</option>
								    </select>
								</div>
								
								<div class="col-md-6">
									<label for="codiceCertificato" class="form-label">Codice Certificato </label>
									<spring:bind path="codiceCertificato">
										<input type="text" name="codiceCertificato" id="codiceCertificato" class="form-control" placeholder="Inserire codice certificato" value="${edit_richiesta_attr.codiceCertificato }" required >
									</spring:bind>
									<form:errors  path="codiceCertificato" cssClass="error_field" />
								</div>
								
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${edit_richiesta_attr.dataInizio}' />
								<div class="col-md-3">
									<label for="dataInizio" class="form-label">Data di Inizio <span class="text-danger">*</span></label>
                        			<spring:bind path="dataInizio">
	                        		<input class="form-control ${status.error ? 'is-invalid' : ''}" id="dataInizio" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataInizio" required 
	                            		value="${parsedDate}" >
		                            </spring:bind>
	                            	<form:errors  path="dataInizio" cssClass="error_field" />
								</div>
								
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${edit_richiesta_attr.dataFine}' />
								<div class="col-md-3">
									<label for="dataFine" class="form-label">Data Fine <span class="text-danger">*</span></label>
                        			<spring:bind path="dataFine">
	                        		<input class="form-control ${status.error ? 'is-invalid' : ''}" id="dataFine" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataFine" required 
	                            		value="${parsedDate}" >
		                            </spring:bind>
	                            	<form:errors  path="dataFine" cssClass="error_field" />
								</div>
								
								
								
								<div class="col-md-6">
									<label for="note" class="form-label">Note </label>
									<spring:bind path="note">
										<input type="text" name="note" id="note" class="form-control" placeholder="Inserire note" value="${edit_richiesta_attr.note }"  >
									</spring:bind>
									<form:errors  path="note" cssClass="error_field" />
								</div>
									
								<div class="col-12">	
									<a href="${pageContext.request.contextPath}/permesso/listAllPersonali" class='btn btn-outline-secondary' style='width:100px'>
					            		<i class='fa fa-chevron-left'></i> Indietro
					        		</a>
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									
								</div>
		
							</form:form>
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>	
			
			<!-- end container -->	
			</div>	
		</main>
		<jsp:include page="../footer.jsp" />
		
	</body>
</html>