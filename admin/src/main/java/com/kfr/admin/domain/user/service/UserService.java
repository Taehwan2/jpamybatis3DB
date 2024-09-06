package com.kfr.admin.domain.user.service;

import com.kfr.admin.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserEntity userSave(UserEntity user) {
        var encodePassword = passwordEncoder.encode(user.getUserPassword());
        user.encodeUserPassword(encodePassword);
        user.setUserRole(user.checkRole(user.getUserEmail()));
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserEntity getUserById(Long userId) {
        return userRepository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    public UserEntity getUserByEmail(String email) {
        return userRepository.findUserByUserEmail(email);
    }

}
