package com.kfr.admin.domain.user.service;

import com.kfr.admin.domain.user.entity.UserEntity;

public interface UserRepository {

    UserEntity save(UserEntity user);

    UserEntity findByUserId(Long userId);

    UserEntity findUserByUserEmail(String email);
}
