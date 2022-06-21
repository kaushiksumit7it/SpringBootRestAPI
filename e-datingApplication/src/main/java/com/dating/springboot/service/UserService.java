package com.dating.springboot.service;

import java.util.List;
import java.util.Optional;

import com.dating.springboot.dto.UserDto;

public interface UserService {

	public UserDto registerUser(UserDto user);

	public UserDto getById(Long userId);

	public UserDto updateUser(UserDto user);

	public boolean deleteUser(Long userId);

	public List<UserDto> findAll();

}
