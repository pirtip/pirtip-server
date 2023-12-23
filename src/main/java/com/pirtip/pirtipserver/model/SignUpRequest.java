package com.pirtip.pirtipserver.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SignUpRequest {

	private final String username;
	private final String password;
	private final String nickname;
}
