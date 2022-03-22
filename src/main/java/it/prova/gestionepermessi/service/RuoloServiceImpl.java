package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.repository.RuoloRepository;


@Service
public class RuoloServiceImpl implements RuoloService {
	@Autowired
	private RuoloRepository ruoloRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Ruolo> listAll() {
		return (List<Ruolo>)ruoloRepository.findAll();
	}
	
	@Override
	@Transactional
	public void inserisciNuovo(Ruolo ruoloInstance) {
		ruoloRepository.save(ruoloInstance);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) {
		return ruoloRepository.findByDescrizioneAndCodice(descrizione, codice);
	}
}
