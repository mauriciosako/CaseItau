package br.com.sako.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ClienteNaoEncontradoException(String exception) {
		super(exception);
	}
	
}
