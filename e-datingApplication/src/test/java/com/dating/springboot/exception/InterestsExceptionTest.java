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

import com.dating.springboot.dto.InterestsDto;
import com.dating.springboot.exceptions.InterestsNotFoundException;
import com.dating.springboot.model.exception.ExceptionResponse;
import com.dating.springboot.service.InterestsService;
import com.dating.springboot.testutils.MasterData;

@SpringBootTest
@AutoConfigureMockMvc
public class InterestsExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InterestsService interestsService;
	
	@Test
	public void testCreateInterestsInvalidDataException() throws Exception {
		String uri="/interests/add";
		InterestsDto interestsDto = MasterData.getInterestsDto();
		InterestsDto savedInterestsDto = MasterData.getInterestsDto();
		savedInterestsDto.setUserId(1L);

		interestsDto.setLikes("Ab");

		when(this.interestsService.createInterest(interestsDto)).thenReturn(savedInterestsDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)
				.content(MasterData.asJsonString(interestsDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertTrue(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value());

	}

	@Test
	public void testInterestUpdateInvalidDataException() throws Exception {
		String uri="/interests/update";
		InterestsDto interestsDto = MasterData.getInterestsDto();
		InterestsDto savedInterestsDto = MasterData.getInterestsDto();
		savedInterestsDto.setUserId(1L);

		interestsDto.setLikes("Ab");

		when(this.interestsService.updateInterest(interestsDto)).thenReturn(savedInterestsDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(uri)
				.content(MasterData.asJsonString(interestsDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value());

	}

	@Test
	public void testDeleteInterestInterestNotFoundException() throws Exception {
		String uri="/interests/delete/2";
		ExceptionResponse exResponse = new ExceptionResponse("Interest with id 2 does not exists!",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());

		when(this.interestsService.deleteInterest(2L))
				.thenThrow(new InterestsNotFoundException("Interest with id 2 does not exists!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(uri)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains(exResponse.getMessage()));

	}
}
