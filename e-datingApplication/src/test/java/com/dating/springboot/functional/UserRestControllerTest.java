package com.dating.springboot.functional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dating.springboot.dto.UserDto;
import com.dating.springboot.service.UserService;
import com.dating.springboot.testutils.MasterData;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRestControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserService userService;

	
	@Test
	public void testRegisterUser() throws Exception {
		 String uri = "/users/register-user";
		UserDto userDto = MasterData.getUserDto();
		UserDto savedUserDto = MasterData.getUserDto();
		savedUserDto.setUserId(1L);

		when(this.userService.registerUser(userDto)).thenReturn(savedUserDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).content(MasterData.asJsonString(userDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedUserDto)));

	}
	 @Test
	   public void getUsersList() throws Exception {
	      String uri = "/users/get/all";
	      List<UserDto> users = MasterData.getUserDtoList();

			when(this.userService.findAll()).thenReturn(users);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON);

			MvcResult result = mvc.perform(requestBuilder).andReturn();
	      assertTrue(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(users)));
	   }
	 
	 @Test
		public void testDeleteUser() throws Exception {
		 String uri = "/users/delete/1";	
		 UserDto userDto = MasterData.getUserDto();
	        userDto.setUserId(1L);
			when(this.userService.deleteUser(1L)).thenReturn(true);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(uri)
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

			MvcResult result = mvc.perform(requestBuilder).andReturn();
		      assertTrue(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(true)));

		}
	 
	 @Test
		public void testUpdateUser() throws Exception {
		 String uri = "/users/update-user";
		 	UserDto userDto = MasterData.getUserDto();
			UserDto savedUserDto = MasterData.getUserDto();
			savedUserDto.setUserId(1L);

			when(this.userService.updateUser(userDto)).thenReturn(savedUserDto);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.put(uri).content(MasterData.asJsonString(userDto))
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertTrue(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedUserDto)));

		}
	 
	 
}
