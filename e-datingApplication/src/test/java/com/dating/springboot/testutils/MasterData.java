package com.dating.springboot.testutils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.dating.springboot.dto.InterestsDto;
import com.dating.springboot.dto.MatchDto;
import com.dating.springboot.dto.UserDto;

public class MasterData {

	public static UserDto getUserDto() {
		UserDto user = new UserDto();
		user.setUserId(1L);
		user.setName("Name");
		user.setUsername("Name1user");
		user.setPassword("Name1pwd");
		user.setEmail("name@mail.com");
		user.setAge(22);
		user.setGender("Male");
		user.setPhoneNumber(1234567890L);
		user.setCity("Mumbai");
		user.setCountry("India");
		return user;

	}

	public static List<UserDto> getUserDtoList() {
		List<UserDto> users = new ArrayList<>();
		UserDto user = new UserDto();
		user.setUserId(1L);
		user.setName("First");
		user.setUsername("Firstuser");
		user.setPassword("Firstpwd");
		user.setEmail("first@mail.com");
		user.setAge(22);
		user.setGender("Male");
		user.setPhoneNumber(1234567890L);
		user.setCity("Mumbai");
		user.setCountry("India");
		users.add(user);
		user = new UserDto();
		user.setUserId(2L);
		user.setName("Second");
		user.setUsername("Seconduser");
		user.setPassword("Secondpwd");
		user.setEmail("second@mail.com");
		user.setAge(23);
		user.setGender("Female");
		user.setPhoneNumber(99994567890L);
		user.setCity("London");
		user.setCountry("England");
		users.add(user);
		return users;
	}

	public static MatchDto getMatchDto() {
		MatchDto matchDto = new MatchDto();
		matchDto.setId(1L);
		matchDto.setUserId(2L);
		matchDto.setMatchedUserId(1L);
		return matchDto;
	}

	public static List<MatchDto> getMatchDtoList() {
		List<MatchDto> matches = new ArrayList<>();
		MatchDto matchDto = new MatchDto();
		matchDto.setId(1L);
		matchDto.setUserId(2L);
		matchDto.setMatchedUserId(1L);
		matches.add(matchDto);
		matchDto = new MatchDto();
		matchDto.setId(2L);
		matchDto.setUserId(2L);
		matchDto.setMatchedUserId(1L);
		matches.add(matchDto);
		return matches;
	}

		public static InterestsDto getInterestsDto() {

		InterestsDto interestDto = new InterestsDto();
		interestDto.setInterestId(1L);
		interestDto.setUserId(2L);
		List<String> hobbies = new ArrayList<>();
		hobbies.add("Playing Cricket");
		hobbies.add("Reading Books");
		hobbies.add("Travelling");
		interestDto.setHobbies(hobbies);
		interestDto.setLikes("To Talk");
		interestDto.setDislikes("Getting up early in the morning");
		interestDto.setProfileUrl("www.abc.com");
		interestDto.setAbout("I am a Simple man");
		return interestDto;
	}

	public static List<InterestsDto> getInterestsDtoList() {
		List<InterestsDto> interests = new ArrayList<>();
		InterestsDto interestDto = new InterestsDto();
		interestDto.setInterestId(1L);
		interestDto.setUserId(2L);
		List<String> hobbies = new ArrayList<>();
		hobbies.add("Playing Cricket");
		hobbies.add("Reading Books");
		hobbies.add("Travelling");
		interestDto.setHobbies(hobbies);
		interestDto.setLikes("To Talk");
		interestDto.setDislikes("Getting up early in the morning");
		interestDto.setProfileUrl("www.abc.com");
		interestDto.setAbout("I am a Simple man");
		interests.add(interestDto);
		interestDto = new InterestsDto();
		interestDto.setInterestId(2L);
		interestDto.setUserId(1L);
		hobbies = new ArrayList<>();
		hobbies.add("Sleeping");
		hobbies.add("Playing Games");
		interestDto.setHobbies(hobbies);
		interestDto.setLikes("Riding the Bikes");
		interestDto.setDislikes("Sleeping early");
		interestDto.setProfileUrl("www.example.com");
		interestDto.setAbout("Have passion for life");
		interests.add(interestDto);
		return interests;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
