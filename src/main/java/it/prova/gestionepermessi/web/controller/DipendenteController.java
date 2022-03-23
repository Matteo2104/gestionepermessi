package it.prova.gestionepermessi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.dto.RuoloDTO;
import it.prova.gestionepermessi.dto.UtenteDTO;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.RuoloService;
import it.prova.gestionepermessi.service.UtenteService;

@Controller
@RequestMapping(value = "/dipendente")
public class DipendenteController {
	@Autowired
	private RuoloService ruoloService;
	@Autowired
	private UtenteService utenteService;
	@Autowired
	private DipendenteService dipendenteService;
	
	@GetMapping
	public ModelAndView listAllDipendenti() {
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		ModelAndView mv = new ModelAndView();
		/*
		Utente utenteInSessione = utenteService.findByUsername(auth.getName());
		
		for (Ruolo ruolo : utenteInSessione.getRuoli()) {
			if (ruolo.getCodice().equals(Ruolo.ROLE_ADMIN_USER)) 
				mv.addObject("userAdmin", true);
		}
		*/
		
		List<Utente> utenti = utenteService.listAllUtenti();
		mv.addObject("utente_list_attribute", utenti);
		mv.setViewName("utente/list");
		return mv;
	}
	
	// CICLO RICERCA
	@GetMapping("/search")
	public String search(Model model) {
		return "dipendente/search";
	}
	@PostMapping("/list")
	public String list(DipendenteDTO dipendenteExample, ModelMap model) {
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		List<DipendenteDTO> dipendentiDTOlist = DipendenteDTO.createDipendenteDTOListFromModelList(dipendenteService.findByExample(dipendenteExample.buildDipendenteModel(false), null, null, null).toList());
		
		
		/*
		Utente utenteInSessione = utenteService.findByUsername(auth.getName());

		for (Ruolo ruolo : utenteInSessione.getRuoli()) {
			if (ruolo.getCodice().equals(Ruolo.ROLE_ADMIN_USER)) 
				model.addAttribute("userAdmin", true);
		}
		*/
		
		model.addAttribute("dipendente_list_attribute", dipendentiDTOlist);
		return "dipendente/list";
	}
}
