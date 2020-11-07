package br.com.sako.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.sako.exception.ClienteNaoEncontradoException;
import br.com.sako.model.Cliente;
import br.com.sako.repository.ClienteRepository;

@Service
public class ClienteServices {
	
	@Autowired
	ClienteRepository repository;
	
	public Cliente cadastrarCliente(Cliente cliente) {
		return repository.save(cliente);
	}
	
	public List<Cliente> listarClientes() {
		return repository.findAll();
	}
	
	public void delete(int id) {
		Cliente entity = repository.findById(id)
				.orElseThrow(() -> new ClienteNaoEncontradoException("Nenhum cliente encontrado para este ID"));
		repository.delete(entity);
	}
	
	
	public Cliente consultarCliente(String conta) {
		Cliente cliente = new Cliente();
		cliente.setId(1);
		cliente.setNome("Mauricio Sako");
		cliente.setNumConta(conta);
		cliente.setSaldo(new BigDecimal(50000));
		return cliente;
	}
	

}
