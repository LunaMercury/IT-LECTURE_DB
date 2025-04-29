package com.example.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {		
 
		// 권한 체크
		http.authorizeRequests().anyRequest().authenticated(); // 어떠한 리퀘스트이던 모두 인증이 필요
		
		// 로그인
		http.formLogin().permitAll();
		
		// 로그아웃
		http.logout().permitAll();
		
		// 예외처리
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 테스트용으로 임시로 메모리 상에 유저정보를 저장헤서 로그인 시도 
		auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("USER"); // {noop}:암호화 하지 않겠다
	}
	
}
