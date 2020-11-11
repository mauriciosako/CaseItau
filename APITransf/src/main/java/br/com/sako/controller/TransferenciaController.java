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
import br.com.sako.service.TransferenciaServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Transferência Endpoint", description = "Endpoint para operações de transferência.")
@RestController
@RequestMapping("/transf")
public class TransferenciaController {
	
	@Autowired
	private TransferenciaServices transferenciaServices;

	@ApiOperation(value = "Consultar as transferências envolvendo uma conta em ordem descrescente de data.")
	@RequestMapping(value="/{conta}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transferencia> buscarTransferencias(@PathVariable("conta") String conta) {
		return transferenciaServices.buscarTransferencias(conta);
	}
	
	@ApiOperation(value = "Realizar transferência de um valor entre duas contas.")
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Transferencia realizaTransferencia(@RequestBody Transferencia transferencia) {
		return transferenciaServices.realizaTransferencia(transferencia);
	}
	
}
