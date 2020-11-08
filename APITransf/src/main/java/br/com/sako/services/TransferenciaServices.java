package br.com.sako.services;

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
	
	@Transactional(dontRollbackOn = {ValorAcimaLimiteException.class, SaldoInsuficienteException.class})
	public Transferencia realizaTransferencia(Transferencia transferencia) {
		transferencia.setDataTransf(new Date());

		Cliente clienteOri = clienteServ.consultarCliente(transferencia.getContaOrigem());
		
		if(transferencia.getValor() > 1000) {
			transferencia.setSucesso(false);
			repository.save(transferencia);
			throw new ValorAcimaLimiteException(
					"Valor da transferência acima do limite de R$1000");
		}
		
		if(clienteOri.getSaldo() < transferencia.getValor()) {
			transferencia.setSucesso(false);
			repository.save(transferencia);
			throw new SaldoInsuficienteException(
					"Saldo de R$"+clienteOri.getSaldo()+" é insuficiente para transferência de R$"+ transferencia.getValor());
		}
		
		Cliente clienteDest = clienteServ.consultarCliente(transferencia.getContaDestino());
		
		clienteServ.somaSaldo(clienteDest.getNumConta(), transferencia.getValor());
		clienteServ.subtraiSaldo(clienteOri.getNumConta(), transferencia.getValor());
		
		transferencia.setSucesso(true);
		
		return  repository.save(transferencia);
	}
	
	public List<Transferencia> buscarTransferencias(String conta) {
		
		//incluir exceção
		
		List<Transferencia> transferencias = repository.findByNumConta(conta);
		
		return transferencias;
	}
}
