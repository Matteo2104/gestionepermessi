package it.prova.gestionepermessi.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.model.Sesso;
import it.prova.gestionepermessi.model.Utente;

public class DipendenteDTO {
	
	private Long id;
	
	
	private String nome;
	
	private String cognome;
	
	private String codFis;

	private String email;

	private Date dataNascita;

	private Date dataAssunzione;

	private Date dataDimissioni;
	
	private Sesso sesso;
	
	private UtenteDTO utente;
	
	private Long[] richiestePermessoId;
	
	public DipendenteDTO() {}
	public DipendenteDTO(Long id, String nome, String cognome, String codFis, String email, Date dataNascita, Date dataAssunzione, Date dataDimissioni, Sesso sesso) {
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
	public UtenteDTO getUtente() {
		return utente;
	}
	public Long[] getRichiestePermessoId() {
		return richiestePermessoId;
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
	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}
	public void setRichiestePermessoId(Long[] richiestePermessoId) {
		this.richiestePermessoId = richiestePermessoId;
	}
	public Dipendente buildDipendenteModel(boolean includeIdPermessi) {
		Dipendente result = new Dipendente(this.id, this.nome, this.cognome,this.codFis, this.email, this.dataNascita, this.dataAssunzione,
				this.dataDimissioni, this.sesso);
		if (includeIdPermessi && richiestePermessoId != null)
			result.setRichiestePermesso(Arrays.asList(richiestePermessoId).stream().map(id -> new RichiestaPermesso(id))
					.collect(Collectors.toSet()));

		return result;
	}
	
	
	public static DipendenteDTO buildDipendenteDTOFromModel(Dipendente dipendenteModel) {
		DipendenteDTO result = new DipendenteDTO(dipendenteModel.getId(), dipendenteModel.getNome(),
				dipendenteModel.getCognome(), dipendenteModel.getCodFis(), dipendenteModel.getEmail(), 
				dipendenteModel.getDataNascita(), dipendenteModel.getDataAssunzione(), dipendenteModel.getDataDimissioni(), 
				dipendenteModel.getSesso());

		if (!dipendenteModel.getRichiestePermesso().isEmpty())
			result.richiestePermessoId = dipendenteModel.getRichiestePermesso().stream().map(r -> r.getId()).collect(Collectors.toList())
					.toArray(new Long[] {});

		return result;
	}
	
	public static List<DipendenteDTO> createDipendenteDTOListFromModelList(List<Dipendente> modelListInput) {
		return modelListInput.stream().map(dipendenteEntity -> {
			return DipendenteDTO.buildDipendenteDTOFromModel(dipendenteEntity);
		}).collect(Collectors.toList());
	}
	
	
	@Override
	public String toString() {
		return "DipendenteDTO [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", codFis=" + codFis + ", email="
				+ email + ", dataNascita=" + dataNascita + ", dataAssunzione=" + dataAssunzione + ", dataDimissioni="
				+ dataDimissioni + ", sesso=" + sesso + "]";
	}
	
	
	
}
