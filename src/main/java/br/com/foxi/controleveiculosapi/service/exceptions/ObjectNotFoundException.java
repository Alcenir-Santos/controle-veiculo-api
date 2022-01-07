package br.com.foxi.controleveiculosapi.service.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2687676761803780390L;
	
	public ObjectNotFoundException(String message) {
		super(message);
	}
	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}	
