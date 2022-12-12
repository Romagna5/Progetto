package it.domenico.drprogetto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.domenico.drprogetto.entity.Paziente;

public interface PazienteRepository  extends JpaRepository<Paziente,Long>{

}
