package com.dating.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dating.springboot.dto.InterestsDto;
import com.dating.springboot.dto.MatchDto;
import com.dating.springboot.dto.UserDto;
import com.dating.springboot.exceptions.InvalidDataException;
import com.dating.springboot.filter.Filter;
import com.dating.springboot.service.MatchService;

@RestController
@RequestMapping("/users/matches")
public class MatchRestController {

	@Autowired
	MatchService matchService;

	@PostMapping("/add")
	public ResponseEntity<?> saveMatch(@Valid @RequestBody MatchDto matchDto, BindingResult result) {
		if (result.hasErrors()) {
			List<FieldError> fe = result.getFieldErrors();
			throw new InvalidDataException(result.getFieldErrorCount()+" error(s)! Invalid Match Data at " + fe.toString());
		}
		MatchDto savedMatchDto = matchService.saveMatch(matchDto);
		return ResponseEntity.ok(savedMatchDto);

	}
	@GetMapping("/{userId}")
	public List<MatchDto> getMatches(@PathVariable Long userId) {
		return matchService.getAllMatches(userId);
	}

}