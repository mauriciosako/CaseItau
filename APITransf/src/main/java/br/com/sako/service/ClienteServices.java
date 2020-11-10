package br.com.sako.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.sako.exception.ClienteNaoEncontradoException;
import br.com.sako.exception.ContaJaCadastradaException;
import br.com.sako.exception.ContaNaoInformadaException;
import br.com.sako.exception.TamanhoContaAcimaLimiteException;
import br.com.sako.model.Cliente;
import br.com.sako.repository.ClienteRepository;

@Service
public class ClienteServices {
	
	@Autowired
	ClienteRepository repository;
	
	/**
	 * Cadastra um novo cliente e 
	 * lança exceção, caso o número de conta passado esteja em branco, ou tenha tamanha maior que 10 ou
	 * já esteja cadastrado
	 * @param cliente
	 * @return
	 */
	public Cliente cadastrarCliente(Cliente cliente) {
		
		if(cliente.getNumConta() == null || "".equals(cliente.getNumConta())) {
			throw new ContaNaoInformadaException("Número de conta não informado informado.");
		}
		
		if(cliente.getNumConta().length() > 10) {
			throw new TamanhoContaAcimaLimiteException("Tamanho do número da conta maior que o limite de 10.");
		}
		
		//Verifica se já existe um cliente com essa conta
		Optional<Cliente> entity = repository.findByNumConta(cliente.getNumConta());
		if(entity.isPresent()) throw new ContaJaCadastradaException("Número de conta já cadastrado.");
		
		return repository.save(cliente);
	}
	
	/**
	 * Lista todos os clientes em ordem alfabética
	 * @return
	 */
	public List<Cliente> listarClientes() {
		Sort sort = Sort.by("nome");
		return repository.findAll(sort);
	}
	
	
	/**
	 * Consulta as informações de um cliente pelo número de sua conta e retorna erro, caso não haja nenhum cliente cadastrado
	 * para a conta passada como parâmetro
	 * @param conta
	 * @return
	 */
	public Cliente consultarCliente(String conta) {
		//Se não encontrar o cliente lança exceção e retorna erro.
		Cliente cliente = repository.findByNumConta(conta)
				.orElseThrow(() -> new ClienteNaoEncontradoException("Nenhum cliente encontrado para esta conta."));
		
		cliente.setId(cliente.getId());
		cliente.setNome(cliente.getNome());
		cliente.setNumConta(cliente.getNumConta());
		cliente.setSaldo(cliente.getSaldo());
		return cliente;
	}
	
	/**
	 * Adiciona o valor ao saldo da conta 
	 * @param conta
	 * @param valor
	 */
	public void somaSaldo(String conta, float valor) {
		repository.updateSaldoSoma(conta, valor);
	}
	
	/**
	 * Subtrai o valor do saldo da conta
	 * @param conta
	 * @param valor
	 */
	public void subtraiSaldo(String conta, float valor) {
		repository.updateSaldoSubtrai(conta, valor);
	}

}
