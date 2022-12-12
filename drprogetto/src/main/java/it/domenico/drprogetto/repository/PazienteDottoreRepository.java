package it.domenico.drprogetto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.domenico.drprogetto.entity.PazienteDottore;

public interface PazienteDottoreRepository extends JpaRepository<PazienteDottore,Long>{
	
}
