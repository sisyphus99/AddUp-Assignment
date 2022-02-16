package com.assignment.Profile.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.assignment.Profile.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	List<User> findByToken(String token);
	
	List<User> findByFirstNameAndLastNameAndEmailAndPhone(String firstName,String lastName,String email,String phone);
}