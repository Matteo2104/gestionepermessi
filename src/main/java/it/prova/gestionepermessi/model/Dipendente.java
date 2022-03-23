package it.prova.gestionepermessi.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dipendente")
public class Dipendente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "codFis")
	private String codFis;
	@Column(name = "email")
	private String email;
	@Column(name = "dataNascita")
	private Date dataNascita;
	@Column(name = "dataAssunzione")
	private Date dataAssunzione;
	@Column(name = "dataDimissioni")
	private Date dataDimissioni;
	
	@Enumerated(EnumType.STRING)
	private Sesso sesso;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id", nullable = false, unique=true)
	private Utente utente;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dipendente")
	private Set<RichiestaPermesso> richiestePermesso = new HashSet<>();
	
	public Dipendente() {}
	public Dipendente(String nome, String cognome, String codFis, String email, Date dataNascita, Date dataAssunzione, Date dataDimissioni, Sesso sesso) {
		this.nome = nome;
		this.cognome = cognome;
		this.codFis = codFis;
		this.email = email;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
		this.dataDimissioni = dataDimissioni;
		this.sesso = sesso;
	}
	public Dipendente(Long id) {
		this.id = id;
	}
	public Dipendente(Long id, String nome, String cognome, String codFis, String email, Date dataNascita, Date dataAssunzione, Date dataDimissioni, Sesso sesso) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codFis = codFis;
		this.email = email;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
		this.dataDimissioni = dataDimissioni;
		this.sesso = sesso;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getCodFis() {
		return codFis;
	}

	public String getEmail() {
		return email;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public Date getDataAssunzione() {
		return dataAssunzione;
	}

	public Date getDataDimissioni() {
		return dataDimissioni;
	}

	public Sesso getSesso() {
		return sesso;
	}

	public Utente getUtente() {
		return utente;
	}

	public Set<RichiestaPermesso> getRichiestePermesso() {
		return richiestePermesso;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setCodFis(String codFis) {
		this.codFis = codFis;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public void setDataAssunzione(Date dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}

	public void setDataDimissioni(Date dataDimissioni) {
		this.dataDimissioni = dataDimissioni;
	}

	public void setSesso(Sesso sesso) {
		this.sesso = sesso;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public void setRichiestePermesso(Set<RichiestaPermesso> richiestePermesso) {
		this.richiestePermesso = richiestePermesso;
	}

	@Override
	public String toString() {
		return "Dipendente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", codFis=" + codFis + ", email="
				+ email + ", dataNascita=" + dataNascita + ", dataAssunzione=" + dataAssunzione + ", dataDimissioni="
				+ dataDimissioni + ", sesso=" + sesso + "]";
	}
	
	
}
