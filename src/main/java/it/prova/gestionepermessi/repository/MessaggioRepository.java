package it.prova.gestionepermessi.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionepermessi.model.Messaggio;

public interface MessaggioRepository extends CrudRepository<Messaggio, Long>, PagingAndSortingRepository<Messaggio, Long>, JpaSpecificationExecutor<Messaggio> {
	@Query("from Messaggio m join m.richiestaPermesso rp where rp.id = ?1")
	Messaggio findByIdPermesso(Long id);
}
