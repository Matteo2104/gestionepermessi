<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header>
  <!-- Fixed navbar -->
 <nav class="navbar navbar-expand-lg navbar-dark bg-primary" aria-label="Eighth navbar example">
    <div class="container">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07" aria-controls="navbarsExample07" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExample07">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/home">Home</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="dropdown07" data-bs-toggle="dropdown" aria-expanded="false">Azioni</a>
            <ul class="dropdown-menu" aria-labelledby="dropdown07">
              <sec:authorize access="hasRole('ADMIN_USER')">
              	 <li><a class="dropdown-item" href="${pageContext.request.contextPath}/dipendente/search">Ricerca Dipendenti</a></li>
              </sec:authorize>
              <sec:authorize access="hasRole('BO_USER')">
              	<li><a class="dropdown-item" href="${pageContext.request.contextPath}/permesso/search">Ricerca Permessi</a></li>
              	<li><a class="dropdown-item" href="${pageContext.request.contextPath}/messaggio/search">Gestione Messaggi</a></li>
              </sec:authorize>
            </ul> 
          </li>
          
       		<sec:authorize access="hasRole('ADMIN_USER')">
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Gestione Utenze</a>
		        <div class="dropdown-menu" aria-labelledby="dropdown01">
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/utente/search">Ricerca Utenti</a>
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/utente/insert">Inserisci Utente</a>
		        </div>
		      </li>
		   </sec:authorize>
        </ul>
      </div>
      
      
      <sec:authorize access="isAuthenticated()">
      
      	<div class="nav-item dropdown">
            <a class="nav-link dropdown-toggle text-light" href="#" id="dropdown07" data-bs-toggle="dropdown" aria-expanded="false">Dropdown</a>
            <div class="dropdown-menu" aria-labelledby="dropdown07">
              
              <a class="dropdown-item" href="${pageContext.request.contextPath}/utente/reset">Reset Password</a>
              <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a>
             
            </div> 
            
         </div>
         <p class="navbar-text">Utente: ${userInfo.username }(${userInfo.nome } ${userInfo.cognome })</p>
         

      </sec:authorize>
    </div>
  </nav>
  
  
</header>
