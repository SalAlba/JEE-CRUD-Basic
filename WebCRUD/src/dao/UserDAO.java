package dao;

import java.util.List;

import model.User;

public interface UserDAO {
	public void createUser(User user);
	public List<User> getAllUsers();
	public void updateUser(User user);
	public void deleteUser(int userID);
}
