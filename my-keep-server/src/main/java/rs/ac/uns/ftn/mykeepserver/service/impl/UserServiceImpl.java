package rs.ac.uns.ftn.mykeepserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.mykeepserver.model.User;
import rs.ac.uns.ftn.mykeepserver.repository.UserRepository;
import rs.ac.uns.ftn.mykeepserver.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public User findById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

}
