package it.prova.gestionepermessi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionepermessi.model.Messaggio;

public interface MessaggioRepository extends CrudRepository<Messaggio, Long>, PagingAndSortingRepository<Messaggio, Long>, JpaSpecificationExecutor<Messaggio> {
	@Query("from Messaggio m join m.richiestaPermesso rp where rp.id = ?1")
	Messaggio findByIdPermesso(Long id);
	
	@Query("select count(m) from Messaggio m where m.letto = 0")
	int contaQuantiNonLetti();
	
	@Query("from Messaggio")
	List<Messaggio> listAll();
}
