package it.prova.gestionepermessi.service;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.Messaggio;

public interface MessaggioService {

	public Page<Messaggio> findByExample(Messaggio example, Integer pageNo, Integer pageSize, String sortBy);

}
