package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Transactional
    public User update(int id, User user){
        // 1. 영속화
        // get() = 무조건 찾았다.
        // orElseThrow() 못찾았으니 Exception 발동
        User userEntity = userRepository.findById(id).orElseThrow(new Supplier<CustomValidationApiException>() {
            @Override
            public CustomValidationApiException get() {
                return new CustomValidationApiException("찾을 수 없는 id 입니다.");
            }
        });

        // 2. 영속화된 오브젝트를 수정 - 더티체킹(업데이트 완료)
        userEntity.setName(user.getName());
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        userEntity.setPassword(encPassword);
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());
        // 더티체킹이 일어나서 업데이트가 완료됨 - 영속성 컨텍스트에 있는 데이터와 DB 에 있는 데이터를 비교하여 자동으로 DB에 넣어줌.

        return userEntity;
    }
}
