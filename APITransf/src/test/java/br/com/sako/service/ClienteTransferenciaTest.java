package br.com.sako.service;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sako.model.Cliente;
import br.com.sako.model.Transferencia;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClienteTransferenciaTest {
	
	@Autowired
	private ClienteServices clienteServices;
	
	@Autowired
	private TransferenciaServices transferenciaServices;
	
	@Test
	public void teste1CadastrarCliente() throws Exception {
		
		//1
		Cliente cliente1 = new Cliente();
		cliente1.setNome("Mauricio Sako");
		cliente1.setNumConta("0000000001");
		cliente1.setSaldo(1000);
		
		Cliente cliente2 = clienteServices.cadastrarCliente(cliente1);
		
		Assertions.assertEquals(cliente2.getNome(), "Mauricio Sako");
		Assertions.assertEquals(cliente2.getNumConta(), "0000000001");
		Assertions.assertEquals(cliente2.getSaldo(), 1000);
		
		//2
		cliente1 = new Cliente();
		cliente1.setNome("Daniela Mello");
		cliente1.setNumConta("0000000002");
		cliente1.setSaldo(2000);
		
		cliente2 = clienteServices.cadastrarCliente(cliente1);
		
		Assertions.assertEquals(cliente2.getNome(), "Daniela Mello");
		Assertions.assertEquals(cliente2.getNumConta(), "0000000002");
		Assertions.assertEquals(cliente2.getSaldo(), 2000);
		
		//3
		cliente1 = new Cliente();
		cliente1.setNome("Lisiane Sako");
		cliente1.setNumConta("0000000003");
		cliente1.setSaldo(3000);
		
		cliente2 = clienteServices.cadastrarCliente(cliente1);
		
		Assertions.assertEquals(cliente2.getNome(), "Lisiane Sako");
		Assertions.assertEquals(cliente2.getNumConta(), "0000000003");
		Assertions.assertEquals(cliente2.getSaldo(), 3000);
		
	}

	@Test
	public void teste2ListarClientesTest() {
		List<Cliente> clientes =  clienteServices.listarClientes();
		Assertions.assertEquals(clientes.size(), 3);
		
		//O serviço retorna na ordem alfabetica pelo nome
		//Por isso, o teste fica nessa ordem
		Assertions.assertEquals(clientes.get(0).getNome(), "Daniela Mello");
		Assertions.assertEquals(clientes.get(0).getNumConta(), "0000000002");
		Assertions.assertEquals(clientes.get(0).getSaldo(), 2000);
		
		Assertions.assertEquals(clientes.get(1).getNome(), "Lisiane Sako");
		Assertions.assertEquals(clientes.get(1).getNumConta(), "0000000003");
		Assertions.assertEquals(clientes.get(1).getSaldo(), 3000);
		
		Assertions.assertEquals(clientes.get(2).getNome(), "Mauricio Sako");
		Assertions.assertEquals(clientes.get(2).getNumConta(), "0000000001");
		Assertions.assertEquals(clientes.get(2).getSaldo(), 1000);
	}

	
	@Test
	public void teste3ConsultarClientePelaContaTest() {
		Cliente cliente = clienteServices.consultarCliente("0000000001");
		Assertions.assertEquals(cliente.getNome(), "Mauricio Sako");
		Assertions.assertEquals(cliente.getNumConta(), "0000000001");
		Assertions.assertEquals(cliente.getSaldo(), 1000);
		
		cliente = clienteServices.consultarCliente("0000000002");
		Assertions.assertEquals(cliente.getNome(), "Daniela Mello");
		Assertions.assertEquals(cliente.getNumConta(), "0000000002");
		Assertions.assertEquals(cliente.getSaldo(), 2000);
		
		cliente = clienteServices.consultarCliente("0000000003");
		Assertions.assertEquals(cliente.getNome(), "Lisiane Sako");
		Assertions.assertEquals(cliente.getNumConta(), "0000000003");
		Assertions.assertEquals(cliente.getSaldo(), 3000);
		
	}
	
	@Test
	public void teste4TransferenciaTest() {
		Transferencia transferencia = new Transferencia();
		transferencia.setContaOrigem("0000000001");
		transferencia.setContaDestino("0000000002");
		transferencia.setValor(100);
		Transferencia transferenciaRet = transferenciaServices.realizaTransferencia(transferencia);

		Assertions.assertEquals(transferenciaRet.isSucesso(), true);
		
		
		transferencia = new Transferencia();
		transferencia.setContaOrigem("0000000002");
		transferencia.setContaDestino("0000000003");
		transferencia.setValor(200);
		transferenciaRet = transferenciaServices.realizaTransferencia(transferencia);

		Assertions.assertEquals(transferenciaRet.isSucesso(), true);
		
		
		transferencia = new Transferencia();
		transferencia.setContaOrigem("0000000003");
		transferencia.setContaDestino("0000000001");
		transferencia.setValor(300);
		transferenciaRet = transferenciaServices.realizaTransferencia(transferencia);

		Assertions.assertEquals(transferenciaRet.isSucesso(), true);

	}
	
	@Test
	public void teste5ListarTransferenciasTest() {

		List<Transferencia> transferencias = transferenciaServices.buscarTransferencias("0000000001");
		Assertions.assertEquals(transferencias.size(), 2);
		
		transferencias = transferenciaServices.buscarTransferencias("0000000002");
		Assertions.assertEquals(transferencias.size(), 2);
		
		transferencias = transferenciaServices.buscarTransferencias("0000000003");
		Assertions.assertEquals(transferencias.size(), 2);

	}
	
	@Test
	public void teste6SaldoAposTransferenciaTest() {
		Cliente cliente = clienteServices.consultarCliente("0000000001");
		Assertions.assertEquals(cliente.getSaldo(), 1200);
		
		cliente = clienteServices.consultarCliente("0000000002");
		Assertions.assertEquals(cliente.getSaldo(), 1900);
		
		cliente = clienteServices.consultarCliente("0000000003");
		Assertions.assertEquals(cliente.getSaldo(), 2900);

	}
	
	
	
}
