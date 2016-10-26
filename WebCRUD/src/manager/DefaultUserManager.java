package manager;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
//import javax.inject.Named;
import javax.enterprise.context.SessionScoped; // it's enterprise 

import dao.UserDAO;
import model.User;

//@Named(value="DefaultUserManager")
@ManagedBean
@SessionScoped
public class DefaultUserManager implements Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private UserDAO userDAO;
	
	private User user=new User();
	
	// get/set... always
	public User getUser() {return user; }
	public void setUser(User user) { this.user = user; }
	
	public String createUser(){ 
		userDAO.createUser(user);
//		user=new User(); // I don't know if we need this
		return "index"; // will return to index.xhtml
	}
	
	public List<User> getAllUsers(){ return userDAO.getAllUsers();}
	
	public void deleteUser(int userID){  userDAO.deleteUser(userID); }
	
	public String updateUser(User us){user=us; return"edit";} // will return to edit.xhtml
	public String updateUser(){userDAO.updateUser(user); return "index";}	
	
}
