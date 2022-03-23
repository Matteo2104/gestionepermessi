package it.prova.gestionepermessi.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Utente;

public interface DipendenteRepository extends CrudRepository<Dipendente, Long>, PagingAndSortingRepository<Dipendente, Long>, JpaSpecificationExecutor<Dipendente> {

}
