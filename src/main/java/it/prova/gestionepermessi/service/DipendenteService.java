package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.Dipendente;

public interface DipendenteService {

	public List<Dipendente> listAllDipendenti();

	public Page<Dipendente> findByExample(Dipendente example, Integer pageNo, Integer pageSize, String sortBy);

	public Dipendente caricaSingoloDipendenteConRichiestePermesso(Long id);


}
