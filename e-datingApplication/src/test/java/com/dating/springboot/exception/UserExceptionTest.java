package com.dating.springboot.exception;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dating.springboot.dto.UserDto;
import com.dating.springboot.exceptions.UserNotFoundException;
import com.dating.springboot.model.exception.ExceptionResponse;
import com.dating.springboot.service.UserService;
import com.dating.springboot.testutils.MasterData;

@SpringBootTest
@AutoConfigureMockMvc
public class UserExceptionTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;

		
	@Test
	public void testRegisterUserInvalidDataException() throws Exception {
		String uri = "/users/register-user";
		UserDto userDto = MasterData.getUserDto();
		UserDto savedUserDto = MasterData.getUserDto();
		savedUserDto.setUserId(1L);

		userDto.setName("ab");

		when(this.userService.registerUser(userDto)).thenReturn(savedUserDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).content(MasterData.asJsonString(userDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertTrue(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value());

	}

	@Test
	public void testUpdateUserInvalidDataException() throws Exception {
		String uri = "/users/update-user";
		UserDto userDto = MasterData.getUserDto();
		UserDto savedUserDto = MasterData.getUserDto();
		savedUserDto.setUserId(1L);

		userDto.setName("ab");
		when(this.userService.updateUser(userDto)).thenReturn(savedUserDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(uri).content(MasterData.asJsonString(userDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value());

	}

	@Test
	public void testDeleteUserNotFoundException() throws Exception {
		String uri = "/users/delete/2";
		ExceptionResponse exResponse = new ExceptionResponse("User with id 2 does not exists!", System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value());

		when(this.userService.deleteUser(2L)).thenThrow(new UserNotFoundException("User with id 2 does not exists!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(uri)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains(exResponse.getMessage()));

	}	 
	 
}
