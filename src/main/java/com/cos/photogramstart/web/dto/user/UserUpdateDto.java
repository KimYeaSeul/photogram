package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    private String website;
    private String bio;
    private String phone;
    private String gender;

    // 불필요한 데이터가 있기 때문에 위험한 방법, 코드 수정이 필요.
    public User toEntity(){
        return User.builder()
                .name(name) // 이름도 validation 체크 필요.
                .password(password) // 패스워드를 기재 안했다면 DB에 공백이 들어감. Validation 체크 필요.
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
