package com.kfr.admin.infrastructure.user.mybatis;

import com.kfr.admin.domain.user.entity.UserEntity;
import com.kfr.admin.domain.user.service.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryMybatisImpl implements UserRepository {


    private final UserMybatisRepository userMybatisRepository;
    public UserEntity save(UserEntity user){
        return null;
    }

    public UserEntity findByUserId(Long userId){
        return  userMybatisRepository.findUserById(userId);
    }

    @Override
    public UserEntity findUserByUserEmail(String email) {
        return userMybatisRepository.findUserByEmail(email);
    }

}
