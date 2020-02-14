package com.ymotse.security.authentication;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ymotse.entity.User;
import com.ymotse.repository.UserRepository;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@Component
@Transactional
@Repository
public class LoginDetailsService implements UserDetailsService {

	
	
	@Autowired private UserRepository userRepository;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String login) {
		User user = userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User not found by: " + login));
		
		return UserPrinciple.build(user);
	}
	
}
