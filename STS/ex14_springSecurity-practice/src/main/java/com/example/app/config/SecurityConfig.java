package com.example.app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.example.app.config.auth.PrincipalDetailsService;
import com.example.app.config.auth.exceptionHandler.CustomAccessDeniedHandler;
import com.example.app.config.auth.exceptionHandler.CustomAuthenticationEntryPoint;
import com.example.app.config.auth.loginHandler.CustomLoginFailureHandler;
import com.example.app.config.auth.loginHandler.CustomLoginSuccessHandler;
import com.example.app.config.auth.logoutHandler.CustomLogoutHandler;
import com.example.app.config.auth.logoutHandler.CustomLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private PrincipalDetailsService principalDetailsService;
	
	@Autowired
	private DataSource dataSource3;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
 
		// CSRF 비활성화
		http.csrf().disable(); // +CSRF토큰값 확인X, GET 로그아웃 처리 가능
		
		// CSRF토큰 쿠키형태로 전달
//		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		
		// 권한 체크
		http.authorizeRequests()
			.antMatchers("/","/join","/login").permitAll() // 모두 허용
			.antMatchers("/user").hasAnyRole("USER","MANAGER")
			.antMatchers("/manager").hasRole("MANAGER")
			.antMatchers("/admin").hasRole("ADMIN")
			.anyRequest() // 어떠한 리퀘스트이던 모두 인증이 필요
//			.permitAll()
			.authenticated();
		
		// 로그인
		http.formLogin()
			.loginPage("/login")
			.permitAll()
			.successHandler(new CustomLoginSuccessHandler()) // 인증 성공시
			.failureHandler(new CustomLoginFailureHandler()); // 인증 실패시
		
		// 로그아웃
		http.logout()
			.permitAll()
			.addLogoutHandler(new CustomLogoutHandler()) // 로그아웃 실행
			.logoutSuccessHandler(new CustomLogoutSuccessHandler()); //로그아웃 성공시			
		
		// 예외처리
		http.exceptionHandling()
			.authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // 미인증계정 예외처리
			.accessDeniedHandler(new CustomAccessDeniedHandler()); // 권한이 부족한 경우
		
		// REMEMBER-ME
		http.rememberMe()
			.key("rememberMeKey")
			.rememberMeParameter("remember-me") // .jsp의 name태그에 맞춰
			.alwaysRemember(false)			// 항상 체크 상태일지
			.tokenValiditySeconds(60*60)	// 60분
			.tokenRepository(tokenRepository()); 		// 토큰을 저장하고 관리하는 방법을 설정
	}
	
	// 테스트용으로 임시로 메모리 상에 유저정보를 저장헤서 로그인 시도 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user0").password(passwordEncoder.encode("1234")).roles("USER"); // {noop}:암호화 하지 않겠다
		auth.inMemoryAuthentication().withUser("manager0").password(passwordEncoder.encode("1234")).roles("MANAGER");
		auth.inMemoryAuthentication().withUser("admin0").password(passwordEncoder.encode("1234")).roles("ADMIN");
		
		auth.userDetailsService(principalDetailsService).passwordEncoder(passwordEncoder);
	}	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource3);
		return repo;
	}
}