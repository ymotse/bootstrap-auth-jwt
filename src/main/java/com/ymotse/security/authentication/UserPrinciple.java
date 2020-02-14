package com.ymotse.security.authentication;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ymotse.entity.User;

import lombok.Getter;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
public class UserPrinciple implements UserDetails {
	
	
	
	private static final long serialVersionUID = 1L;

	@Getter 
	private Long id;

    @Getter 
    private String username;

    @JsonIgnore
    @Getter 
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    
    
    public UserPrinciple(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
    
    
    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = user
        		.getRoles()
        		.stream()
        		.map(
    				role -> new SimpleGrantedAuthority(role.getName())
    			)
        		.collect(
    				Collectors.toList()
    			);
        
        return new UserPrinciple(user.getUserId(), user.getLogin(), user.getPassword(), authorities);
    }
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }
    
}
