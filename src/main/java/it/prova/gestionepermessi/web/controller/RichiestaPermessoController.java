package it.prova.gestionepermessi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.model.RichiestaPermesso;
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
	
	@GetMapping
	public ModelAndView listAllRichieste() {
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		ModelAndView mv = new ModelAndView();
		/*
		Utente utenteInSessione = utenteService.findByUsername(auth.getName());
		
		for (Ruolo ruolo : utenteInSessione.getRuoli()) {
			if (ruolo.getCodice().equals(Ruolo.ROLE_ADMIN_USER)) 
				mv.addObject("userAdmin", true);
		}
		*/
		
		List<RichiestaPermesso> richieste = richiestaPermessoService.listAllRichieste();
		mv.addObject("permesso_list_attribute", richieste);
		mv.setViewName("permesso/list");
		return mv;
	}
	
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

		model.addAttribute("permesso_list_attribute", richieste);
		return "permesso/list";
	}
	
	// CICLO VISUALIZZAZIONE
	@GetMapping("/show/{idRichiestaPermesso}")
	public String show(@PathVariable(required = true) Long idRichiestaPermesso, Model model) {
		RichiestaPermesso richiesta = richiestaPermessoService.caricaSingolaRichiestaConDipendente(idRichiestaPermesso);
		RichiestaPermessoDTO richiestaDTO = RichiestaPermessoDTO.buildRichiestaPermessoDTOFromModel(richiesta);
		
		//System.out.println(utente);
		
		model.addAttribute("show_richiesta_attr", richiestaDTO);
		//model.addAttribute("show_ruoli_attr", RuoloDTO.createRuoloDTOListFromModelSet(utente.getRuoli()));
		
		return "permesso/show";
	}
	
	
	// CICLO RICERCA PERSONALE
	@GetMapping("/searchPersonale")
	public String searchPersonale(Model model) {
		return "permesso/searchPersonale";
	}

	@PostMapping("/listPersonale")
	public String listPersonale(RichiestaPermessoDTO permessoExample, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long idUtenteInSessione = utenteService.findByUsername(auth.getName()).getId();

		// System.out.println(permessoExample);

		List<RichiestaPermesso> richieste = richiestaPermessoService.findByExamplePersonale(idUtenteInSessione,
				permessoExample.buildRichiestaPermessoModel(true), null, null, null).toList();

		model.addAttribute("permesso_list_attribute", richieste);
		return "permesso/list";
	}
	
}
