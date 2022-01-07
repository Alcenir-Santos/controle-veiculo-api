package br.com.foxi.controleveiculosapi.service.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2687676761803780390L;
	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}	
