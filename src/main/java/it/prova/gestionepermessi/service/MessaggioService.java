package it.prova.gestionepermessi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.Messaggio;

public interface MessaggioService {

	public Page<Messaggio> findByExample(Messaggio example, Integer pageNo, Integer pageSize, String sortBy);

	public int contaQuantiNonLetti();

	public List<Messaggio> listAll();

	public Optional<Messaggio> caricaSingoloMessaggio(Long id);

	public void read(Messaggio messaggio);

}
