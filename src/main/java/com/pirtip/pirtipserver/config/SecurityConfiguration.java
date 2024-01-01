package com.pirtip.pirtipserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.formLogin().disable()
			.httpBasic().disable();

		http.authorizeRequests()
			.antMatchers("/api/user/signup").anonymous()
			.antMatchers("/swagger-ui.html/**", "/swagger-ui/**", "/v3/api-docs/**").anonymous()
			.anyRequest().authenticated();

		return http.build();
	}
}
