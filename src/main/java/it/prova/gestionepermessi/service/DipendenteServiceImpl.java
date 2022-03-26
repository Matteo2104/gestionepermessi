package it.prova.gestionepermessi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.StatoUtente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.repository.DipendenteRepository;
import it.prova.gestionepermessi.repository.RuoloRepository;
import it.prova.gestionepermessi.repository.UtenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService {
	@Autowired
	private DipendenteRepository repository;
	@Autowired
	private UtenteRepository utenteRepository;
	@Autowired
	private RuoloRepository ruoloRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@Value("${defaultPassword}")
	private String defaultPassword;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Dipendente> listAllDipendenti() {
		return (List<Dipendente>) repository.findAll();
	}
	
	@Override
	@Transactional
	public Dipendente findByNomeAndCognome(String nome, String cognome) {
		return repository.findByNomeAndCognome(nome, cognome).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Dipendente caricaSingoloDipendenteConRichiestePermesso(Long id) {
		return repository.findByIdConRichieste(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Dipendente caricaSingoloDipendente(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Dipendente caricaSingoloDipendenteConRuoli(Long id) {
		return repository.findByIdConRuoli(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void inserisciNuovo(Dipendente dipendente, Long idRuolo) {
		String username = dipendente.getNome().substring(0, 1) + "." + dipendente.getCognome();
		Utente utente = new Utente(username, passwordEncoder.encode(defaultPassword), dipendente.getNome(), dipendente.getCognome(), new Date());
		
		
		
		utente.getRuoli().add(ruoloRepository.findById(idRuolo).orElse(null));
		
		dipendente.setEmail(username + "@azienda.it");
		
		dipendente.setUtente(utente);
		utente.setDipendente(dipendente);
		utente.setStato(StatoUtente.CREATO);
		
		utenteRepository.save(utente);
		repository.save(dipendente);
	}
	
	@Override
	@Transactional
	public void aggiornaDipendente(Dipendente dipendente) {
		// ricarico il dipendente valorizzando anche il campo utente con i ruoli
		Dipendente dipendenteReloaded = repository.findByIdConRuoli(dipendente.getId()).orElse(null);
		
		System.out.println(dipendente);
		
		// eseguo una serie di aggiornamenti
		dipendenteReloaded.setNome(dipendente.getNome());
		dipendenteReloaded.setCognome(dipendente.getCognome());
		
		dipendenteReloaded.getUtente().setNome(dipendente.getNome());
		dipendenteReloaded.getUtente().setCognome(dipendente.getCognome());
		
		dipendenteReloaded.getUtente().setUsername(dipendente.getNome().substring(0, 1) + "." + dipendente.getCognome());
		dipendenteReloaded.setEmail(dipendenteReloaded.getUtente().getUsername() + "@azienda.it");
		
		dipendenteReloaded.setDataNascita(dipendente.getDataNascita());
		dipendenteReloaded.setDataAssunzione(dipendente.getDataAssunzione());
		dipendenteReloaded.setDataDimissioni(dipendente.getDataDimissioni());

		
		
		//utenteRepository.save(dipendente.getUtente());
		repository.save(dipendenteReloaded);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Dipendente> findByExample(Dipendente example, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<Dipendente> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();
			
			if (StringUtils.isNotEmpty(example.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%" + example.getNome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCognome()))
				predicates.add(cb.like(cb.upper(root.get("cognome")), "%" + example.getCognome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCodFis()))
				predicates.add(cb.like(cb.upper(root.get("codFis")), "%" + example.getCodFis().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getEmail()))
				predicates.add(cb.like(cb.upper(root.get("email")), "%" + example.getEmail().toUpperCase() + "%"));
			
			if (StringUtils.isNotEmpty(example.getEmail()))
				predicates.add(cb.like(cb.upper(root.get("email")), "%" + example.getEmail().toUpperCase() + "%"));
			
			if (example.getDataNascita() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataNascita"), example.getDataNascita()));
			
			if (example.getDataAssunzione() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataAssunzione"), example.getDataAssunzione()));
			
			if (example.getDataDimissioni() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataDimissioni"), example.getDataDimissioni()));
			
			if (example.getSesso() != null)
				predicates.add(cb.equal(root.get("sesso"), example.getSesso()));

			
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
	public List<Dipendente> cercaByCognomeENomeILike(String term) {
		return repository.findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(term, term);
	}
}
