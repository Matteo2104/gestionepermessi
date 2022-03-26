package it.prova.gestionepermessi.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.dto.RuoloDTO;
import it.prova.gestionepermessi.dto.UtenteDTO;
import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.model.TipoPermesso;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.AttachmentService;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.RichiestaPermessoService;
import it.prova.gestionepermessi.service.RuoloService;
import it.prova.gestionepermessi.service.UtenteService;
import it.prova.gestionepermessi.validation.ValidationNoPassword;
import it.prova.gestionepermessi.validation.ValidationWithPassword;

@Controller
@RequestMapping(value = "/permesso")
public class RichiestaPermessoController {

	@Autowired
	private RichiestaPermessoService richiestaPermessoService;
	@Autowired
	private DipendenteService dipendenteService;
	@Autowired
	private AttachmentService attachmentService;
	
	
	// se non serve toglilo
	@Autowired
	private UtenteService utenteService;
	@Autowired
	private RuoloService ruoloService;
	
	@GetMapping
	public ModelAndView listAllRichieste() {
		
		
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
	
	
	
	
	@GetMapping("/listAllPersonali")
	public ModelAndView listAllPersonali() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Utente utenteInSessione = null;
		 
		if (auth != null ) {
			utenteInSessione = utenteService.findByUsername(auth.getName());
		} 
		
		ModelAndView mv = new ModelAndView();
		/*
		Utente utenteInSessione = utenteService.findByUsername(auth.getName());
		
		for (Ruolo ruolo : utenteInSessione.getRuoli()) {
			if (ruolo.getCodice().equals(Ruolo.ROLE_ADMIN_USER)) 
				mv.addObject("userAdmin", true);
		}
		*/
		
		
		
		List<RichiestaPermesso> richieste = richiestaPermessoService.listAllRichiestePersonali(utenteInSessione.getId());
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
		
		Attachment attachment = attachmentService.caricaAttachmentByIdRichiesta(richiesta.getId());
		richiestaDTO.setAttachment(attachment);
		
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
	
	// CICLO INSERIMENTO 
	@GetMapping("/insert")
	public String create(Model model) {
		//Attachment attachment = new Attachment();
		//RichiestaPermessoDTO richiestaPermessoDTO = new RichiestaPermessoDTO();

		//model.addAttribute("attachment_insert_richiesta_attr", attachment);
		model.addAttribute("insert_richiesta_attr", new RichiestaPermessoDTO());
		return "permesso/insert";
	}
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("insert_richiesta_attr") RichiestaPermessoDTO richiestaPermessoDTO,
			@RequestParam(name="file", required=false) MultipartFile file, Attachment attachment,
			@RequestParam(name="giornoSingolo", required=false) boolean giornoSingolo,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {
		
		if (result.hasErrors()) 
			return "permesso/insert";
		
		if (richiestaPermessoDTO.getTipoPermesso().equals(TipoPermesso.MALATTIA) && file == null) {
			model.addAttribute("errorMessage", "Attenzione! Se il permesso è di tipo ferie è obbligatorio inserire un certificato valido");
			return "permesso/insert";
		}
		
		if (!giornoSingolo && (richiestaPermessoDTO.getDataFine() == null || richiestaPermessoDTO.getDataFine().compareTo(richiestaPermessoDTO.getDataInizio()) < 0)) {
			model.addAttribute("errorMessage", "Attenzione! È presente un errore di validazione nel campo data fine");
			model.addAttribute("insert_richiesta_attr", richiestaPermessoDTO);
			return "permesso/insert";
		}
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long idUtenteInSessione = utenteService.findByUsername(auth.getName()).getId();
		
		RichiestaPermesso richiestaPermesso = richiestaPermessoDTO.buildRichiestaPermessoModel(true);
		
		if (file != null) {
			attachment.setContentType(file.getContentType());
			attachment.setNomeFile(file.getName());
			try {
				attachment.setPayload(file.getBytes());
			} catch (Exception e) {
				throw new RuntimeException("non è stato possibile leggere il contenuto del file");
				// fare altro
			}
			
			richiestaPermesso.setAttachment(attachment);
		}
		

		// System.out.println(utenteDTO);
		/*
		 * if (!result.hasFieldErrors("password") &&
		 * !utenteDTO.getPassword().equals(utenteDTO.getConfermaPassword()))
		 * result.rejectValue("confermaPassword", "password.diverse");
		 */

		/*
		if (result.hasErrors()) {
			return "permesso/insert";
		}
		*/

		richiestaPermessoService.inserisciNuovo(idUtenteInSessione, richiestaPermesso);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/permesso/listAllPersonali";
	}
	
	
	// CICLO MODIFICA
	@GetMapping("/edit/{idRichiesta}")
	public String edit(@PathVariable(required = true) Long idRichiesta, Model model) {
		RichiestaPermesso richiestaPermesso = richiestaPermessoService.caricaSingolaRichiesta(idRichiesta);
		
		//System.out.println(richiestaPermesso);
		
		if (richiestaPermesso.getApprovato()==null || !richiestaPermesso.getApprovato()) {
			model.addAttribute("edit_richiesta_attr",
					RichiestaPermessoDTO.buildRichiestaPermessoDTOFromModel(richiestaPermesso));
			return "permesso/edit";
		}

		model.addAttribute("errorMessage", "Operazione non autorizzata!!");
		return "permesso";
	}
	@PostMapping("/update")
	public String update(@ModelAttribute("edit_richiesta_attr") RichiestaPermessoDTO richiestaPermessoDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "permesso/edit";
		}

		if (richiestaPermessoDTO.getApprovato() == null || !richiestaPermessoDTO.getApprovato()) {
			richiestaPermessoService.aggiorna(richiestaPermessoDTO.buildRichiestaPermessoModel(false));
			redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
			return "redirect:/permesso/listAllPersonali";
		}

		redirectAttrs.addFlashAttribute("errorMessage", "Operazione non autorizzata!!!!");
		return "redirect:/index";
	}
	
