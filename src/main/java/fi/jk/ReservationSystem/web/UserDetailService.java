package fi.jk.ReservationSystem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fi.jk.ReservationSystem.domain.User;
import fi.jk.ReservationSystem.domain.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {
	private final UserRepository uRepository;
	
	@Autowired
	public UserDetailService(UserRepository userRepository) {
		this.uRepository = userRepository;
	}
	
		// Check password and role for current user (authenticate and authorize user)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException  {   
    	User currentUser = uRepository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(
        		username,
        		currentUser.getPassword(),
        		AuthorityUtils.createAuthorityList(currentUser.getRole()));
        return user;
    } 

}
