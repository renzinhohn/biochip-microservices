package com.biochip.resource.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {

	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		// Configure Http Security
		// Scope based Access Control
		http.authorizeHttpRequests()
				.antMatchers(HttpMethod.GET, "/users/status/check")
				.hasAuthority("SCOPE_profile")
			.anyRequest().authenticated()
			.and()
			.oauth2ResourceServer()
			.jwt();

		return http.build();
	}
}
