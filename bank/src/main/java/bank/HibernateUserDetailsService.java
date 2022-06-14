package bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bank.models.User;
import bank.models.UserRepository;

@Service
public class HibernateUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository repository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User u = this.repository.findByEmail(email);
		if(u == null) {
			throw new UsernameNotFoundException(email);
		}
		return u;
	}

}
