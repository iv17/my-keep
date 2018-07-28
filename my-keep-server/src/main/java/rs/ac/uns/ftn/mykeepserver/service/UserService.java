package rs.ac.uns.ftn.mykeepserver.service;

import rs.ac.uns.ftn.mykeepserver.model.User;

public interface UserService {

	public User findById(int id);
	public User findByEmail(String email);
	public User findByEmailAndPassword(String email, String password);
	public User save(User user);
	public User update(String email, User user);
	public User changePassword(String email, String newPassword);
	
}
