package br.com.sako.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sako.exception.SaldoInsuficienteException;
import br.com.sako.exception.ValorAcimaLimiteException;
import br.com.sako.model.Cliente;
import br.com.sako.model.Transferencia;
import br.com.sako.repository.TransferenciaRepository;

@Service
public class TransferenciaServices {
	
	@Autowired
	TransferenciaRepository repository;
	
	@Autowired
	ClienteServices clienteServ;
	
	/**
	 * Realiza a transferência do entre as duas contas, subtraindo o valor da conta origem
	 * e adicionando o valor à conta destino
	 * @param transferencia
	 * @return
	 */
	@Transactional(dontRollbackOn = {ValorAcimaLimiteException.class, SaldoInsuficienteException.class})
	public Transferencia realizaTransferencia(Transferencia transferencia) {
		transferencia.setDataTransf(new Date());

		Cliente clienteOri = clienteServ.consultarCliente(transferencia.getContaOrigem());
		
		/*
		 * Se o valor da transferência é maior que 1000, grava a transferência com sucesso = false
		 * e lança a exceção retornando erro
		 */
		if(transferencia.getValor() > 1000) {
			transferencia.setSucesso(false);
			repository.save(transferencia);
			throw new ValorAcimaLimiteException(
					"Valor da transferência acima do limite de R$1000");
		}
		
		
		/*
		 * Se o valor da transferência é maior que saldo na conta, grava a transferência com sucesso = false
		 * e lança a exceção retornando erro
		 */
		if(clienteOri.getSaldo() < transferencia.getValor()) {
			transferencia.setSucesso(false);
			repository.save(transferencia);
			throw new SaldoInsuficienteException(
					"Saldo de R$"+clienteOri.getSaldo()+" é insuficiente para transferência de R$"+ transferencia.getValor());
		}
		
		Cliente clienteDest = clienteServ.consultarCliente(transferencia.getContaDestino());
		
		//subtrai o valor da conta origem
		clienteServ.subtraiSaldo(clienteOri.getNumConta(), transferencia.getValor());
		
		//adiciona o valor da conta destino
		clienteServ.somaSaldo(clienteDest.getNumConta(), transferencia.getValor());
		
		transferencia.setSucesso(true);
		
		return  repository.save(transferencia);
	}
	
	/**
	 * Consulta as transferências envolvendo uma conta em ordem descrescente de data
	 * @param conta
	 * @return
	 */
	public List<Transferencia> buscarTransferencias(String conta) {
		List<Transferencia> transferencias = repository.findByNumConta(conta);
		
		return transferencias;
	}
}
