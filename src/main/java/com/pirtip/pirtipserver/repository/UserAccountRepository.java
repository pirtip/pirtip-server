package com.pirtip.pirtipserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pirtip.pirtipserver.entity.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

	Optional<UserAccount> findByUsername(String username);

	Optional<UserAccount> findByNickname(String nickname);
}
