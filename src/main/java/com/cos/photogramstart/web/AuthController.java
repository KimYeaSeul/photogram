package com.cos.photogramstart.web;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller // 1. IoC 등록 2. 파일을 리턴하는 컨트롤러
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
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { // x-www-form-urlencoded 방식 key=value
		// @Controller 는 String 반환시 파일을 반환.
		// @Controller 이지만 @ResponseBody 가 붙어있으면 데이터를 리턴함.
		if(bindingResult.hasErrors()){
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error : bindingResult.getFieldErrors()){
				errorMap.put(error.getField(), error.getDefaultMessage());
				log.error(error.getDefaultMessage());
			}
			// @ResponseBody String 을 에서 return "유효성 검사 실패" 하면 페이지에 글자만 나오고,
			// String에서 throw new RuntimeException("유효성 검사 실패") 하면 페이지에 에러 페이지와 글자가 나오고
			throw new CustomValidationException("유효성 검사 실패", errorMap);
		} else {

			log.info("siguupDto = {}", signupDto);
			// User <- SignupDto
			User user = signupDto.toEntity();
			log.info(user.toString());
			User userEntity = authService.join(user);
			log.info(userEntity.toString());
			return "auth/signin";
		}
	}
}
