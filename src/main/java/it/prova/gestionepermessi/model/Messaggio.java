package it.prova.gestionepermessi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "messaggio")
public class Messaggio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "testo")
	private String testo;
	@Column(name = "oggetto")
	private String oggetto;
	@Column(name = "letto")
	private Boolean letto;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "richiestaPermesso_id", nullable = false)
	private RichiestaPermesso richiestaPermesso;
	
	public Messaggio() {}
	public Messaggio(Long id, String testo, String oggetto, Boolean letto) {
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

	public RichiestaPermesso getRichiestaPermesso() {
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

	public void setRichiestaPermesso(RichiestaPermesso richiestaPermesso) {
		this.richiestaPermesso = richiestaPermesso;
	}
	
	
	/*
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dipendente_id", nullable = false)
	private Utente utenteInserimento;
	*/
}
