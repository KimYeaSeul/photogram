package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// JPA - Java Persistence API (자바로 데이터를 영구적으로 저장할 수 있는 API 제공)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // Create Table in DB
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String email;
	private String name;
	private String website; // 웹사이트
	private String bio; // 자기소개
	private String phone;
	private String gender;
	
	private String profileImageUrl; // 사진
	private String role; // 권한
	
	private LocalDateTime createDate; 
	
	@PrePersist // DB에 Insert되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}