package tn.esprit.pitwin.services;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.pitwin.entities.Candidate;
import tn.esprit.pitwin.entities.User;
import tn.esprit.pitwin.interfaces.IUserService;
import tn.esprit.pitwin.utilities.BCrypt;
import tn.esprit.pitwin.utilities.UserSession;
import tn.esprit.pitwin.utilities.codeGen;

import org.apache.commons.codec.digest.DigestUtils;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


@Stateless
public class UserService extends AbstractService<User> implements IUserService {

    @PersistenceContext(unitName = "pitwin-ejb")
    private EntityManager em;

    public UserService() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
	public void addUser(User user) {
		 
		String salt=BCrypt.gensalt();
		String paass=BCrypt.hashpw(user.getPassword(), salt);
		user.setPassword(paass);
		System.out.print(paass);
		user.setEnable(false);
		user.setConfirm(codeGen.getInstance().randomString(5));
		
		
		if(user instanceof Candidate)
		{
			Candidate c = new Candidate();
			c.setEmail(user.getEmail());
			c.setFirstname(user.getFirstname());
			c.setLastname(user.getLastname());
			c.setPassword(user.getPassword());
			c.setGender(user.getGender());
			c.setBirthday(user.getBirthday());
			c.setAddress(user.getAddress());
			c.setUsername(user.getUsername());
			em.persist(c);
		}
		else
		{
			em.persist(user);
		}
		
			
	}
    
    @Override
	public boolean UsernameMailUnique(String username,String email)
	{
		TypedQuery<User> q=  em.createQuery("SELECT u from User u ",User.class); 
		
		List<User> user=q.getResultList();
		for(User u:user)
		{
		if((u.getUsername().equals(username))||(u.getEmail().equals(email)))		{
			return true;
		}
		
		}
		return false;
	}

    @Override
    public User login(String email, String password) {
        System.out.println("user: " + email);
        System.out.println("password:" + DigestUtils.md5Hex(password));
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> c = cb.createQuery(User.class);
        Root<User> emp = c.from(User.class);
        c.select(emp)
                .where(cb.equal(emp.get("email"), email),
                       cb.equal(emp.get("password"), DigestUtils.md5Hex(password)));

        try {
            return em.createQuery(c).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
	public boolean authenticate(String email, String password) {

		TypedQuery<User> q=  em.createQuery("SELECT u from User u where u.email= :email ",User.class); 
		q.setParameter("email", email);
		
		User user=q.getSingleResult();
		
		if(BCrypt.checkpw(password, user.getPassword())&& user.isEnable()==true)
		{
			UserSession.getInstance().cleanUserSession();
			UserSession.getInstance(user);
			return true;
		}

		else
			return false;

	}
    
    @Override
	public void updateToken(String email,String token)
	{
		TypedQuery<User> q=  em.createQuery("SELECT u from User u where u.email= :email ",User.class);
		q.setParameter("email", email);
		User u=q.getSingleResult();
		u.setToken(token);
		em.merge(u);
	}

	@Override
	public void confirmCode(String code, long idUser) {
		User user=em.find(User.class, idUser);
		if(code.equals(user.getConfirm()))
		{
			user.setEnable(true);
			em.merge(user);
		}
		
	}
	@Override
	public void logout() {
		System.out.println("------------------------------------------- "+UserSession.getInstance().getUsername());
		User user=em.find(User.class, UserSession.getInstance().getId());
		user.setToken(null);
		em.merge(user);
		UserSession.getInstance().cleanUserSession();
		System.out.println("********************"+UserSession.getInstance().getId()+"***********************");
	}


}


	
	