package br.com.sako.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.sako.exception.ClienteNaoEncontradoException;
import br.com.sako.exception.ContaJaCadastradaException;
import br.com.sako.model.Cliente;
import br.com.sako.repository.ClienteRepository;

@Service
public class ClienteServices {
	
	@Autowired
	ClienteRepository repository;
	
	public Cliente cadastrarCliente(Cliente cliente) {
		Optional<Cliente> entity = repository.findByNumConta(cliente.getNumConta());
		if(entity.isPresent()) throw new ContaJaCadastradaException("Número de conta já cadastrado.");
		
		return repository.save(cliente);
	}
	
	public List<Cliente> listarClientes() {
		Sort sort = Sort.by("nome");
		return repository.findAll(sort);
	}
	
	public void excluirCliente(String conta) {
		Cliente entity = repository.findByNumConta(conta)
				.orElseThrow(() -> new ClienteNaoEncontradoException("Nenhum cliente encontrado para esta conta."));
		repository.delete(entity);
	}
	
	
	public Cliente consultarCliente(String conta) {
		Cliente cliente = repository.findByNumConta(conta)
				.orElseThrow(() -> new ClienteNaoEncontradoException("Nenhum cliente encontrado para esta conta."));
		
		cliente.setId(cliente.getId());
		cliente.setNome(cliente.getNome());
		cliente.setNumConta(cliente.getNumConta());
		cliente.setSaldo(cliente.getSaldo());
		return cliente;
	}
	
	public void somaSaldo(String conta, float valor) {
		repository.updateSaldoSoma(conta, valor);
	}
	
	public void subtraiSaldo(String conta, float valor) {
		repository.updateSaldoSubtrai(conta, valor);
	}

}
