package it.prova.gestionepermessi.service;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.RichiestaPermesso;

public interface RichiestaPermessoService {

	public Page<RichiestaPermesso> findByExample(RichiestaPermesso example, Integer pageNo, Integer pageSize, String sortBy);

	public RichiestaPermesso caricaSingolaRichiestaConDipendente(Long id);

}
