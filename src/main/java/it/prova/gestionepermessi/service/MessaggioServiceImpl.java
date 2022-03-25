package it.prova.gestionepermessi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.repository.MessaggioRepository;

@Service
public class MessaggioServiceImpl implements MessaggioService {
	@Autowired
	private MessaggioRepository repository;
	
	@Override
	@Transactional
	public int contaQuantiNonLetti() {
		return repository.contaQuantiNonLetti();
	}
	
	@Override
	@Transactional
	public void read(Messaggio messaggio) {
		Messaggio messaggioReloaded = repository.caricaMessaggioConRichiesta(messaggio.getId());
		
		messaggioReloaded.setLetto(messaggio.getLetto());
		
		System.out.println(messaggio);
		System.out.println(messaggioReloaded);
		
		messaggioReloaded.setLetto(true);
		
		repository.save(messaggioReloaded);
	}
	
	@Override
	@Transactional
	public List<Messaggio> listAll() {
		return repository.listAll();
	}
	
	@Override
	@Transactional
	public Optional<Messaggio> caricaSingoloMessaggio(Long id) {
		return repository.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Messaggio> findByExample(Messaggio example, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<Messaggio> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();
			
		

			if (StringUtils.isNotBlank(example.getTesto()))
				predicates.add(cb.like(root.get("testo"), "%" + example.getTesto() + "%"));
			
			if (StringUtils.isNotBlank(example.getOggetto()))
				predicates.add(cb.like(root.get("oggetto"), "%" + example.getOggetto() + "%"));

			if (example.getLetto() != null)
				predicates.add(cb.equal(cb.upper(root.get("letto")), example.getLetto()));
			

			
			
			if (example.getRichiestaPermesso() != null && example.getRichiestaPermesso().getDipendente() != null && example.getRichiestaPermesso().getDipendente().getId() != null) 
				predicates.add(cb.equal(cb.upper(root.get("dipendente")), example.getRichiestaPermesso().getDipendente().getId()));
			
		
			
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
