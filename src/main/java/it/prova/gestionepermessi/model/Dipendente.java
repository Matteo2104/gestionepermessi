package it.prova.gestionepermessi.model;

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
	private String dataNascita;
	@Column(name = "dataAssunzione")
	private String dataAssunzione;
	@Column(name = "dataDimissioni")
	private String dataDimissioni;
	
	@Enumerated(EnumType.STRING)
	private Sesso sesso;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id", nullable = false)
	private Utente utente;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dipendente")
	private Set<RichiestaPermesso> richiestePermesso = new HashSet<>();
}
