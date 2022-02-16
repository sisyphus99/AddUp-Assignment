package com.assignment.Profile.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.Profile.dto.UserDto;
import com.assignment.Profile.model.User;
import com.assignment.Profile.repository.UserRepository;

@Service
public class ProfileServiceImpl implements ProfileService {
   @Autowired
   UserRepository repo;

   
	@Override
	public String register(UserDto user) {
		// TODO Auto-generated method stub
		String token;
		//Check if the user already Exists
		List<User> foundUser=repo.findByFirstNameAndLastNameAndEmailAndPhone(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone());
		//If found.return the auth token
		if(foundUser.size()>0) {
			token = foundUser.get(0).getToken();
		}
		else {
		//Else save the user and return auth token
		String token_format= "yyyy-MM-dd-HH-mm-ss";
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(token_format);
        String generatedToken=sdf.format(cal.getTime())+"-"+user.getPhone();
        User profile=new User();
        profile.setFirstName(user.getFirstName());
        profile.setLastName(user.getLastName());
        profile.setPhone(user.getPhone());
        profile.setEmail(user.getEmail());
        profile.setToken(generatedToken);
        repo.save(profile);
        token=generatedToken;
		}
		return token;
	}

	@Override
	public String updateUser(String token,UserDto request) {
		// TODO Auto-generated method stub
		String result;
		List<User> user=repo.findByToken(token);
		if(user.size()==0) {
			result="Wrong auth token";
		}
		else {
		User searchedUser=user.get(0);
		searchedUser.setEmail(request.getEmail());
		searchedUser.setFirstName(request.getFirstName());
		searchedUser.setLastName(request.getLastName());
		searchedUser.setPhone(request.getPhone());
		repo.save(searchedUser);
		result="Success";
		}
		return result;
	}

	@Override
	public UserDto getUser(String token) {
		// TODO Auto-generated method stub
		UserDto dto;
		List<User> user=repo.findByToken(token);
	    if(user.size()==0)
	    	dto=null;
	    else {
		User searchedUser=user.get(0);
		dto=new UserDto();
		dto.setFirstName(searchedUser.getFirstName());
		dto.setLastName(searchedUser.getLastName());
		dto.setEmail(searchedUser.getEmail());
		dto.setPhone(searchedUser.getToken());
	    }
		return dto;
	}

}
