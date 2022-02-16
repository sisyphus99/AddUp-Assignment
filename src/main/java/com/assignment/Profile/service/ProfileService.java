package com.assignment.Profile.service;

import com.assignment.Profile.dto.UserDto;

public interface ProfileService {
public String register(UserDto user);
public String updateUser(String id,UserDto user);
public UserDto getUser(String id);
}
