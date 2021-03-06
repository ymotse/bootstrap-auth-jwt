package com.ymotse.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ymotse.security.JwtProvider;
import com.ymotse.security.authentication.FormUser;
import com.ymotse.security.authentication.JwtResponse;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@RestController
@RequestMapping("/api")
public class PostControllers {
	
	
	
	@Autowired private AuthenticationManager authenticationManager;
	
	@Autowired private JwtProvider jwtProvider;
	
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody FormUser userRequest) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
	
}
