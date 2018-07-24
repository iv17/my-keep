package rs.ac.uns.ftn.mykeepserver.service;

import rs.ac.uns.ftn.mykeepserver.model.User;

public interface UserService {

	public User findById(int id);
	public User findByEmail(String email);
	public User save(User user);
	
}
