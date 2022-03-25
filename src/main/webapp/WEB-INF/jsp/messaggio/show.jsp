<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
			        Visualizza dettaglio Messaggio
			    </div>
			    
			    <form:form modelAttribute="show_messaggio_attr" method="post" action="${pageContext.request.contextPath}/messaggio/close">
			    <form:hidden path="id"/>
			    
			    <div class='card-body'>
			    
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Id:</dt>
					  <dd class="col-sm-9">${show_messaggio_attr.id}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Oggetto:</dt>
					  <dd class="col-sm-9">${show_messaggio_attr.oggetto}</dd>
					  <form:hidden path="oggetto"/>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Testo:</dt>
					  <dd class="col-sm-9">${show_messaggio_attr.testo}</dd>
					   <form:hidden path="testo"/>
			    	</dl>
			    	
			    	
			    	
			    	
			    	
			    	
			    	
			    
			    	
			    <!-- end card body -->
			    </div>
			    
			    <div class='card-footer'>
			        <div class="col-12">
			        	<c:if test="${not show_messaggio_attr.letto }" >
							<button type="submit" name="idMessaggio" value="${show_messaggio_attr.id }" id="idMessaggio" class="btn btn-primary">Esci e segna come letto</button>
						</c:if>
						<a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/messaggio">Indietro</a> 
					</div>
			    </div>
			    </form:form>
			<!-- end card -->
			</div>	
	
		<!-- end container -->  
		</div>
		
		
		
		
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>