	// CICLO ELIMINAZIONE
	@GetMapping("/delete/{idRichiesta}")
	public String delete(@PathVariable(required = true) Long idRichiesta, Model model) {
		RichiestaPermesso richiestaPermesso = richiestaPermessoService.caricaSingolaRichiesta(idRichiesta);
		
		//System.out.println(richiestaPermesso);
		
		if (richiestaPermesso.getApprovato()==null || !richiestaPermesso.getApprovato()) {
			model.addAttribute("delete_richiesta_attr",
					RichiestaPermessoDTO.buildRichiestaPermessoDTOFromModel(richiestaPermesso));
			return "permesso/delete";
		}

		model.addAttribute("errorMessage", "Operazione non autorizzata!!");
		return "permesso";
	}
	@PostMapping("/remove")
	public String remove(@RequestParam(required = true) Long idImpiegato,
			RedirectAttributes redirectAttrs) {
		
		
		RichiestaPermesso richiestaPermesso = richiestaPermessoService.caricaSingolaRichiesta(idImpiegato);
		
		// se la richiesta non è stata ancora approvata...
		if (richiestaPermesso.getApprovato() == null || !richiestaPermesso.getApprovato()) {
			richiestaPermessoService.rimuovi(idImpiegato);
			redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
			return "redirect:/permesso/listAllPersonali";
		}

		// altrimenti blocco la navigazione
		redirectAttrs.addFlashAttribute("errorMessage", "Operazione non autorizzata!!!!");
		return "redirect:/index";
	}
	
	// APPROVAZIONE
	@PostMapping("/approva")
	public String update(@RequestParam(name="idRichiesta") Long idRichiesta,
			RedirectAttributes redirectAttrs, HttpServletRequest request) {

		//System.out.println(idRichiesta);
		
		richiestaPermessoService.approva(idRichiesta);

		redirectAttrs.addFlashAttribute("successMessage", "Richiesta Autorizzata");
		return "redirect:/permesso";
	}
	
	
	
	@GetMapping("/showAttachment/{idAttachment}")
	public ResponseEntity<byte[]> showAttachment(@PathVariable(required = true) Long idAttachment) {

		Attachment file = attachmentService.caricaSingoloElemento(idAttachment);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getNomeFile() + "\"")
				.body(file.getPayload());

	}
	
}
