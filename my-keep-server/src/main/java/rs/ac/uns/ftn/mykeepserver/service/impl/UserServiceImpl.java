package rs.ac.uns.ftn.mykeepserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	public User findByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User changePassword(String email, String newPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = findByEmail(email);
		user.setPassword(encoder.encode(newPassword));
		save(user);
		return user;
	}

	@Override
	public User update(String email, User request) {
		
		User user = findByEmail(email);
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		save(user);
		
		return user;
	}

}
