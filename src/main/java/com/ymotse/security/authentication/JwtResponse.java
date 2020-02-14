package com.ymotse.security.authentication;

import java.util.Calendar;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@Data
@RequiredArgsConstructor
public class JwtResponse {
	
	
	
	private String type = "Bearer ";
	
    @NonNull 
    private String token;
    
    private String created = Calendar.getInstance().getTime().toString();
    
}