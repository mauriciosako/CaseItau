package br.com.sako.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sako.model.Cliente;
import br.com.sako.service.ClienteServices;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteServices services;
	
	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cliente> listarClientes() {
		return services.listarClientes();
	}

	@RequestMapping(value="/{conta}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Cliente consultarCliente(@PathVariable("conta") String conta) {
		return services.consultarCliente(conta);
	}
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
		return services.cadastrarCliente(cliente);
	}
	
	@RequestMapping(value="/{conta}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public void excluirCliente(@PathVariable("conta") String conta) {
		services.excluirCliente(conta);
	}
	
}
