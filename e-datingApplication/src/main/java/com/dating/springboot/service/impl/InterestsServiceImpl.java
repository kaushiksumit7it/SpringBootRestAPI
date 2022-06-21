package com.dating.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dating.springboot.dto.InterestsDto;
import com.dating.springboot.dto.UserDto;
import com.dating.springboot.entity.Interests;
import com.dating.springboot.entity.User;
import com.dating.springboot.exceptions.InterestsNotFoundException;
import com.dating.springboot.exceptions.UserNotFoundException;
import com.dating.springboot.repository.InterestsRepository;
import com.dating.springboot.service.InterestsService;
import com.dating.springboot.service.UserService;

@Service
public class InterestsServiceImpl implements InterestsService {

	@Autowired
	private InterestsRepository interestsRepository;

	@Autowired
	private UserService userService;
	@Override
	public InterestsDto createInterest(InterestsDto interestsDto) {
		Interests interests = new Interests();
		UserDto userdto= userService.getById(interestsDto.getUserId());
		if(userdto!=null)
		{	
		BeanUtils.copyProperties(interestsDto, interests);
		Interests savedinterests= interestsRepository.save(interests);
		InterestsDto savedinterestdto = new InterestsDto();
		BeanUtils.copyProperties(savedinterests, savedinterestdto);
		return savedinterestdto;
		}
		else {
			throw new UserNotFoundException("Valid user with Id "+interestsDto.getUserId()+" does not exists!");
		}
	}

	@Override
	public InterestsDto updateInterest(InterestsDto interestsDto) {

		Interests interests = new Interests();
		BeanUtils.copyProperties(interestsDto, interests);
		Interests savedinterests= interestsRepository.save(interests);
		InterestsDto savedinterestdto = new InterestsDto();
		BeanUtils.copyProperties(savedinterests, savedinterestdto);
		return savedinterestdto;
	}

	@Override
	public boolean deleteInterest(Long interestId) {
		InterestsDto interestsDto = getById(interestId);
		Interests interests = new Interests();
		BeanUtils.copyProperties(interestsDto, interests);
		interestsRepository.delete(interests);
		return true;
	}

	@Override
	public InterestsDto getById(Long interestId) {
		Optional<Interests> interests = interestsRepository.findById(interestId);
		if (interests.isPresent()) {
			InterestsDto interestsDto = new InterestsDto();
			BeanUtils.copyProperties(interests.get(), interestsDto);
			return interestsDto;
		} else {
			throw new InterestsNotFoundException("Interest with id " + interestId + " does not exists!");
		}

	}

	@Override
	public List<InterestsDto> getInterestsByUserId(Long userId) {
		List<Interests> interests = interestsRepository.findAll();
		List<InterestsDto> interestsDto = new ArrayList<>();
		UserDto userdto= userService.getById(userId);
		if(userdto!=null)
		{
		if(interests.size()!=0)
		{
		for (Interests interest : interests) {
			if(interest.getUserId()== userId)
			{	
			InterestsDto interestDto = new InterestsDto();
			BeanUtils.copyProperties(interest, interestDto);
			interestsDto.add(interestDto);
			}
		}
		}
		else
		{
			throw new InterestsNotFoundException("No data avaiable for Interests!");
		}
		}
		else
		{
			throw new UserNotFoundException("User with id " + userId + " does not exists!");
		}
		
		return interestsDto;
	}


	@Override
	public List<InterestsDto> findAll() {
		List<Interests> interests = interestsRepository.findAll();
		List<InterestsDto> interestsDto = new ArrayList<>();
		for (Interests interest : interests) {
			InterestsDto interestDto = new InterestsDto();
			BeanUtils.copyProperties(interest, interestDto);
			interestsDto.add(interestDto);
		}
		return interestsDto;
	}

}
