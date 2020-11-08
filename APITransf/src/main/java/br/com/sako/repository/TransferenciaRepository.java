package br.com.sako.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sako.model.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer>{
	
	@Query(value = "SELECT * FROM transferencia WHERE conta_origem = :conta OR conta_destino = :conta ORDER BY data_transf desc", nativeQuery = true)
	public List<Transferencia> findByNumConta(@Param("conta") String conta);

	
	
	
}
