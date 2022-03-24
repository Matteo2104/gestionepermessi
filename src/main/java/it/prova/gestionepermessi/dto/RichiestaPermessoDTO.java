package it.prova.gestionepermessi.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


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
	
	private DipendenteDTO dipendente;
	
	
	
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
	
	
	
	public Long getId() {
		return id;
	}
	public TipoPermesso getTipoPermesso() {
		return tipoPermesso;
	}
	public Date getDataInizio() {
		return dataInizio;
	}
	public Date getDataFine() {
		return dataFine;
	}
	public Boolean getApprovato() {
		return approvato;
	}
	public String getCodiceCertificato() {
		return codiceCertificato;
	}
	public String getNote() {
		return note;
	}
	public Attachment getAttachment() {
		return attachment;
	}
	public DipendenteDTO getDipendente() {
		return dipendente;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setTipoPermesso(TipoPermesso tipoPermesso) {
		this.tipoPermesso = tipoPermesso;
	}
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	public void setApprovato(Boolean approvato) {
		this.approvato = approvato;
	}
	public void setCodiceCertificato(String codiceCertificato) {
		this.codiceCertificato = codiceCertificato;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	public void setDipendente(DipendenteDTO dipendente) {
		this.dipendente = dipendente;
	}
	public RichiestaPermesso buildRichiestaPermessoModel(boolean includeIdRoles) {
		RichiestaPermesso result = new RichiestaPermesso(this.id, this.tipoPermesso, this.dataInizio, this.dataFine, this.approvato, this.codiceCertificato, this.note);
		
		if (this.dipendente != null) {
			result.setDipendente(this.dipendente.buildDipendenteModel(false));
		}
		/*
		if (includeIdRoles && ruoliIds != null)
			result.setRuoli(Arrays.asList(ruoliIds).stream().map(id -> new Ruolo(id)).collect(Collectors.toSet()));
		*/
	
		return result;
	}
	
	
	public static RichiestaPermessoDTO buildRichiestaPermessoDTOFromModel(RichiestaPermesso richiestaPermessoModel) {
		RichiestaPermessoDTO result = new RichiestaPermessoDTO(richiestaPermessoModel.getId(), richiestaPermessoModel.getTipoPermesso(),
				richiestaPermessoModel.getDataInizio(), richiestaPermessoModel.getDataFine(), richiestaPermessoModel.getApprovato(), 
				richiestaPermessoModel.getCodiceCertificato(), richiestaPermessoModel.getNote());
		
		result.setDipendente(DipendenteDTO.buildDipendenteDTOFromModel(richiestaPermessoModel.getDipendente()));
		
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
				+ ", note=" + note + ", dipendente=" + dipendente + "]";
	}
	
	
	
}
