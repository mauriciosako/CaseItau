package br.com.sako.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TamanhoContaAcimaLimiteException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public TamanhoContaAcimaLimiteException(String exception) {
		super(exception);
	}
}
