package com.ymotse.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@Data
@Entity
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "login" })
})
@NoArgsConstructor
@ToString
public class User implements UserDetails {
	
	
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@Id
	@SequenceGenerator(name = "seq_user_id", sequenceName = "seq_user_id", allocationSize = 1)
	@GeneratedValue(generator = "seq_user_id", strategy = GenerationType.SEQUENCE)
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "login", unique = true, length = 30, nullable = false, updatable = false)
	private String login;
	
	@JsonIgnore
	@Column(name = "password", nullable = false)
	private String pass;
	
	@Column(name = "enable", nullable = false)
	private boolean enable;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		auths.add(new SimpleGrantedAuthority("AUTH_STRING"));
		return auths;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return this.pass;
	}

	@JsonIgnore
	@Override
	public String getUsername() {
		return this.login;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return this.enable;
	}

}
