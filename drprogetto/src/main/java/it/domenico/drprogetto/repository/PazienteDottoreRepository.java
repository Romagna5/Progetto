package it.domenico.drprogetto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.domenico.drprogetto.entity.PazienteDottore;

public interface PazienteDottoreRepository extends JpaRepository<PazienteDottore,Long>{
	
	@Query("SELECT pd FROM PazienteDottore pd WHERE pd.paziente.nome_paz=?1 OR pd.paziente.cognome_paz=?1 OR pd.paziente.cod_fis_paz=?1 ")
	public List<PazienteDottore> FindPazVis(String ric);
	
}
