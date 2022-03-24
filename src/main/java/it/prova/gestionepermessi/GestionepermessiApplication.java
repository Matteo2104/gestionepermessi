package it.prova.gestionepermessi;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.model.Sesso;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.RuoloService;
import it.prova.gestionepermessi.service.UtenteService;



@SpringBootApplication
public class GestionepermessiApplication implements CommandLineRunner {
	
	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;
	@Autowired
	private DipendenteService dipendenteServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(GestionepermessiApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN_USER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ROLE_ADMIN_USER"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Back Office User", "ROLE_BO_USER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Back Office User", "ROLE_BO_USER"));
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_DIPENDENTE_USER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Classic User", "ROLE_DIPENDENTE_USER"));
		}

		// CREO UN UTENTE ADMIN
		if (utenteServiceInstance.findByUsername("m.rossi") == null) {
			Utente admin = new Utente("mario", "rossi", new Date());
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN_USER"));
			
			utenteServiceInstance.inserisciNuovo(admin);
			System.out.println(admin.getPassword());
			
			//l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}
		
		// CREO UN UTENTE BACK OFFICE
		if (utenteServiceInstance.findByUsername("a.verdi") == null) {
			Utente classicUser = new Utente("antonio", "verdi", new Date());
			classicUser.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Back Office User", "ROLE_BO_USER"));
			utenteServiceInstance.inserisciNuovo(classicUser);
			//l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(classicUser.getId());
		}
		
		// CREO UN UTENTE DIPENDENTE
				if (utenteServiceInstance.findByUsername("v.tortosa") == null) {
					Utente classicUser = new Utente("valeria", "tortosa", new Date());
					classicUser.getRuoli()
							.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Dipendente User", "ROLE_DIPENDENTE_USER"));
					utenteServiceInstance.inserisciNuovo(classicUser);
					//l'inserimento avviene come created ma io voglio attivarlo
					utenteServiceInstance.changeUserAbilitation(classicUser.getId());
				}
		
		/*
		if (utenteServiceInstance.findByUsername("user1") == null) {
			Utente classicUser1 = new Utente("user1", "user1", "Antonioo", "Verdii", new Date());
			classicUser1.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_DIPENDENTE_USER"));
			utenteServiceInstance.inserisciNuovo(classicUser1);
			//l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(classicUser1.getId());
		}

		if (utenteServiceInstance.findByUsername("user2") == null) {
			Utente classicUser2 = new Utente("user2", "user2", "Antoniooo", "Verdiii", new Date());
			classicUser2.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER"));
			utenteServiceInstance.inserisciNuovo(classicUser2);
			//l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(classicUser2.getId());
		}
		
		if (dipendenteServiceInstance.findByNomeAndCognome("matteo", "scarcella") == null) {
			Utente classicUser2 = new Utente("user2", "user2", "Antoniooo", "Verdiii", new Date());
			Dipendente dipendente = new Dipendente("matteo", "scarcella", "codicefiscale", "scarcella@outlook.it", null, null, null, Sesso.MASCHIO, utenteServiceInstance.findByUsername("m.scarcella"));
			
			
			
			dipendenteServiceInstance.inserisciNuovo(classicUser2);
			//l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(classicUser2.getId());
		}
		*/
	}

}
