package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Utente;

public interface DipendenteService {

	public List<Dipendente> listAllDipendenti();

	public Page<Dipendente> findByExample(Dipendente example, Integer pageNo, Integer pageSize, String sortBy);

	public Dipendente caricaSingoloDipendenteConRichiestePermesso(Long id);



	public Dipendente findByNomeAndCognome(String nome, String cognome);

	public void inserisciNuovo(Dipendente dipendenteInstance, Long idRuolo);

	public Dipendente caricaSingoloDipendente(Long id);

	public Dipendente caricaSingoloDipendenteConRuoli(Long id);

	public void aggiornaDipendente(Dipendente dipendente);

	public List<Dipendente> cercaByCognomeENomeILike(String term);



}
