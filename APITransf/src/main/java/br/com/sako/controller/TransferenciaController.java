package br.com.sako.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sako.model.Transferencia;
import br.com.sako.services.TransferenciaServices;

@RestController
@RequestMapping("/transf")
public class TransferenciaController {
	
	@Autowired
	private TransferenciaServices transferenciaServices;
	
	@RequestMapping(value="/{conta}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transferencia> buscarTransferencias(@PathVariable("conta") String conta) {
		return transferenciaServices.buscarTransferencias(conta);
	}
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Transferencia realizaTransferencia(@RequestBody Transferencia transferencia) {
		return transferenciaServices.realizaTransferencia(transferencia);
	}
	
}
