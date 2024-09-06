package com.kfr.admin.infrastructure.user.jpa;

import com.kfr.admin.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUserEmail(String email);
}
