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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionepermessi.model.StatoUtente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.repository.UtenteRepository;


@Service
public class UtenteServiceImpl implements UtenteService {
	@Autowired
	private UtenteRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional(readOnly = true)
	public List<Utente> listAllUtenti() {
		return (List<Utente>) repository.findAll();
	}
	
	@Override
	@Transactional
	public Utente findByUsername(String username) {
		return repository.findByUsername(username).orElse(null);
	}
	
	@Override
	@Transactional
	public void inserisciNuovo(Utente utenteInstance) {
		utenteInstance.setStato(StatoUtente.CREATO);
		utenteInstance.setPassword(passwordEncoder.encode(utenteInstance.getPassword())); 
		utenteInstance.setDateCreated(new Date());
		utenteInstance.setUsername(utenteInstance.getNome().substring(0, 1) + "." + utenteInstance.getCognome());
		repository.save(utenteInstance);
	}
	
	@Override
	@Transactional
	public void aggiorna(Utente utenteInstance) {
		Utente utenteReloaded = repository.findById(utenteInstance.getId()).orElse(null);
		if(utenteReloaded == null)
			throw new RuntimeException("Elemento non trovato");
		
		utenteReloaded.setNome(utenteInstance.getNome());
		utenteReloaded.setCognome(utenteInstance.getCognome());
		utenteReloaded.setUsername(utenteInstance.getNome().substring(0, 1) + "." + utenteInstance.getCognome());
		utenteReloaded.setRuoli(utenteInstance.getRuoli());
		repository.save(utenteReloaded);
	}
	
	@Override
	@Transactional
	public void changeUserAbilitation(Long utenteInstanceId) {
		Utente utenteInstance = caricaSingoloUtente(utenteInstanceId);
		if(utenteInstance == null)
			throw new RuntimeException("Elemento non trovato.");
		
		if(utenteInstance.getStato() == null || utenteInstance.getStato().equals(StatoUtente.CREATO))
			utenteInstance.setStato(StatoUtente.ATTIVO);
		else if(utenteInstance.getStato().equals(StatoUtente.ATTIVO))
			utenteInstance.setStato(StatoUtente.DISABILITATO);
		else if(utenteInstance.getStato().equals(StatoUtente.DISABILITATO))
			utenteInstance.setStato(StatoUtente.ATTIVO);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Utente caricaSingoloUtente(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Utente caricaSingoloUtenteConRuoli(Long id) {
		return repository.findByIdConRuoli(id).orElse(null);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Page<Utente> findByExample(Utente example, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<Utente> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();
			
		

			if (StringUtils.isNotEmpty(example.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%" + example.getNome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCognome()))
				predicates.add(cb.like(cb.upper(root.get("cognome")), "%" + example.getCognome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getUsername()))
				predicates.add(cb.like(cb.upper(root.get("username")), "%" + example.getUsername().toUpperCase() + "%"));

			if (example.getDateCreated() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dateCreated"), example.getDateCreated()));
			
			if (example.getStato() != null)
				predicates.add(cb.equal(root.get("stato"), example.getStato()));

			
			if (example.getRuoli() != null && !example.getRuoli().isEmpty()) 
				predicates.add(root.join("ruoli").in(example.getRuoli()));
			
		
			
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
