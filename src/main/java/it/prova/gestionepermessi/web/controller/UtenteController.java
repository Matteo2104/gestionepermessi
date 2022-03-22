package it.prova.gestionepermessi.web.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.prova.gestionepermessi.dto.RuoloDTO;
import it.prova.gestionepermessi.dto.UtenteDTO;
import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.RuoloService;
import it.prova.gestionepermessi.service.UtenteService;



@Controller
@RequestMapping(value = "/utente")
public class UtenteController {
	
	@Autowired
	private RuoloService ruoloService;
	@Autowired
	private UtenteService utenteService;
	
	@GetMapping("/search")
	public String searchUtente(Model model) {
		model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
		return "utente/search";
	}
	
	@PostMapping("/list")
	public String listUtenti(UtenteDTO utenteExample, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		List<Utente> utenti = utenteService.findByExample(utenteExample.buildUtenteModel(true), null, null, null).toList();
		
		Utente utenteInSessione = utenteService.findByUsername(auth.getName());

		for (Ruolo ruolo : utenteInSessione.getRuoli()) {
			if (ruolo.getCodice().equals(Ruolo.ROLE_ADMIN_USER)) 
				model.addAttribute("userAdmin", true);
		}
		
		model.addAttribute("utente_list_attribute", utenti);
		return "utente/list";
	}
	
}
