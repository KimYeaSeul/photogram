package com.cos.photogramstart.web.dto.auth;

import lombok.Data;

@Data
// 통신할 때 필요한 데이터를 담는 오브젝트
public class SignupDto {
	private String username;
	private String password;
	private String email;
	private String name;
}
