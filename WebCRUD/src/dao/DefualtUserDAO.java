package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.User;

@Stateless
public class DefualtUserDAO implements UserDAO{
	
	@PersistenceContext(name="WebCRUD")
	private EntityManager em;
	
	@Override
	public void createUser(User user) { 
		em.persist(user);
//		em.flush();
	}
	
	@Override
	public List<User> getAllUsers(){Query query=em.createNamedQuery("User.findAll"); return query.getResultList(); }
	
	@Override
	public void updateUser(User user) {
		User tmp=em.find(User.class,user.getId());
		
		tmp.setName(user.getName());
		tmp.setLastname(user.getLastname());
		tmp.setMail(user.getMail());
		tmp.setPass(user.getPass());
		tmp.setType(user.getType());
		tmp.setImg(user.getImg());
		tmp.setCreated(user.getCreated());
		tmp.setModified(user.getModified());	
		em.persist(tmp);
	}
	
	@Override
	public void deleteUser(int userID) { 
//	IllegalArgumentException - if the instance is not an entity or is a detached entity
		try{ 
			em.remove(em.find(User.class,userID));
		}catch(IllegalArgumentException  e){ System.err.println("@ERR -1 IllegalArgumentException");}
	}

}
