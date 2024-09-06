package com.kfr.admin.infrastructure.user.jpa;

import com.kfr.admin.domain.user.entity.UserEntity;
import com.kfr.admin.domain.user.service.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
@Slf4j
@Primary
public class UserRepositoryJpaImpl implements UserRepository {

   private final UserJpaRepository userJpaRepository;


    public UserEntity save(UserEntity user){
        return userJpaRepository.save(user);
    }

    public UserEntity findByUserId(Long userId){
        return  userJpaRepository.findById(userId).orElseThrow();
    }

    @Override
    public UserEntity findUserByUserEmail(String email) {
        return userJpaRepository.findByUserEmail(email).orElseThrow();
    }
}
