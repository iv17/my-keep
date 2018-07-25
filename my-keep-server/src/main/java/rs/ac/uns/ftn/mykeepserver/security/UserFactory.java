package rs.ac.uns.ftn.mykeepserver.security;

import rs.ac.uns.ftn.mykeepserver.model.User;

public class UserFactory {

	
	private UserFactory() {
		super();
	}

	public static SecurityUser create(User user) {
        SecurityUser securityUser = new SecurityUser();
        securityUser.setFirstName(user.getFirstName());
        securityUser.setLastName(user.getLastName());
        securityUser.setEmail(user.getEmail());
        securityUser.setPassword(user.getPassword());
         
        return securityUser;
	}
	
}