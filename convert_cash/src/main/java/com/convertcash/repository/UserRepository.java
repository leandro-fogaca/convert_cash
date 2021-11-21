package com.convertcash.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.convertcash.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	public Optional<User> findByEmail(String email);

}