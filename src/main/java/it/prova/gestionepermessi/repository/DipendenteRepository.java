package it.prova.gestionepermessi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionepermessi.model.Dipendente;

public interface DipendenteRepository extends CrudRepository<Dipendente, Long>, PagingAndSortingRepository<Dipendente, Long>, JpaSpecificationExecutor<Dipendente> {
	@Query("from Dipendente d left join fetch d.richiestePermesso where d.id = ?1")
	Optional<Dipendente> findByIdConRichieste(Long id);
}
