package com.dating.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dating.springboot.dto.UserDto;
import com.dating.springboot.entity.User;
import com.dating.springboot.exceptions.UserNotFoundException;
import com.dating.springboot.repository.UserRepository;
import com.dating.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDto registerUser(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		User saveduser= repository.save(user);
		UserDto saveduserdto = new UserDto();
		BeanUtils.copyProperties(saveduser, saveduserdto);
		return saveduserdto;
	}

	@Override
	public UserDto getById(Long userId) {
		Optional<User> user = repository.findById(userId);
		if (user.isPresent()) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(user.get(), userDto);
			return userDto;
		} else {
			throw new UserNotFoundException("User with id " + userId + " does not exists!");
		}

	}

	@Override
	public UserDto updateUser(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		User saveduser= repository.save(user);
		UserDto saveduserdto = new UserDto();
		BeanUtils.copyProperties(saveduser, saveduserdto);
		return saveduserdto;
	}

	@Override
	public boolean deleteUser(Long userId) {
		UserDto userDto = getById(userId);
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		repository.delete(user);
		return true;
	}

	@Override
	public List<UserDto> findAll() {
		List<User> users = repository.findAll();
		List<UserDto> usersDto = new ArrayList<>();
		for (User user : users) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(user, userDto);
			usersDto.add(userDto);
		}
		return usersDto;
	}

}
