package com.apress.AWS.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apress.AWS.dto.UserDTO;
import com.apress.AWS.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class UserRegistrationController {

	@Autowired
	private UserService userService;

	// URI - /api/users
	@GetMapping(value = "users")
	public ResponseEntity<List<UserDTO>> istAllUsers() {
		final Iterable<UserDTO> UsersIterable = this.userService.listAll();
		final List<UserDTO> users = StreamSupport.stream(UsersIterable.spliterator(), false)
				.collect(Collectors.toList());
		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}

	// URI - /api/user/id/1
	@GetMapping("user/id/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") final Long userId) {
		try {
			final UserDTO user = this.userService.get(userId);
			return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
	}

	// URI - /api/user/save
	@PostMapping(value = "user/save")
	public ResponseEntity<UserDTO> save(@RequestBody UserDTO user) {
		this.userService.save(user);
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}

	// URI - /api/user/delete/id/1
	@DeleteMapping("user/delete/id/{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable(name = "id") final Long userId) {
		this.userService.delete(userId);
		return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
	}

}
