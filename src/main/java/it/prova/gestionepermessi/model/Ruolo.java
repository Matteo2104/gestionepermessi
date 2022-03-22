package it.prova.gestionepermessi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ruolo")
public class Ruolo {
	public static final String ROLE_ADMIN_USER = "ROLE_ADMIN_USER";
	public static final String ROLE_BO_USER = "ROLE_BO_USER";
	public static final String ROLE_DIPENDENTE_USER = "ROLE_DIPENDENTE_USER";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "codice")
	private String codice;

}
