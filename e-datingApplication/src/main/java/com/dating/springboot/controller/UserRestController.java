package com.dating.springboot.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dating.springboot.dto.UserDto;
import com.dating.springboot.exceptions.InvalidDataException;
import com.dating.springboot.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	private UserService userService;

	@GetMapping("/get/all")
	public ResponseEntity<?> getUsers() {

		List<UserDto> list = userService.findAll();
		if (list.size() != 0) {
			return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);

		} else {
			return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/get/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable Long userId) {

		UserDto userDto = userService.getById(userId);

		return ResponseEntity.ok(userDto);

	}

	@PostMapping("/register-user")
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserDto userDto, BindingResult result) {

		if (result.hasErrors()) {
			List<FieldError> fe = result.getFieldErrors();
			throw new InvalidDataException(result.getFieldErrorCount()+" error(s)! Invalid User Data at " + fe.toString());
		}
		UserDto savedUserDto=userService.registerUser(userDto);
		return ResponseEntity.ok(savedUserDto);

	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Long userId) {

		userService.deleteUser(userId);
		return new ResponseEntity<>(true, HttpStatus.OK);

	}

	@PutMapping("/update-user")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDto, BindingResult result) {

		if (result.hasErrors()) {
			throw new InvalidDataException("Invalid User Data");
		}
		UserDto savedUserDto=userService.updateUser(userDto);
		return ResponseEntity.ok(savedUserDto);

	}
}
