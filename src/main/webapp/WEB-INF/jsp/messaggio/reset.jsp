<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!doctype html>
<html lang="it">
	<head>
	  <meta charset="utf-8">
		<title>Reset</title>
	
		<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	
	
		 <!-- Custom styles for login -->
	    <link href="../assets/css/signin.css" rel="stylesheet">
	</head>
	
	<body class="text-center">
		<main class="form-signin">
		
					<%-- se l'attributo in request ha errori --%>
					<spring:hasBindErrors  name="validate_utente_attr">
						<%-- alert errori --%>
						<div class="alert alert-danger " role="alert">
							Attenzione!! Sono presenti errori di validazione
						</div>
					</spring:hasBindErrors>
					
			
		   	
		   	
			   	<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
				  ${errorMessage}
				</div>
				
				<div class="alert alert-info alert-dismissible fade show ${infoMessage==null?'d-none': ''}" role="alert">
				  ${infoMessage}
				</div>
				
				
			  	<img class="mb-4" src="./assets/brand/bootstrap-logo.svg" alt="" width="72" height="57">
				<h1 class="h3 mb-3 fw-normal">Reset</h1>
		    	
		    	
		    <form:form modelAttribute="validate_utente_attr" method="POST" action="apply" novalidate="novalidate" class="form-signin">
		    	
		    	
			  	<div class="form-floating">
			  		<label for="oldPassword">Vecchia Password</label>
			  		<spring:bind path="oldPassword">
			      		<input type="password" name="oldPassword" class="form-control ${status.error ? 'is-invalid' : ''}" id="oldPassword" placeholder="oldPassword" required>
			      	</spring:bind>
			      	<form:errors  path="oldPassword" cssClass="error_field" />
			    </div>
			    
			    <div class="form-floating">
			  		<label for="newPassword">Nuova Password</label>
			  		<spring:bind path="newPassword">
			      		<input type="password" name="newPassword" class="form-control ${status.error ? 'is-invalid' : ''}" id="newPassword" placeholder="newPassword" required>
			      	</spring:bind>
			      	<form:errors  path="newPassword" cssClass="error_field" />
			    </div>
			    
			    <div class="form-floating">
			  		<label for="confirmNewPassword">Conferma Nuova Password</label>
			  		<spring:bind path="confirmNewPassword">
			      		<input type="password" name="confirmNewPassword" class="form-control ${status.error ? 'is-invalid' : ''}" id="confirmNewPassword" placeholder="confirmNewPassword" required>
			      	</spring:bind>
			      	<form:errors  path="confirmNewPassword" cssClass="error_field" />
			    </div>
			
			    <div class="checkbox mb-3">
			      <label>
			        <input type="checkbox" value="remember-me"> Remember me
			      </label>
			    </div>
			    <button class="w-100 btn btn-lg btn-primary" type="submit">Reset</button>
			    <p class="mt-5 mb-3 text-muted">&copy; 2017-2021</p>
			  
			  
			  
			</form:form>
		</main>
	</body>
</html>