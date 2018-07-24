package rs.ac.uns.ftn.mykeepserver.exceptions;

@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException {
	
	public BadRequestException() {}
	
	public BadRequestException(String message) {
		super(message);
	}

}
