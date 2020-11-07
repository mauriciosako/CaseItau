package br.com.sako.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sako.model.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer>{
	
	
}
