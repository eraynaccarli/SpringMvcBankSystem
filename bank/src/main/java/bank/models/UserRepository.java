package bank.models;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import bank.RegisterRequest;

@Repository
public class UserRepository {

	@Autowired
	private SessionFactory sf;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Transactional
	public User findById(int id) {
		User u = (User)this.sf.getCurrentSession().get(User.class, id);
		return u;
	}
	
	@Transactional
	public User findByEmail(String email) {
		Criteria c = this.sf.getCurrentSession().createCriteria(User.class);
		c.add(Restrictions.eq("email", email));
		return (User)c.uniqueResult();
	}
	
	@Transactional
	public Integer save(RegisterRequest request) {
		User u = new User();
		u.setName(request.getName());
		u.setEmail(request.getEmail());
		u.setPassword(encoder.encode(request.getPassword()));
		u.setTc(request.getTc());
		u.setSurname(request.getSurname());
		return (Integer)this.sf.getCurrentSession().save(u);
	}
}
