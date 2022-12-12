package it.domenico.drprogetto.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Paziente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_paz;
	private String cod_fis_paz;
	private String nome_paz;
	private String cognome_paz;
	private Date dataNascita_paz;
	
	public Paziente() {
		super();
	}
	
	public Paziente(String cod_fis_paz, String nome_paz, String cognome_paz, Date dataNascita_paz) {
		super();
		this.cod_fis_paz = cod_fis_paz;
		this.nome_paz = nome_paz;
		this.cognome_paz = cognome_paz;
		this.dataNascita_paz = dataNascita_paz;
	}

	public Long getId_paz() {
		return id_paz;
	}

	public void setId_paz(Long id_paz) {
		this.id_paz = id_paz;
	}

	public String getCod_fis_paz() {
		return cod_fis_paz;
	}
	public void setCod_fis_paz(String cod_fis_paz) {
		this.cod_fis_paz = cod_fis_paz;
	}
	public String getNome_paz() {
		return nome_paz;
	}
	public void setNome_paz(String nome_paz) {
		this.nome_paz = nome_paz;
	}
	public String getCognome_paz() {
		return cognome_paz;
	}
	public void setCognome_paz(String cognome_paz) {
		this.cognome_paz = cognome_paz;
	}
	public Date getDataNascita_paz() {
		return dataNascita_paz;
	}

	public void setDataNascita_paz(Date dataNascita_paz) {
		this.dataNascita_paz = dataNascita_paz;
	}
	

	@Override
	public String toString() {
		return "Paziente [id_paz=" + id_paz + ", cod_fis_paz=" + cod_fis_paz + ", nome_paz=" + nome_paz
				+ ", cognome_paz=" + cognome_paz + ", dataNascita_paz=" + dataNascita_paz + "]";
	}


	@OneToMany(
			mappedBy = "paziente", 
			fetch = FetchType.EAGER)
	private Set<PazienteDottore> pazienteDottore;	
	
	public Set<PazienteDottore> getPazienteDottore() {
		return pazienteDottore;
	}
	
}
