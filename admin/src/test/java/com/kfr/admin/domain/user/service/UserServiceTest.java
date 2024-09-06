package com.kfr.admin.domain.user.service;

import com.kfr.admin.domain.user.entity.UserEntity;
import com.kfr.admin.domain.user.entity.enums.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;


import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    @Test
    @DisplayName("user1 객체를 userService 를 통해 저장했을 때 저장이 잘 되는 지 확인하는 테스트")
    void userSave() {
        //given
        var user1 = UserEntity.builder()
                .id(1L)
                .userAge(10)
                .userEmail("infran@email.com")
                .userPassword("jjredic")
                .companyName("kfr")
                .userName("신아록")
                .role(UserRole.USER)
                .build();

        //when
        given(userRepository.save(user1)).willReturn(user1);


         //then
        var result = userService.userSave(user1);
        assertThat(result.getUserName()).isEqualTo(user1.getUserName());
    }

    @Test
    @DisplayName("user1 객체를 userService 를 통해 UserId가 1L인 user 가 잘 조회 되는 지 확인하는 테스트")
    void getUserById() {

        var user1 = UserEntity.builder()
                .id(1L)
                .userAge(10)
                .userEmail("infran@email.com")
                .userPassword("jjredic")
                .companyName("kfr")
                .userName("신아록")
                .role(UserRole.USER)
                .build();

        given(userRepository.findByUserId(1L)).willReturn(user1);

        var result = userService.getUserById(1L);
        assertThat(result.getUserName()).isEqualTo(user1.getUserName());

    }

    @Test
    @DisplayName("user1 객체를 userService 를 통해 UserId가 1L인 user 가 잘 저장 되는 지 확인하는 테스트")
    void getUserByEmail() {

        var user1 = UserEntity.builder()
                .id(1L)
                .userAge(10)
                .userEmail("infran@email.com")
                .userPassword("jjredic")
                .companyName("kfr")
                .userName("신아록")
                .role(UserRole.USER)
                .build();

        given(userRepository.findUserByUserEmail("infran@email.com")).willReturn(user1);

        var result = userService.getUserByEmail("infran@email.com");
        assertThat(result.getUserName()).isEqualTo(user1.getUserName());

    }
}