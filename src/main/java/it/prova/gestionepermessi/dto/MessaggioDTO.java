package it.prova.gestionepermessi.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.model.RichiestaPermesso;

public class MessaggioDTO {
	
	private Long id;
	private String testo;
	private String oggetto;
	private Boolean letto;
	
	
	private RichiestaPermessoDTO richiestaPermesso;
	
	
	public MessaggioDTO() {}
	public MessaggioDTO(Long id, String testo, String oggetto, Boolean letto) {
		this.id = id;
		this.testo = testo;
		this.oggetto = oggetto;
		this.letto = letto;
	}
	
	public Long getId() {
		return id;
	}
	public String getTesto() {
		return testo;
	}
	public String getOggetto() {
		return oggetto;
	}
	public Boolean getLetto() {
		return letto;
	}
	public RichiestaPermessoDTO getRichiestaPermesso() {
		return richiestaPermesso;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}
	public void setLetto(Boolean letto) {
		this.letto = letto;
	}
	public void setRichiestaPermesso(RichiestaPermessoDTO richiestaPermesso) {
		this.richiestaPermesso = richiestaPermesso;
	}
	
	
	
	
	public Messaggio buildMessaggioModel(boolean includeRichiestaPermesso, boolean includeDipendente) {
		Messaggio result = new Messaggio(this.id, this.testo, this.oggetto, this.letto);
		
		//System.out.println(this.richiestaPermesso);
		
		if (includeRichiestaPermesso) {
			if (includeDipendente) {
				result.setRichiestaPermesso(this.richiestaPermesso.buildRichiestaPermessoModel(true));
			} else {
				result.setRichiestaPermesso(this.richiestaPermesso.buildRichiestaPermessoModel(false));
			}
		}
		
		return result;
	}
	
	
	public static MessaggioDTO buildMessaggioDTOFromModel(Messaggio messaggioModel) {
		MessaggioDTO result = new MessaggioDTO(messaggioModel.getId(), 
				messaggioModel.getTesto(), messaggioModel.getOggetto(), messaggioModel.getLetto());
		
		//result.setDipendente(DipendenteDTO.buildDipendenteDTOFromModel(richiestaPermessoModel.getDipendente()));
		
		/*
		if (!dipendenteModel.getRichiestePermesso().isEmpty())
			result.richiestePermessoId = dipendenteModel.getRichiestePermesso().stream().map(r -> r.getId()).collect(Collectors.toList())
					.toArray(new Long[] {});
		*/
		return result;
	}
	
	
	public static List<RichiestaPermessoDTO> createRichiestaPermessoDTOListFromModelSet(Set<RichiestaPermesso> modelSetInput) {
		return modelSetInput.stream().map(richiestaPermessoEntity -> {
			return RichiestaPermessoDTO.buildRichiestaPermessoDTOFromModel(richiestaPermessoEntity);
		}).collect(Collectors.toList());
	}
	
	
	@Override
	public String toString() {
		return "MessaggioDTO [id=" + id + ", testo=" + testo + ", oggetto=" + oggetto + ", letto=" + letto + "]";
	}
	
	
}
