package it.prova.gestionepermessi.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.model.Dipendente;

public interface AttachmentRepository extends CrudRepository<Attachment, Long>, PagingAndSortingRepository<Attachment, Long>, JpaSpecificationExecutor<Attachment> {

}
