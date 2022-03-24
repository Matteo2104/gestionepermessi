<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Pagina dei Risultati</title>
	 </head>
	 
	<body class="d-flex flex-column h-100">
	 
		<!-- Fixed navbar -->
		<jsp:include page="../navbar.jsp"></jsp:include>
	 
	
		<!-- Begin page content -->
		<main class="flex-shrink-0">
		  <div class="container">
		  
		  		<div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
				  ${successMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
				<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
				  Esempio di operazione fallita!
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
				<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
				  Aggiungere d-none nelle class per non far apparire
				   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
		  
		  
		  
		  		<div class='card'>
				    <div class='card-header'>
				        <h5>Lista dei risultati</h5> 
				    </div>
				    <div class='card-body'>
				    	
				    	
				    	<sec:authorize access="hasAnyRole({'ADMIN_USER', 'BO_USER'})">
				    		<a href="${pageContext.request.contextPath }/permesso/search" class='btn btn-outline-secondary' >
				            	<i class='fa fa-chevron-left'></i> Torna alla Ricerca
				           	</a>
				        </sec:authorize>
				            
				        
				        <sec:authorize access="hasRole('DIPENDENTE_USER')">
				        	<a class="btn btn-primary " href="${pageContext.request.contextPath}/permesso/insert">Richiedi Permesso</a>
				            <a href="${pageContext.request.contextPath }/permesso/searchPersonale" class='btn btn-outline-secondary' >
				            	<i class='fa fa-chevron-left'></i> Torna alla Ricerca
				           	</a>
				        </sec:authorize>
				    
				        <div class='table-responsive'>
				            <table class='table table-striped ' >
				                <thead>
				                    <tr>
			                         	<th>Tipologia Permesso</th>
				                        
				                        <th>Data di Inizio</th>
				                        <th>Data di Fine</th>
				                       
				                        <th>Azioni</th>
				                    </tr>
				                </thead>
				                <tbody>
				                	<c:forEach items="${permesso_list_attribute }" var="permessoItem">
										<tr>
											<td>${permessoItem.tipoPermesso }</td>
											
											<td><fmt:formatDate type = "date" value = "${permessoItem.dataInizio }" /></td>
											<td><fmt:formatDate type = "date" value = "${permessoItem.dataFine }" /></td>
											<td>
												<a class="btn  btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/permesso/show/${permessoItem.id}">Visualizza</a>
												
												<sec:authorize access="hasRole('DIPENDENTE_USER')">
												
												<c:if test="${not permessoItem.approvato }">
													<a class="btn  btn-sm btn-outline-warning" href="${pageContext.request.contextPath}/permesso/edit/${permessoItem.id}">Modifica</a>
													<a class="btn  btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/permesso/delete/${permessoItem.id}">Elimina</a>
												</c:if>
												
												</sec:authorize>
												
												<!-- non è possibile modificare o eliminare i permessi 
													<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="${pageContext.request.contextPath}/regista/edit/${registaItem.id }">Edit</a>
													<a class="btn btn-outline-danger btn-sm" href="laservletperrimuovere">Delete</a>
												-->
											</td>
										</tr>
									</c:forEach>
				                </tbody>
				            </table>
				        </div>
				   
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