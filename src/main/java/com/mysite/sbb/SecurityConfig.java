package com.mysite.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만드는 애너테이션
// 스프링 시큐리티 활성화하는 역할
@EnableMethodSecurity(prePostEnabled = true) // @PreAuthorize 애너테이션을 사용하기 위해 반드시 필요한 설정이다. 
public class SecurityConfig {
	 @Bean
	 SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
	                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
	            .csrf((csrf) -> csrf
	                .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
	            .headers((headers)-> headers.addHeaderWriter(new XFrameOptionsHeaderWriter(
	            		XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
	            .formLogin((formLogin)-> formLogin.loginPage("/user/login")
	            		.defaultSuccessUrl("/"))
	            .logout((logout)-> logout
	            		.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
	            		.logoutSuccessUrl("/")
	            		.invalidateHttpSession(true));
	        
	        return http.build();
	 }
	 
	 @Bean
	 PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
	 
	 @Bean
	 // 스프링 시큐리티의 인증을 처리한다. 
	 AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		 return authenticationConfiguration.getAuthenticationManager();
	 }
}
