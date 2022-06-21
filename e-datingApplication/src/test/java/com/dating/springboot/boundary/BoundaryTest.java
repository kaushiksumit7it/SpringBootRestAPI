package com.dating.springboot.boundary;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.dating.springboot.dto.InterestsDto;
import com.dating.springboot.dto.MatchDto;
import com.dating.springboot.dto.UserDto;
import com.dating.springboot.testutils.MasterData;

@SpringBootTest
@AutoConfigureMockMvc
public class BoundaryTest {

	private static Validator validator;
	
	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	
	@Test
	public void testUserNameNotNull() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setUsername("");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testUserNameMinThree() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setUsername("Ab");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testUserNameMaxHundred() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		String name = "";
		for (int i = 0; i < 120; i++) {
			name.concat("A");
		}
		userDto.setUsername(name);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}
	
	@Test
	public void testUserAgeNotNull() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setAge(null);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testUserAgeMinEighteen() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setAge(10);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testUserAgeMaxFortyFive() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setAge(65);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testUserMobileNotNull() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setPhoneNumber(null);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testUserMobileMinTen() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setPhoneNumber(12345L);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testUserMobileMaxTen() throws Exception {
		UserDto userDto = MasterData.getUserDto();

		userDto.setPhoneNumber(123456789012L);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testUserGenderNotNull() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setGender("");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testUserEmailNotNull() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setEmail("");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testUserEmailValidFormat() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setEmail("abc");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testUserCityNotNull() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setCity("");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testUserCountryNotNull() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		userDto.setCountry("");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		assertTrue(!violations.isEmpty());
	}
	
	@Test
	public void testInterestsLikesInNotNull() throws Exception {
		InterestsDto interestsDto = MasterData.getInterestsDto();
		interestsDto.setLikes("");
		Set<ConstraintViolation<InterestsDto>> violations = validator.validate(interestsDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testInterestsLikesInMinThree() throws Exception {
		InterestsDto interestsDto = MasterData.getInterestsDto();
		interestsDto.setLikes("Ab");
		Set<ConstraintViolation<InterestsDto>> violations = validator.validate(interestsDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testInterestsLikesInMaxHunderds() throws Exception {
		InterestsDto interestsDto = MasterData.getInterestsDto();
		String interestedIn = "";
		for (int i = 0; i < 120; i++) {
			interestedIn.concat("A");
		}
		interestsDto.setLikes(interestedIn);
		Set<ConstraintViolation<InterestsDto>> violations = validator.validate(interestsDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testInterestsDislikesInNotNull() throws Exception {
		InterestsDto interestsDto = MasterData.getInterestsDto();
		interestsDto.setDislikes("");
		Set<ConstraintViolation<InterestsDto>> violations = validator.validate(interestsDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testInterestsDislikesInMinThree() throws Exception {
		InterestsDto interestsDto = MasterData.getInterestsDto();
		interestsDto.setDislikes("Ab");
		Set<ConstraintViolation<InterestsDto>> violations = validator.validate(interestsDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testInterestsDislikesInMaxHunderds() throws Exception {
		InterestsDto interestsDto = MasterData.getInterestsDto();
		String notInterestedIn = "";
		for (int i = 0; i < 120; i++) {
			notInterestedIn.concat("A");
		}
		interestsDto.setDislikes(notInterestedIn);
		Set<ConstraintViolation<InterestsDto>> violations = validator.validate(interestsDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testInterestsProfileUrlNotNull() throws Exception {
		InterestsDto interestsDto = MasterData.getInterestsDto();
		interestsDto.setProfileUrl(null);
		Set<ConstraintViolation<InterestsDto>> violations = validator.validate(interestsDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testInterestsAboutNotNull() throws Exception {
		InterestsDto interestsDto = MasterData.getInterestsDto();
		interestsDto.setAbout(null);
		Set<ConstraintViolation<InterestsDto>> violations = validator.validate(interestsDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testInterestsAboutMinThree() throws Exception {
		InterestsDto interestsDto = MasterData.getInterestsDto();
		interestsDto.setAbout("Ab");
		Set<ConstraintViolation<InterestsDto>> violations = validator.validate(interestsDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testInterestsAboutMaxHunderd() throws Exception {
		InterestsDto interestsDto = MasterData.getInterestsDto();
		String about = "";
		for (int i = 0; i < 120; i++) {
			about.concat("A");
		}
		interestsDto.setAbout(about);
		Set<ConstraintViolation<InterestsDto>> violations = validator.validate(interestsDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testMatchUserIdNotNull() throws Exception {
		MatchDto matchDto = MasterData.getMatchDto();
		matchDto.setUserId(null);
		Set<ConstraintViolation<MatchDto>> violations = validator.validate(matchDto);
		assertTrue(!violations.isEmpty());
	}

	@Test
	public void testMatchMatchedUserIdNotNull() throws Exception {
		MatchDto matchDto = MasterData.getMatchDto();
		matchDto.setMatchedUserId(null);
		Set<ConstraintViolation<MatchDto>> violations = validator.validate(matchDto);
		assertTrue(!violations.isEmpty());
	}

}
