package it.prova.gestionepermessi.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.repository.AttachmentRepository;

@Service
public class AttachmentServiceImpl implements AttachmentService {
	
	@Autowired
	private AttachmentRepository repository;
	
	@Override
	@Transactional
	public Attachment caricaAttachmentByIdRichiesta(Long id) {
		return repository.findByIdRichiesta(id).orElse(null);
	}
	
	@Override
	@Transactional
	public Attachment caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}
}
