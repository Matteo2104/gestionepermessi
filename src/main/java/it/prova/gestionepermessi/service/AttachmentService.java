package it.prova.gestionepermessi.service;

import it.prova.gestionepermessi.model.Attachment;

public interface AttachmentService {

	public Attachment caricaAttachmentByIdRichiesta(Long id);

	public Attachment caricaSingoloElemento(Long id);

}
