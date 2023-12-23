package com.pirtip.pirtipserver.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pirtip.pirtipserver.model.SignUpRequest;
import com.pirtip.pirtipserver.model.UserDto;
import com.pirtip.pirtipserver.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequest request) {
		UserDto userDto = userService.signUp(request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(userDto);
	}
}
