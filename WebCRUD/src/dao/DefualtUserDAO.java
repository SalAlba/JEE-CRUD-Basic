package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.User;

@Stateless
public class DefualtUserDAO implements UserDAO {

	@PersistenceContext(name = "WebCRUD")
	private EntityManager em;

	@Override
	public void createUser(User user) { em.persist(user); }
	
	@Override
	public User findById(int userID){ return em.find(User.class,userID); }
	
	@Override
	public User viewUser(User user){ return em.find(User.class,user.getId()); }	
	
	@Override
	public List<User> getAllUsers() {
		Query query = em.createNamedQuery("User.findAll");
		return query.getResultList();
	}

	@Override
	public void updateUser(User user) {em.merge(user); }

	@Override
	public void deleteUser(int userID) {
		// IllegalArgumentException - if the instance is not an entity or is a
		// detached entity
		try {
			em.remove(em.find(User.class, userID));
		} catch (IllegalArgumentException e) {
			System.err.println("@ERR -1 IllegalArgumentException");
		}
	}

}
