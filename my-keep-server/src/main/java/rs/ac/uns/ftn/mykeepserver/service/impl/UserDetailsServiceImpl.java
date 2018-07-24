package rs.ac.uns.ftn.mykeepserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.mykeepserver.model.User;
import rs.ac.uns.ftn.mykeepserver.repository.UserRepository;
import rs.ac.uns.ftn.mykeepserver.security.UserFactory;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) {
		User user = userRepository.findByEmail(email);
		
		return UserFactory.create(user);
		
	}

}
