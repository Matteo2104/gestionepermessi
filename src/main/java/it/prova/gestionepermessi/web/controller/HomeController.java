package it.prova.gestionepermessi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.prova.gestionepermessi.service.MessaggioService;
import it.prova.gestionepermessi.service.UtenteService;

@Controller
public class HomeController {
	@Autowired
	private MessaggioService messaggioService;
	@Autowired
	private UtenteService utenteService;
	
	@RequestMapping(value = {"/home",""})
	public String home(Model model) {
		int numeroMessaggiNonLetti = messaggioService.contaQuantiNonLetti();
		
		
		model.addAttribute("nuoviMessaggi", numeroMessaggiNonLetti);
		return "index";
	}
}
