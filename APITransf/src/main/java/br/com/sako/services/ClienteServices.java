package br.com.sako.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.sako.model.Cliente;

@Service
public class ClienteServices {
	
	public Cliente consultaCliente(Long id) {
		Cliente cliente = new Cliente();
		cliente.setId(new Long(1));
		cliente.setNome("Mauricio Sako");
		cliente.setNumConta("123456789");
		cliente.setSaldo(new BigDecimal(50000));
		return cliente;
	}
	
}
