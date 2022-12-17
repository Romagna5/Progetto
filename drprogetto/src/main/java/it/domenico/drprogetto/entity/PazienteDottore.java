package it.domenico.drprogetto.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;

import it.domenico.drprogetto.repository.DottoreRepository;

@Entity
public class PazienteDottore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date data;
	private Time ora;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_paz")
	private Paziente paziente;

	public Paziente getPaziente() {
		return paziente;
	}
	public void setPaziente(Paziente paziente) {
		this.paziente = paziente;
	}
	    
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_dot")
	private Dottore dottore;
	 
	public Dottore getDottore() {
	return dottore;
	}
	 
	public void setDottore(Dottore dottore) {
		this.dottore = dottore;
	}

	    
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
			this.data = data;
	}
	
	public Time getOra() {
		return ora;
	}
	public void setOra(Time ora) {
		this.ora = ora;
	}
	
	
	@Override
	public String toString() {
		return "PazienteDottore [id=" + id + ", data=" + data + ", ora=" + ora + ", paziente=" + paziente + ", dottore="
				+ dottore + "]";
	}
	
	
	
	

}
