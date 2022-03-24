package it.prova.gestionepermessi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.dto.MessaggioDTO;
import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.service.MessaggioService;

@Controller
@RequestMapping(value = "/messaggio")
public class MessaggioController {
	@Autowired
	private MessaggioService messaggioService;
	
	// CICLO RICERCA
	@GetMapping("/search")
	public String search(Model model) {
		MessaggioDTO messaggio = new MessaggioDTO();
		RichiestaPermessoDTO permesso = new RichiestaPermessoDTO();
		DipendenteDTO dipendente = new DipendenteDTO();
		permesso.setDipendente(dipendente);
		messaggio.setRichiestaPermesso(permesso);
		
		model.addAttribute("messaggio_search_attr", messaggio);

		//System.out.println(messaggio.getRichiestaPermesso().getDipendente());

		return "messaggio/search";
	}
	@PostMapping("/list")
	public String list(MessaggioDTO messaggioExample, ModelMap model) {

		
		System.out.println("permesso: " + messaggioExample.getRichiestaPermesso());

//		List<Messaggio> messaggi = messaggioService
//				.findByExample(messaggioExample.buildMessaggioModel(true, true), null, null, null).toList();

		//model.addAttribute("messaggio_list_attribute", messaggi);
		return "messaggio/list";
	}
}
