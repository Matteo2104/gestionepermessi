package it.prova.gestionepermessi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "richiestapermesso")
public class RichiestaPermesso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Enumerated(EnumType.STRING)
	private TipoPermesso tipoPermesso;
	@Column(name = "dataInizio")
	private Date dataInizio;
	@Column(name = "dataFine")
	private Date dataFine;
	@Column(name = "approvato")
	private Boolean approvato;
	@Column(name = "codiceCertificato")
	private String codiceCertificato;
	@Column(name = "note")
	private String note;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_id", nullable = false)
	private Attachment attachment;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dipendente_id", nullable = false)
	private Dipendente dipendente;
	
	public RichiestaPermesso() {}
	public RichiestaPermesso(Long id) {
		this.id = id;
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
	public Dipendente getDipendente() {
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
	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}
	
	
}
