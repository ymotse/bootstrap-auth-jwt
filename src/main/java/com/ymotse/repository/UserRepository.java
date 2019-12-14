package com.ymotse.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ymotse.entity.User;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	
	
	Optional<User> findByLogin(String login);
	
}
