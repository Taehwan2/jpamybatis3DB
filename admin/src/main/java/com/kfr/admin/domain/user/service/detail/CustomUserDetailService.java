package com.kfr.admin.domain.user.service.detail;

import com.kfr.admin.domain.user.entity.UserEntity;
import com.kfr.admin.domain.user.entity.detail.CustomUserDetails;
import com.kfr.admin.domain.user.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        UserEntity userData = userRepository.findUserByUserEmail(userEmail);

        if(userData!=null){
            return new CustomUserDetails(userData);
        }

        return null;
    }
}
