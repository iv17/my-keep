package rs.ac.uns.ftn.mykeepserver.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionResolver {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> notFoundException(NotFoundException exception) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<String>(exception.getMessage(), headers, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<String> badRequestException(BadRequestException exception) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<String>(exception.getMessage(), headers, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<String> forbiddenException(ForbiddenException exception) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<String>(exception.getMessage(), headers, HttpStatus.FORBIDDEN);
	}
	
}
