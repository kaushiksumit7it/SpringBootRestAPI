package com.dating.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dating.springboot.dto.InterestsDto;
import com.dating.springboot.dto.UserDto;
import com.dating.springboot.exceptions.InvalidDataException;
import com.dating.springboot.service.InterestsService;

@CrossOrigin
@RestController
@RequestMapping("/interests")
public class InterestsRestController {

	@Autowired
	private InterestsService interestsService;

	@PostMapping("/add")
	public ResponseEntity<?> addInterests(@Valid @RequestBody InterestsDto interestsDto, BindingResult result) {
		if (result.hasErrors()) {
			List<FieldError> fe = result.getFieldErrors();
			throw new InvalidDataException(result.getFieldErrorCount()+" error(s)! Invalid Interests Data at " + fe.toString());
		}
		InterestsDto savedintererstsDto = interestsService.createInterest(interestsDto);
		return new ResponseEntity<InterestsDto>(savedintererstsDto, HttpStatus.OK);

	}

	@PutMapping("/update")
	public ResponseEntity<?> updateInterests(@Valid @RequestBody InterestsDto interestsDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("Invalid Interest Data");
		}
		InterestsDto updatedintererstsDto = interestsService.updateInterest(interestsDto);
		return new ResponseEntity<InterestsDto>(updatedintererstsDto, HttpStatus.OK);

	}

	@DeleteMapping("delete/{interestId}")
	public ResponseEntity<?> deleteInterests(@PathVariable Long interestId) {
		interestsService.deleteInterest(interestId);
		return new ResponseEntity<>(true, HttpStatus.OK);

	}

	@GetMapping("get/{interestId}")
	public ResponseEntity<InterestsDto> getInterestsById(@PathVariable Long interestId) {
		InterestsDto interestDto = interestsService.getById(interestId);

		return ResponseEntity.ok(interestDto);
	}

	@GetMapping("get/by-user-id/{userId}")
	public ResponseEntity<List<InterestsDto>> getInterestsByUserId(@PathVariable Long userId) {
		List<InterestsDto> interestDto = interestsService.getInterestsByUserId(userId);

		return ResponseEntity.ok(interestDto);
	}

	
	@GetMapping("/get/all")
	public ResponseEntity<?> getInterests() {

		List<InterestsDto> list = interestsService.findAll();
		if (list.size() != 0) {
			return new ResponseEntity<List<InterestsDto>>(list, HttpStatus.OK);

		} else {
			return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
		}
	}
}
