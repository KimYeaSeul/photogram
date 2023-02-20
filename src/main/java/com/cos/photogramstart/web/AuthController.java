package com.cos.photogramstart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AuthController {

	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	private final AuthService authService;
	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	@PostMapping("/auth/signup")
	public String signup(SignupDto signupDto) { // x-www-form-urlencoded 방식 key=value
		log.info("siguupDto = {}", signupDto);
		// User <- SignupDto
		User user = signupDto.toEntity();
		log.info(user.toString());
		User userEntity = authService.join(user);
		log.info(userEntity.toString());
		return "auth/signin";
	}
}
