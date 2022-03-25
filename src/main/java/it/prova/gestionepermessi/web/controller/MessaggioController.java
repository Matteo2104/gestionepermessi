package it.prova.gestionepermessi.web.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.dto.MessaggioDTO;
import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.service.MessaggioService;
import it.prova.gestionepermessi.service.RichiestaPermessoService;

@Controller
@RequestMapping(value = "/messaggio")
public class MessaggioController {
	@Autowired
	private MessaggioService messaggioService;
	
	@GetMapping
	public ModelAndView listAll() {
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		ModelAndView mv = new ModelAndView();
		
		
		List<Messaggio> messaggi = messaggioService.listAll();
		mv.addObject("messaggio_list_attribute", messaggi);
		mv.setViewName("messaggio/list");
		return mv;
	}
	
	// CICLO RICERCA
	@GetMapping("/search")
	public String search(Model model) {
		MessaggioDTO messaggio = new MessaggioDTO();
		RichiestaPermessoDTO permesso = new RichiestaPermessoDTO();
		DipendenteDTO dipendente = new DipendenteDTO();
		permesso.setDipendente(dipendente);
		messaggio.setRichiestaPermesso(permesso);
		
		model.addAttribute("messaggio_search_attr", messaggio);
		
		model.addAttribute("permesso_search_attr", permesso);

		//System.out.println(messaggio.getRichiestaPermesso().getDipendente());

		return "messaggio/search";
	}
	@PostMapping("/list")
	public String list(@ModelAttribute("messaggio_search_attr") MessaggioDTO messaggioExample, 
			@ModelAttribute RichiestaPermessoDTO richiestaExample, ModelMap model) {

		
		//System.out.println("permesso: " + richiestaExample);
		
		messaggioExample.setRichiestaPermesso(richiestaExample);

		List<Messaggio> messaggi = messaggioService
				.findByExample(messaggioExample.buildMessaggioModel(true, true), null, null, null).toList();

		model.addAttribute("messaggio_list_attribute", messaggi);
		return "messaggio/list";
	}
	
	
	// CICLO VISUALIZZAZIONE
	@GetMapping("/show/{idMessaggio}")
	public String show(@PathVariable(required = true) Long idMessaggio, Model model) {
		Messaggio messaggio = messaggioService.caricaSingoloMessaggio(idMessaggio).orElse(null);
		MessaggioDTO messaggioDTO = MessaggioDTO.buildMessaggioDTOFromModel(messaggio);

		// System.out.println(utente);

		model.addAttribute("show_messaggio_attr", messaggioDTO);
		// model.addAttribute("show_ruoli_attr",
		// RuoloDTO.createRuoloDTOListFromModelSet(utente.getRuoli()));

		return "messaggio/show";
	}
	@PostMapping("/close")
	public String close(@ModelAttribute("show_messaggio_attr") MessaggioDTO messaggioDTO, RedirectAttributes redirect) {

		
		//System.out.println(messaggioDTO);
		RichiestaPermessoDTO richiestaTemp = new RichiestaPermessoDTO();
		messaggioDTO.setRichiestaPermesso(richiestaTemp);
		messaggioService.read(messaggioDTO.buildMessaggioModel(false, false));

//		List<Messaggio> messaggi = messaggioService
//				.findByExample(messaggioExample.buildMessaggioModel(true, true), null, null, null).toList();

		
		return "redirect:/messaggio";
	}
}
