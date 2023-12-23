package com.pirtip.pirtipserver.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pirtip.pirtipserver.common.BusinessException;
import com.pirtip.pirtipserver.common.ErrorCode;
import com.pirtip.pirtipserver.entity.UserAccount;
import com.pirtip.pirtipserver.model.SignUpRequest;
import com.pirtip.pirtipserver.model.UserDto;
import com.pirtip.pirtipserver.repository.UserAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserAccountRepository userAccountRepository;

	private final PasswordEncoder passwordEncoder;

	@Transactional
	public UserDto signUp(SignUpRequest request) {
		userAccountRepository.findByUsername(request.getUsername())
			.ifPresent(ua -> {
				throw new BusinessException(ErrorCode.DUPLICATE_ERROR, "이미 존재하는 계정 이름입니다. 다른 이름을 사용해주세요.");
			});
		userAccountRepository.findByNickname(request.getNickname())
			.ifPresent(ua -> {
				throw new BusinessException(ErrorCode.DUPLICATE_ERROR, "이미 존재하는 닉네임입니다. 다른 닉네임을 사용해주세요.");
			});

		UserAccount user = signUpRequest2UserAccount(request);
		userAccountRepository.save(user);
		return UserDto.fromUserAccount(user);
	}

	private UserAccount signUpRequest2UserAccount(SignUpRequest request) {
		return UserAccount.builder()
			.username(request.getUsername())
			.password(passwordEncoder.encode(request.getPassword()))
			.nickname(request.getNickname())
			.build();
	}
}
