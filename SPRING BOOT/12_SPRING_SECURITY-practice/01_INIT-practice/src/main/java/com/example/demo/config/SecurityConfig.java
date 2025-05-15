package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        // CSRF 비활성화
//		http.csrf().disable(); // +CSRF토큰값 확인X, GET 로그아웃 처리 가능
        http.csrf((config) -> {
            config.disable();
        });

        // CSRF토큰 쿠키형태로 전달
//		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        // 권한 체크
        http.authorizeHttpRequests((auth) -> {
            auth.requestMatchers("/", "/join", "/login").permitAll(); // 모두 허용)
            auth.requestMatchers("/user").hasAnyRole("USER", "MANAGER", "ADMIN");
            auth.requestMatchers("/manager").hasRole("MANAGER");
            auth.requestMatchers("/admin").hasRole("ADMIN");
            auth.anyRequest().authenticated();
        });


        // 로그인
        http.formLogin((login) -> {
            login.permitAll();
//            login.loginPage("/login");
//            login.successHandler(new CustomLoginSuccessHandler()); // 인증 성공시
//            login.failureHandler(new CustomLoginFailureHandler()); // 인증 실패시
        });


        // 로그아웃
        http.logout((logout) -> {
            logout.permitAll();
//            logout.addLogoutHandler(new CustomLogoutHandler()); // 로그아웃 실행
//            logoutSuccessHandler(new CustomLogoutSuccessHandler()); //로그아웃 성공시
        });


        // 예외처리
        http.exceptionHandling((exception) -> {
//            exception.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
//            exception.accessDeniedHandler(new CustomAccessDeniedHandler());
        });

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}