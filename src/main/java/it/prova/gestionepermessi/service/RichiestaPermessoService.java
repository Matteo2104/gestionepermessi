package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.RichiestaPermesso;

public interface RichiestaPermessoService {

	public Page<RichiestaPermesso> findByExample(RichiestaPermesso example, Integer pageNo, Integer pageSize, String sortBy);

	public RichiestaPermesso caricaSingolaRichiestaConDipendente(Long id);

	public List<RichiestaPermesso> listAllRichieste();

	public Page<RichiestaPermesso> findByExamplePersonale(Long id, RichiestaPermesso example, Integer pageNo, Integer pageSize,
			String sortBy);


	public void inserisciNuovo(Long id, RichiestaPermesso richiestaPermesso);

}
