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

import com.dating.springboot.dto.InterestsDto;
import com.dating.springboot.service.InterestsService;
import com.dating.springboot.testutils.MasterData;

@SpringBootTest
@AutoConfigureMockMvc
public class InterestsRestControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InterestsService interestsService;
	
	@Test
	public void testCreateInterest() throws Exception {
		String uri="/interests/add";
		InterestsDto interestsDto = MasterData.getInterestsDto();
		InterestsDto savedInterestsDto = MasterData.getInterestsDto();
		savedInterestsDto.setInterestId(1L);

		when(this.interestsService.createInterest(interestsDto)).thenReturn(savedInterestsDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)
				.content(MasterData.asJsonString(interestsDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedInterestsDto)));

	}

	@Test
	public void testDeleteInterest() throws Exception {
		String uri="/interests/delete/1";
		InterestsDto interestsDto = MasterData.getInterestsDto();
		interestsDto.setInterestId(1L);
		when(this.interestsService.deleteInterest(1L)).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(uri)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(MasterData.asJsonString(interestsDto));
		System.out.println(result.getResponse().getContentAsString());
		assertTrue(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(true)));

	}
	
	@Test
	public void testUpdateInterest() throws Exception {
		String uri="/interests/update";
		InterestsDto interestsDto = MasterData.getInterestsDto();
		InterestsDto savedInterestsDto = MasterData.getInterestsDto();
		savedInterestsDto.setInterestId(1L);

		when(this.interestsService.updateInterest(interestsDto)).thenReturn(savedInterestsDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(uri)
				.content(MasterData.asJsonString(interestsDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedInterestsDto)));

	}
	
	@Test
	public void testGetInterestsByInterestId() throws Exception {
		String uri="/interests/get/1";
		InterestsDto interestsDto = MasterData.getInterestsDto();

		when(this.interestsService.getById(1L)).thenReturn(interestsDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(interestsDto)));

	}
	
	@Test
	public void testGetAllInterestsByUserId() throws Exception {
		String uri="/interests/get/by-user-id/1";
		List<InterestsDto> interestsDtos = MasterData.getInterestsDtoList();

		when(this.interestsService.getInterestsByUserId(1L)).thenReturn(interestsDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(interestsDtos)));

	}

}
