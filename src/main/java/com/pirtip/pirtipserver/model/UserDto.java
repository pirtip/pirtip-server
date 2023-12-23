package com.pirtip.pirtipserver.model;

import com.pirtip.pirtipserver.entity.UserAccount;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Builder
@Getter
@ToString
public class UserDto {

	private final Long id;
	private final String username;
	private final String nickname;

	public static UserDto fromUserAccount(UserAccount userAccount) {
		return UserDto.builder()
			.id(userAccount.getId())
			.username(userAccount.getUsername())
			.nickname(userAccount.getNickname())
			.build();
	}
}
