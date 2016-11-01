package manager;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
//import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
//import javax.inject.Named;

import dao.UserDAO;
import model.User;

//@Named(value="DefaultUserManager")
//@ApplicationScoped
@ManagedBean
@SessionScoped // Look in updateUser()
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
	
	/**
	 * By Sal. Alba.	
	 * here is good example to show if the entity is detached or not 
	 * first we pass a simple ID 'int'
	 * second one we try to pass entity 
	 */
	public String findById(int userID){  this.user=userDAO.findById(userID); return "view";}	
	public String viewUser(User user){ this.user=userDAO.viewUser(user); return "view"; }
	
	
	/**
	 *  By Sal. Alba.
	 *  We need @SessionScoped or @ApplicationScoped because help us to edit Entity
	 *  Note. when we the session from faces package and after editing when try to  create new Entity
	 *  the last edit will be saved
	 *  it's different when we use from javax.enterprise.context.SessionScoped;
	 */
	public String updateUser(User us){user=us; return"edit";} // will go to edit.xhtml
	public String updateUser(){		
		User tmp=new User();
		tmp.setId(this.user.getId());
		tmp.setName(this.user.getName());
		tmp.setLastname(this.user.getLastname());
		tmp.setMail(this.user.getMail());
		tmp.setPass(this.user.getPass());
		tmp.setImg(this.user.getImg());
		tmp.setType(this.user.getType());
		tmp.setCreated(this.user.getCreated());
		tmp.setModified(this.user.getModified());	
		userDAO.updateUser(tmp); 
		return "index";
	}	
	

	
}
