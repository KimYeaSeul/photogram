package com.cos.photogramstart.web.dto.auth;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
// 통신할 때 필요한 데이터를 담는 오브젝트
public class SignupDto {
	@Size(min=2, max=20)
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String email;
	@NotBlank
	private String name;
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}
}
