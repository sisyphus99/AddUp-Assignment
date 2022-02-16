package com.assignment.Profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.Profile.dto.UserDto;
import com.assignment.Profile.service.ProfileServiceImpl;

@RestController
public class ProfileController {
@Autowired
ProfileServiceImpl service;
@PostMapping("/signUp")
public  ResponseEntity hello(@RequestBody UserDto user) {
	 String token=service.register(user);
	 return new ResponseEntity<>(token, HttpStatus.OK);
}

@GetMapping("/getUser" )
public ResponseEntity getUser(@RequestHeader("auth-token") String token) throws Exception {
    UserDto response=service.getUser(token);
    return new ResponseEntity<>(response, HttpStatus.OK);
}

@PutMapping(path = "/updateUser")
public ResponseEntity updateUser(@RequestHeader("auth-token") String token, @RequestBody UserDto user) 
throws Exception {
    String updateStatus=service.updateUser(token, user);
    return new ResponseEntity<>(updateStatus, HttpStatus.OK);
    
}

}
