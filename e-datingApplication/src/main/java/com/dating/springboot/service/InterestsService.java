package com.dating.springboot.service;

import java.util.List;

import com.dating.springboot.dto.InterestsDto;
import com.dating.springboot.dto.UserDto;

public interface InterestsService {

	public InterestsDto createInterest(InterestsDto interests);
	public InterestsDto updateInterest(InterestsDto interests);
	public boolean deleteInterest(Long interestId);
	public InterestsDto getById(Long interestId);
	public List<InterestsDto> getInterestsByUserId(Long userId);
	public List<InterestsDto> findAll();
	
}
