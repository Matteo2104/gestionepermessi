<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
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
	<title>Ricerca Messaggi</title>
	
    
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp"></jsp:include>
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  <div class="container">
	
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
			  ${errorMessage}
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			
			
			
			<div class='card'>
			    <div class='card-header'>
			        <h5>Ricerca Messaggi</h5> 
			    </div>
			    <div class='card-body'>
	
						<form method="post" action="${pageContext.request.contextPath}/messaggio/list" class="row g-3">
						
							<div class="col-md-6">
								<label for="testo" class="form-label">Testo</label>
								<input type="text" name="testo" id="testo" class="form-control" placeholder="Inserire il testo" >
							</div>
							
							<div class="col-md-6">
								<label for="oggetto" class="form-label">Oggetto</label>
								<input type="text" name="oggetto" id="oggetto" class="form-control" placeholder="Inserire l'oggetto" >
							</div>
							
							<div class="col-md-3">
								<label for="letto" class="form-label">Letto</label>
								    <select class="form-select " id="letto" name="letto" >
								    	<option value="" selected> - Selezionare - </option>
								      	<option value="${true}" >Si</option>
								    	<option value="${false}">No</option>
								      	<option value="" >Letti e non letti</option>
							    	</select>
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
			</div>	
	
		</div>
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>