package it.prova.gestionepermessi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.model.Dipendente;

public interface AttachmentRepository extends CrudRepository<Attachment, Long>, PagingAndSortingRepository<Attachment, Long>, JpaSpecificationExecutor<Attachment> {
	@Query("select a from RichiestaPermesso rp join rp.attachment a where rp.id = ?1")
	Optional<Attachment> findByIdRichiesta(Long id);
}
