package rs.ac.uns.ftn.mykeepserver.service.validation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.mykeepserver.exceptions.NotFoundException;
import rs.ac.uns.ftn.mykeepserver.service.UserService;
import rs.ac.uns.ftn.mykeepserver.service.validation.UserValidationService;

@Component
public class UserValidationServiceImpl implements UserValidationService {

	@Autowired
	private UserService userService;
	
	String exception = "User with that email doesn't exist!";
	
	@Override
	public void validateIfUserExist(Authentication authentication) {
		if(userService.findByEmail(authentication.getName()) == null) {
			throw new NotFoundException(exception);
		}
	}
	
	@Override
	public void validateIfUserExist(String email) {
		if(userService.findByEmail(email) == null) {
			throw new NotFoundException(exception);
		}
	}
	
	@Override
	public void validateIfEmailIsUnique(String email) {
		if(userService.findByEmail(email) != null) {
			throw new NotFoundException(exception);
		}
	}

}
