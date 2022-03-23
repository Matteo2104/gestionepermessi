package it.prova.gestionepermessi.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.dto.RuoloDTO;
import it.prova.gestionepermessi.dto.UtenteDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.RuoloService;
import it.prova.gestionepermessi.service.UtenteService;
import it.prova.gestionepermessi.validation.ValidationNoPassword;

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
		
		List<Dipendente> dipendenti = dipendenteService.listAllDipendenti();
		mv.addObject("dipendente_list_attribute", dipendenti);
		mv.setViewName("dipendente/list");
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
		
		
		model.addAttribute("dipendente_list_attribute", dipendentiDTOlist);
		return "dipendente/list";
	}
	
	// CICLO VISUALIZZAZIONE
	@GetMapping("/show/{idDipendente}")
	public String show(@PathVariable(required = true) Long idDipendente, Model model) {
		Dipendente dipendente = dipendenteService.caricaSingoloDipendenteConRichiestePermesso(idDipendente);
		DipendenteDTO dipendenteDTO = DipendenteDTO.buildDipendenteDTOFromModel(dipendente);
		
		//System.out.println(utente);
		
		model.addAttribute("show_dipendente_attr", dipendenteDTO);
		model.addAttribute("show_richieste_attr", RichiestaPermessoDTO.createRichiestaPermessoDTOListFromModelSet(dipendente.getRichiestePermesso()));
		
		return "dipendente/show";
	}
	
	// CICLO MODIFICA
	@GetMapping("/edit/{idDipendente}")
	public String edit(@PathVariable(required = true) Long idDipendente, Model model) {
		Dipendente dipendenteModel = dipendenteService.caricaSingoloDipendenteConRuoli(idDipendente);
		
		System.out.println(dipendenteModel.getUtente());
		
		model.addAttribute("edit_dipendente_attr", DipendenteDTO.buildDipendenteDTOFromModel(dipendenteModel));
		
		
		
		model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));

		//System.out.println("\n\n SONO QUI \n\n");
		return "dipendente/edit";
	}
	@PostMapping("/update")
	public String update(@Validated(ValidationNoPassword.class) @ModelAttribute("edit_dipendente_attr") DipendenteDTO dipendenteDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
			return "dipendente/edit";
		}
		
		dipendenteService.aggiornaDipendente(dipendenteDTO.buildDipendenteModel(false));
		
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/utente";
	}
	
}
