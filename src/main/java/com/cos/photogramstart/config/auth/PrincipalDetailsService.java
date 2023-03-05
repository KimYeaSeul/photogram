package com.cos.photogramstart.config.auth;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {


    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. password 는 시큐리티가 알아서 처리 해준다.
        // 2. 리턴이 잘 되면 자동으로 UserDetails 타입을 세션으로 만든다.
        User userEntity = userRepository.findByUsername(username);

        if(userEntity == null ){
            return null;
        }else{
            return new PrincipalDetails(userEntity);
        }
    }
}
