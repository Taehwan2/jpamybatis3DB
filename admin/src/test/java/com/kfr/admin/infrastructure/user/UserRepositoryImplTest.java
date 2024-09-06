package com.kfr.admin.infrastructure.user;

import com.kfr.admin.domain.user.entity.UserEntity;
import com.kfr.admin.domain.user.entity.enums.UserRole;
import com.kfr.admin.infrastructure.user.mybatis.UserRepositoryMybatisImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryMybatisImpl userRepository;

    @BeforeEach
    void setUp() {
        var result = UserEntity.builder()
                .id(1L)
                .userAge(10)
                .userEmail("infran@email.com")
                .userPassword("jjredic")
                .companyName("kfr")
                .userName("신아록")
                .role(UserRole.USER)
                .build();

        var result2 = UserEntity.builder()
                .id(2L)
                .userAge(20)
                .userEmail("eemail@email.com")
                .userPassword("jjredic")
                .companyName("kfr")
                .userName("장태환")
                .role(UserRole.USER)
                .build();


        userRepository.save(result);
        userRepository.save(result2);


    }


    @Test
    @DisplayName("userId가 3인 이름이 고예은이라는 User DB에 저장하기")
    void save() {
        var result = UserEntity.builder()
                .id(3L)
                .userAge(20)
                .userEmail("eewmail@email.com")
                .userPassword("jjredic")
                .companyName("kfr")
                .userName("고예은")
                .role(UserRole.USER)
                .build();

        userRepository.save(result);

    }

    @Test
    @DisplayName("userId가 1인 User 객체 가져오기")
    void findByUserId() {
        var result = userRepository.findByUserId(1L);

        assertThat(result.getUserName()).isEqualTo("신아록");
        //assertThat(result.getUserName()).isEqualTo("장태환");
    }

    @Test
    void findUserByUserEmail() {
        var result = userRepository.findUserByUserEmail("eemail@email.com");
        assertThat(result.getUserName()).isEqualTo("장태환");
    }
}