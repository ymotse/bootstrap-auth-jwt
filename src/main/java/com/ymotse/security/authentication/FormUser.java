package com.ymotse.security.authentication;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@Data
@NoArgsConstructor
public class FormUser {
	
	
	
	@JsonProperty("username")
    @NotBlank
    @Size(min = 3, max = 30)
    private String username;

	@JsonProperty("password")
    @NotBlank
    @Size(min = 3, max = 30)
    private String password;
    
}