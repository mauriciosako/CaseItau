package br.com.sako.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transferencia")
public class Transferencia implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "conta_origem", nullable = false, length = 10)
	private String contaOrigem;
	
	@Column(name = "conta_destino", nullable = false, length = 10)
	private String contaDestino;
	
	@Column(name = "valor", nullable = false)
	private BigDecimal valor;
	
	@Column(name = "sucesso")
	private boolean sucesso;
	
	@Column(name = "data_transf")
	private Date dataTransf;
	
	public String getContaOrigem() {
		return contaOrigem;
	}
	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}
	public String getContaDestino() {
		return contaDestino;
	}
	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Date getDataTransf() {
		return dataTransf;
	}
	public void setDataTransf(Date dataTransf) {
		this.dataTransf = dataTransf;
	}
	public boolean isSucesso() {
		return sucesso;
	}
	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}
	
}
