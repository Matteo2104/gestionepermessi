package it.prova.gestionepermessi.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.model.TipoPermesso;

public class RichiestaPermessoDTO {
	
	private Long id;
	
	private TipoPermesso tipoPermesso;
	
	private Date dataInizio;
	
	private Date dataFine;
	
	private Boolean approvato;
	
	private String codiceCertificato;
	
	private String note;
	
	private Attachment attachment;
	
	private Dipendente dipendente;
	
	
	
	public RichiestaPermessoDTO() {}
	public RichiestaPermessoDTO(Long id, TipoPermesso tipoPermesso, Date dataInizio, Date dataFine, Boolean approvato, String codiceCertificato, String note) {
		this.id = id;
		this.tipoPermesso = tipoPermesso;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.approvato = approvato;
		this.codiceCertificato = codiceCertificato;
		this.note = note;
	}
	
	
	public static RichiestaPermessoDTO buildRichiestaPermessoDTOFromModel(RichiestaPermesso richiestaPermessoModel) {
		RichiestaPermessoDTO result = new RichiestaPermessoDTO(richiestaPermessoModel.getId(), richiestaPermessoModel.getTipoPermesso(),
				richiestaPermessoModel.getDataInizio(), richiestaPermessoModel.getDataFine(), richiestaPermessoModel.getApprovato(), 
				richiestaPermessoModel.getCodiceCertificato(), richiestaPermessoModel.getNote());
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
		return "RichiestaPermessoDTO [id=" + id + ", tipoPermesso=" + tipoPermesso + ", dataInizio=" + dataInizio
				+ ", dataFine=" + dataFine + ", approvato=" + approvato + ", codiceCertificato=" + codiceCertificato
				+ ", note=" + note + "]";
	}
	
	
	
}
