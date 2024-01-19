package com.pirtip.pirtipserver.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pirtip.pirtipserver.common.BusinessException;
import com.pirtip.pirtipserver.common.ErrorCode;
import com.pirtip.pirtipserver.entity.UserAccount;
import com.pirtip.pirtipserver.repository.UserAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService {

	private final UserAccountRepository userAccountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount user = userAccountRepository.findByUsername(username)
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

		return User.builder()
			.username(user.getUsername())
			.password(user.getPassword())
			.roles("USER")
			.build();
	}
}
