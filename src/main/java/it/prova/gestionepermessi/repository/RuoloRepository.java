package it.prova.gestionepermessi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.core.CrudMethods;

import it.prova.gestionepermessi.model.Ruolo;



public interface RuoloRepository extends CrudRepository<Ruolo, Long> {
	Ruolo findByDescrizioneAndCodice(String descrizione, String codice);
}
