package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 해당 파일로 시큐리티를 활성화 
@Configuration // IoC
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public BCryptPasswordEncoder encode(){
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 어떤 요청이던지 간에 구분하지 않겠다.
		http.csrf().disable();
		// Security Setting
		// 삭제 시 기존 시큐리티가 가지고 있는 기능이 다 비활성화 됨.
		// super.configure(http); // <- 이 코드가 로그인 페이지 가로채가고 있음. 
		http.authorizeRequests()
			.antMatchers("/","/user/**","/image/**","/subscribe/**","/comment/**","/api/**").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin().loginPage("/auth/signin") // GET -> 인증이 안된 사용자가 보는 페이지
			.loginProcessingUrl("/auth/signin") // POST -> 로그인 요청시 보여지는 페이지, 스프링 시큐리티가 로그인 프로세스 진행
			.defaultSuccessUrl("/");
	}
	
	

}
