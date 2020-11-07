package br.com.sako.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sako.model.Transferencia;
import br.com.sako.repository.TransferenciaRepository;

@Service
public class TransferenciaServices {
	
	@Autowired
	TransferenciaRepository repository;
	
	public Transferencia realizaTransferencia(Transferencia transferencia) {
		transferencia.setSucesso(true);
		transferencia.setDataTransf(new Date());
		return  repository.save(transferencia);
	}
	
	public List<Transferencia> buscarTransferencias(String conta) {
		return repository.findAll();
	}
}
