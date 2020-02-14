package com.ymotse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ymotse.entity.User;
import com.ymotse.repository.UserRepository;
import com.ymotse.security.JwtProvider;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@RestController
@RequestMapping("/api")
public class GetControllers {
	
	
    
    @Autowired private JwtProvider jwtProvider;
    
    @Autowired private UserRepository userRepository;
    
    
    
    @GetMapping(value = "/user")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<User> stores(@RequestHeader(value = "Authorization") String token) {

    	String username = jwtProvider.getUserNameFromJwtToken(token.replace("Bearer ",""));
    	
    	
    	System.out.println( userRepository.findByLogin(username).get().toString() );
    	
		return new ResponseEntity<User>(userRepository.findByLogin(username).get(), HttpStatus.OK);
	}
    
	
}
