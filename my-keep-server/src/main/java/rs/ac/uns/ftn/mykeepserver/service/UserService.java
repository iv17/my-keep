package rs.ac.uns.ftn.mykeepserver.service;

import rs.ac.uns.ftn.mykeepserver.model.User;

public interface UserService {

	public User findById(int id);
	public User findByEmail(String email);
	public User findByEmailAndPassword(String email, String password);
	public User save(User user);
	public User update(int id, User user);
	public User changePassword(int id, String newPassword);
	
}
