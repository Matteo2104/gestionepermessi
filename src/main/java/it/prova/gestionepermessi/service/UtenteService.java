package it.prova.gestionepermessi.service;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.Utente;

public interface UtenteService {

	public Utente findByUsername(String username);

	public void inserisciNuovo(Utente utenteInstance);

	public Utente caricaSingoloUtente(Long id);

	public void changeUserAbilitation(Long utenteInstanceId);

	public Page<Utente> findByExample(Utente example, Integer pageNo, Integer pageSize, String sortBy);

}
