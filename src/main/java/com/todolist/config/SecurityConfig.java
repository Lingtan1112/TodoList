package com.todolist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.todolist.filter.JWTTokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
		return authConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http.csrf().disable().headers().frameOptions().disable().and().authorizeRequests()
				.antMatchers("/","/user/**","/h2-console/**")
				.permitAll()
				.antMatchers("/security/allow").hasRole("ADMIN")
				.antMatchers("/security/notallowed")
				.hasRole("USER").anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.httpBasic(Customizer.withDefaults())
				.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	public JWTTokenFilter jwtTokenFilter() {
		return new JWTTokenFilter();
	}

	
}
