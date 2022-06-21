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

import com.dating.springboot.dto.MatchDto;
import com.dating.springboot.service.MatchService;
import com.dating.springboot.testutils.MasterData;

@SpringBootTest
@AutoConfigureMockMvc
public class MatchRestControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MatchService matchService;

	@Test
	public void testGetAllMatches() throws Exception {
		List<MatchDto> matches = MasterData.getMatchDtoList();

		when(this.matchService.getAllMatches(1L)).thenReturn(matches);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/matches/1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(matches)));

	}

	@Test
	public void testAddMatch() throws Exception {
		String uri="/users/matches/add";
		MatchDto matchDto = MasterData.getMatchDto();
		MatchDto savedMatchDto = MasterData.getMatchDto();
		savedMatchDto.setId(1L);

		when(this.matchService.saveMatch(matchDto)).thenReturn(savedMatchDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)
				.content(MasterData.asJsonString(matchDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedMatchDto)));

	}

}
