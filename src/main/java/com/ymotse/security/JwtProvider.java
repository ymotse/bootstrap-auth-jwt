package com.ymotse.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.ymotse.security.authentication.UserPrinciple;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@Component
public class JwtProvider {
	
	
	
    @Value("${app.secretJWT}")
    private String jwtSecret;

    @Value("${app.jwtMinuteExpiration}")
    private Integer jwtExpiration;

    
    
    public String generateJwtToken(Authentication authentication) {

        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
		                .setSubject((userPrincipal.getUsername()))
		                .setIssuedAt(new Date())
		                .setExpiration( new Date((long) (System.currentTimeMillis() + (60000*jwtExpiration)) ) )
		                .signWith(SignatureAlgorithm.HS512, jwtSecret)
		                .setIssuedAt(new Date())
		                .compact();
    }
    
    protected boolean validateJwtToken(String authToken) throws IOException, ServletException {
    	JwtAuthTokenFilter jwtAuthTokenFilter = new JwtAuthTokenFilter();
    	
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
        	e.printStackTrace();
        } catch (ExpiredJwtException e) {
            jwtAuthTokenFilter.doFilterInternal(null, null, null);
        } catch (UnsupportedJwtException e) {
        	e.printStackTrace();
        } catch (IllegalArgumentException e) {
        	e.printStackTrace();
        }
        return false;
    }
    
    public String getUserNameFromJwtToken(String token) {
        return Jwts
        		.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    
    
}