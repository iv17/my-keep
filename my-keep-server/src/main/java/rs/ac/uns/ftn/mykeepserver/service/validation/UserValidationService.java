package rs.ac.uns.ftn.mykeepserver.service.validation;

import org.springframework.security.core.Authentication;

public interface UserValidationService {

	public void validateIfUserExist(Authentication authentication);
	public void validateIfUserExist(String email);
	public void validateIfUserExist(Authentication authentication, String password);
	public void validateIfEmailIsUnique(String email);
	public void validateIfPasswordMatch(String password, String repeatedPassword);
	
}
