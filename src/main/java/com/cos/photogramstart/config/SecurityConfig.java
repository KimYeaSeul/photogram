package com.cos.photogramstart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity // 해당 파일로 시큐리티를 활성화 
@Configuration // IoC
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 어떤 요청이던지 간에 구분하지 않겠다.
		http.csrf().disable();
		// Security Setting
		// 삭제 시 기존 시큐리티가 가지고 있는 기능이 다 비활성화 됨.
		// super.configure(http); // <- 이 코드가 로그인 페이지 가로채가고 있음. 
		http.authorizeRequests()
			.antMatchers("/","/user/**","/image/**","/subscribe/**","/coment/**").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin().loginPage("/auth/signin")
			.defaultSuccessUrl("/");
	}
	
	

}
