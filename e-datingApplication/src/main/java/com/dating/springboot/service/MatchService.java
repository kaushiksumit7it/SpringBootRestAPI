package com.dating.springboot.service;

import java.util.List;

import com.dating.springboot.dto.MatchDto;
import com.dating.springboot.dto.UserDto;
import com.dating.springboot.filter.Filter;

public interface MatchService {

	public List<MatchDto> getAllMatches(Long userId);

	public MatchDto saveMatch(MatchDto match);

	public List<UserDto> getPotentialMatches(Long userId, List<Filter> filters);
	public List<UserDto> getUsersDto(List<Filter> filters);
	
}