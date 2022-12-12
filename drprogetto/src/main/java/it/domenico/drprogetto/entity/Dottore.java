package it.domenico.drprogetto.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Dottore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_dot;
	private String cod_fis_dot;
	private String nome_dot;
	private String cognome_dot;
	
	public Dottore() {
		super();
	}
	public Dottore(String cod_fis_dot, String nome_dot, String cognome_dot) {
		super();
		this.cod_fis_dot = cod_fis_dot;
		this.nome_dot = nome_dot;
		this.cognome_dot = cognome_dot;
	}
	public Long getId_dot() {
		return id_dot;
	}
	public void setId_dot(Long id_dot) {
		this.id_dot = id_dot;
	}
	public String getCod_fis_dot() {
		return cod_fis_dot;
	}
	public void setCod_fis_dot(String cod_fis_dot) {
		this.cod_fis_dot = cod_fis_dot;
	}
	public String getNome_dot() {
		return nome_dot;
	}
	public void setNome_dot(String nome_dot) {
		this.nome_dot = nome_dot;
	}
	public String getCognome_dot() {
		return cognome_dot;
	}
	public void setCognome_dot(String cognome_dot) {
		this.cognome_dot = cognome_dot;
	}
		
	
	
	@Override
	public String toString() {
		return "Dottore [id_dot=" + id_dot + ", cod_fis_dot=" + cod_fis_dot + ", nome_dot=" + nome_dot
				+ ", cognome_dot=" + cognome_dot + "]";
	}



	@OneToMany(
			mappedBy = "dottore", 
			fetch = FetchType.EAGER)
	private Set<PazienteDottore> pazienteDottore;	
	
	public Set<PazienteDottore> getPazienteDottore() {
		return pazienteDottore;
	}
	
	
}
