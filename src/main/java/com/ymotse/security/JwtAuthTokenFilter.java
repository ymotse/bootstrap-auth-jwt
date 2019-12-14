package com.ymotse.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ymotse.authentication.LoginDetailsService;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
public class JwtAuthTokenFilter extends OncePerRequestFilter {

	
	
    @Autowired private JwtProvider tokenProvider;

    @Autowired private LoginDetailsService loginDetailsService;

    
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        
    	try {
	        String jwt = getJwt(request);
	        
	        if (jwt !=null && tokenProvider.validateJwtToken(jwt)) {
	            String username = tokenProvider.getUserNameFromJwtToken(jwt);
	            
	            UserDetails userDetails = loginDetailsService.loadUserByUsername(username);
	            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	        }
	        else {
	        	response.setStatus(HttpStatus.UNAUTHORIZED.value());
	        }
	        
			filterChain.doFilter(request, response);
    	} catch (Exception e) {
    		response.setStatus(HttpStatus.UNAUTHORIZED.value());
    	}
    }

    
    private String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        	
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
        	return authHeader.replace("Bearer ","");
        }
        return null;
    }
    
    
}
