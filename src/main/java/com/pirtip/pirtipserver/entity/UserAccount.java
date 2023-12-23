package com.pirtip.pirtipserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long id;

	@Column(name = "account_name", unique = true, length = 30)
	private String username;

	@Column(name = "account_password", length = 255)
	private String password;

	@Column(name = "account_nickname", unique = true, length = 10)
	private String nickname;

	@Builder
	public UserAccount(Long id, String username, String password, String nickname) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
	}
}
