package it.prova.gestionepermessi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.dto.RuoloDTO;
import it.prova.gestionepermessi.dto.UtenteDTO;
import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.RichiestaPermessoService;
import it.prova.gestionepermessi.service.RuoloService;
import it.prova.gestionepermessi.service.UtenteService;

@Controller
@RequestMapping(value = "/permesso")
public class RichiestaPermessoController {

	@Autowired
	private RichiestaPermessoService richiestaPermessoService;
	@Autowired
	private DipendenteService dipendenteService;
	
	
	// se non serve toglilo
	@Autowired
	private UtenteService utenteService;
	@Autowired
	private RuoloService ruoloService;
	
	// CICLO RICERCA
	@GetMapping("/search")
	public String search(Model model) {
		RichiestaPermessoDTO permesso = new RichiestaPermessoDTO();
		DipendenteDTO dipendente = new DipendenteDTO();
		permesso.setDipendente(dipendente);
		model.addAttribute("permesso_search_attr", permesso);
		
		//System.out.println(permesso);
		
		return "permesso/search";
	}

	@PostMapping("/list")
	public String list(RichiestaPermessoDTO permessoExample, ModelMap model) {
		// Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		//System.out.println(permessoExample);
		
		List<RichiestaPermesso> richieste = richiestaPermessoService.findByExample(permessoExample.buildRichiestaPermessoModel(true), null, null, null)
				.toList();

		
		
		/*
		 * Utente utenteInSessione = utenteService.findByUsername(auth.getName());
		 * 
		 * for (Ruolo ruolo : utenteInSessione.getRuoli()) { if
		 * (ruolo.getCodice().equals(Ruolo.ROLE_ADMIN_USER))
		 * model.addAttribute("userAdmin", true); }
		 */

		model.addAttribute("permesso_list_attribute", richieste);
		return "permesso/list";
	}
	
}
