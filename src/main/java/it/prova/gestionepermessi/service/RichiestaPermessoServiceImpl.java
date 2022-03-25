package it.prova.gestionepermessi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.repository.AttachmentRepository;
import it.prova.gestionepermessi.repository.DipendenteRepository;
import it.prova.gestionepermessi.repository.MessaggioRepository;
import it.prova.gestionepermessi.repository.RichiestaPermessoRepository;

@Service
public class RichiestaPermessoServiceImpl implements RichiestaPermessoService {
	@Autowired
	private RichiestaPermessoRepository repository;
	@Autowired
	private DipendenteRepository dipendenteRepository;
	@Autowired
	private MessaggioRepository messaggioRepository;
	@Autowired
	private AttachmentRepository attachmentRepository;
	
	@Override
	@Transactional(readOnly = true)
	public RichiestaPermesso caricaSingolaRichiestaConDipendente(Long id) {
		return repository.findByIdConDipendente(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void approva(Long id) {
		RichiestaPermesso richiestaReloaded = repository.findById(id).orElse(null);
		
		richiestaReloaded.setApprovato(true);
		
		Messaggio messaggio = messaggioRepository.findByIdPermesso(id);
		messaggio.setLetto(true);
		
		messaggioRepository.save(messaggio);
		repository.save(richiestaReloaded);
	}
	
	@Override
	@Transactional(readOnly = true)
	public RichiestaPermesso caricaSingolaRichiesta(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void aggiorna(RichiestaPermesso richiestaPermesso) {
		RichiestaPermesso richiestaPermessoReloaded = repository.findByIdConDipendente(richiestaPermesso.getId()).orElse(null); 
		Messaggio messaggio = messaggioRepository.findByIdPermesso(richiestaPermesso.getId());
		
		//System.out.println(richiestaPermessoReloaded.getDipendente());
		
		richiestaPermessoReloaded.setCodiceCertificato(richiestaPermesso.getCodiceCertificato());
		richiestaPermessoReloaded.setDataInizio(richiestaPermesso.getDataInizio());
		richiestaPermessoReloaded.setDataFine(richiestaPermesso.getDataFine());
		richiestaPermessoReloaded.setTipoPermesso(richiestaPermesso.getTipoPermesso());
		richiestaPermessoReloaded.setNote(richiestaPermesso.getNote());
		
		repository.save(richiestaPermessoReloaded);
		
		// faccio le opportune modifiche
		messaggio.setTesto("Con la presente, si richiede un permesso di tipo + " + richiestaPermesso.getTipoPermesso() + " che va dal " + richiestaPermesso.getDataInizio() + " al " + richiestaPermesso.getDataFine());
		messaggio.setRichiestaPermesso(richiestaPermesso);
		messaggioRepository.save(messaggio);
	}
	
	@Override
	@Transactional
	public void rimuovi(Long idRichiestaPermesso) {
		RichiestaPermesso richiestaPermesso = repository.findById(idRichiestaPermesso).orElse(null);
		
		messaggioRepository.delete(messaggioRepository.findByIdPermesso(idRichiestaPermesso));
		
		if (richiestaPermesso.getAttachment() != null) {
			attachmentRepository.delete(richiestaPermesso.getAttachment());
		}
		
		repository.deleteById(idRichiestaPermesso);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<RichiestaPermesso> listAllRichieste() {
		return (List<RichiestaPermesso>) repository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<RichiestaPermesso> listAllRichiestePersonali(Long id) {
		return repository.findByIdPersonali(id);
	}
	
	@Override
	@Transactional
	public void inserisciNuovo(Long id, RichiestaPermesso richiestaPermesso) {
		Dipendente dipendente = dipendenteRepository.findById(id).orElse(null);
		
		Messaggio messaggio = new Messaggio();
		
		// riempio l'istanza di messaggio con le varie informazioni
		messaggio.setOggetto("Richiesta di Permesso da parte di " + dipendente.getNome() + " " + dipendente.getCognome());
		messaggio.setTesto("Con la presente, si richiede un permesso di tipo + " + richiestaPermesso.getTipoPermesso() + " che va dal " + richiestaPermesso.getDataInizio() + " al " + richiestaPermesso.getDataFine());
		messaggio.setLetto(false);
		
		richiestaPermesso.setDipendente(dipendente);
		dipendente.getRichiestePermesso().add(richiestaPermesso);
		attachmentRepository.save(richiestaPermesso.getAttachment());
		repository.save(richiestaPermesso);
		
		messaggio.setRichiestaPermesso(richiestaPermesso);
		messaggioRepository.save(messaggio);
	
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<RichiestaPermesso> findByExample(RichiestaPermesso example, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<RichiestaPermesso> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();
			
		

			if (example.getTipoPermesso() != null)
				predicates.add(cb.equal(cb.upper(root.get("tipoPermesso")), example.getTipoPermesso()));

			if (example.getDataInizio() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataInizio"), example.getDataInizio()));
			
			if (example.getDataFine() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataFine"), example.getDataFine()));

			if (example.getApprovato() != null)
				predicates.add(cb.equal(cb.upper(root.get("approvato")), example.getApprovato()));
			
			if (StringUtils.isNotBlank(example.getCodiceCertificato()))
				predicates.add(cb.like(root.get("codiceCertificato"), "%" + example.getCodiceCertificato() + "%"));
			
			if (StringUtils.isNotBlank(example.getNote()))
				predicates.add(cb.like(root.get("note"), "%" + example.getNote() + "%"));

			
			
			if (example.getDipendente() != null && example.getDipendente().getId() != null) 
				predicates.add(cb.equal(cb.upper(root.get("dipendente")), example.getDipendente().getId()));
			
		
			
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));

		};
		
		Pageable paging = null;
		// se non passo parametri di paginazione non ne tengo conto
		if (pageSize == null || pageSize < 10)
			paging = Pageable.unpaged();
		else
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return repository.findAll(specificationCriteria, paging);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Page<RichiestaPermesso> findByExamplePersonale(Long id, RichiestaPermesso example, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<RichiestaPermesso> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();
			
		

			if (example.getTipoPermesso() != null)
				predicates.add(cb.equal(cb.upper(root.get("tipoPermesso")), example.getTipoPermesso()));

			if (example.getDataInizio() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataInizio"), example.getDataInizio()));
			
			if (example.getDataFine() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataFine"), example.getDataFine()));

			if (example.getApprovato() != null)
				predicates.add(cb.equal(cb.upper(root.get("approvato")), example.getApprovato()));
			
			if (StringUtils.isNotBlank(example.getCodiceCertificato()))
				predicates.add(cb.like(root.get("codiceCertificato"), "%" + example.getCodiceCertificato() + "%"));
			
			if (StringUtils.isNotBlank(example.getNote()))
				predicates.add(cb.like(root.get("note"), "%" + example.getNote() + "%"));

			
			
			// filtro solo le richieste relative all'utente in sessione
			
			predicates.add(cb.equal(cb.upper(root.get("dipendente")), id));
			
			
			
		
			
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));

		};
		
		Pageable paging = null;
		// se non passo parametri di paginazione non ne tengo conto
		if (pageSize == null || pageSize < 10)
			paging = Pageable.unpaged();
		else
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return repository.findAll(specificationCriteria, paging);
	}

}
