package com.dating.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dating.springboot.dto.MatchDto;
import com.dating.springboot.dto.UserDto;
import com.dating.springboot.entity.Match;
import com.dating.springboot.entity.User;
import com.dating.springboot.exceptions.MatchNotFoundException;
import com.dating.springboot.filter.Filter;
import com.dating.springboot.filter.FilterUtils;
import com.dating.springboot.repository.MatchRepository;
import com.dating.springboot.service.MatchService;
import com.dating.springboot.service.UserService;

@Service
public class MatchServiceImpl implements MatchService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchService.class);

	@Autowired
	MatchRepository matchRepository;

	@Autowired
	private UserService userService;
	

	public List<MatchDto> getAllMatches(Long userId) {
		List<Match> matches = matchRepository.findAll();
		List<MatchDto> matchesDto = new ArrayList<>();
		for (Match match : matches) {
			if(userService.getById(userId)!=null)
			{
			if(match.getUserId()==userId)
			{	
			MatchDto matchDto = new MatchDto();
			BeanUtils.copyProperties(match, matchDto);
			matchesDto.add(matchDto);
			}
			else
			{
			throw new MatchNotFoundException("No match found for the user id "+ userId);	
			}
			}
			}
		return matchesDto;
	}

	public MatchDto saveMatch(MatchDto matchDto) {
		Match match = new Match();
		BeanUtils.copyProperties(matchDto, match);
		Match savedmatch = matchRepository.save(match);
		MatchDto savedmatchDto = new MatchDto();
		BeanUtils.copyProperties(savedmatch, savedmatchDto);
		return savedmatchDto;

	}

	@Override
	public List<UserDto> getPotentialMatches(Long userId, List<Filter> filters) {
		List<UserDto> filteredUsers = getUsersDto(filters);
		return filteredUsers;
	}
	
	@Override
	public List<UserDto> getUsersDto(List<Filter> filters) {
		
		List<UserDto> filteredUsers = userService.findAll();
		if (filters != null && !filters.isEmpty()) {
			for (Filter filter : filters) {
				filteredUsers = applyFilter(filter, filteredUsers);
			}
		}
		return filteredUsers;
	}

	private List<UserDto> applyFilter(Filter filter, List<UserDto> filteredUsers) {
		switch (filter.getType()) {
		case AGE:
			filteredUsers = FilterUtils.applyAgeFilter(filteredUsers, filter.getValues());
			break;
		case CITY:
			filteredUsers = FilterUtils.applyLocationFilter(filteredUsers, filter.getValues(), true);
			break;
		case COUNTRY:
			filteredUsers = FilterUtils.applyLocationFilter(filteredUsers, filter.getValues(), false);
			break;
		case GENDER:
			filteredUsers = FilterUtils.applyGenderFilter(filteredUsers, filter.getValues());
			break;
		}
		return filteredUsers;
	}

}