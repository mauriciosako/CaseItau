package br.com.sako.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sako.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	@Query(value = "SELECT * FROM cliente WHERE conta = :conta", nativeQuery = true)
	public Optional<Cliente> findByNumConta(@Param("conta") String conta);

	@Query(value = "UPDATE cliente SET saldo = saldo + :valor WHERE conta = :conta", nativeQuery = true)
	@Modifying
	public void updateSaldoSoma(@Param("conta") String conta, @Param("valor") float valor); 
	
	@Query(value = "UPDATE cliente SET saldo = saldo - :valor WHERE conta = :conta", nativeQuery = true)
	@Modifying
	public void updateSaldoSubtrai(@Param("conta") String conta, @Param("valor") float valor); 
	
	
}
