package com.kfr.admin.Presentation.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kfr.admin.Presentation.user.converter.UserConverter;
import com.kfr.admin.Presentation.user.model.UserRequestDTO;
import com.kfr.admin.Presentation.user.model.UserResponseDTO;
import com.kfr.admin.domain.user.entity.UserEntity;
import com.kfr.admin.domain.user.entity.enums.UserRole;
import com.kfr.admin.domain.user.service.UserService;
import com.kfr.admin.filter.jwt.JWTUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserConverter userConverter;

    @MockBean
    private JWTUtil jwtUtil;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Create User API Test")
    void createUserTest() throws Exception {
        UserRequestDTO userRequestDto = UserRequestDTO.builder()
                .userName("신아록")
                .userAge(30)
                .userEmail("infran@email.com")
                .userPassword("jjredic")
                .companyName("kfr")
                .build();

        var user1 = UserEntity.builder()
                .id(1L)
                .userAge(10)
                .userEmail("infran@email.com")
                .userPassword("jjredic")
                .companyName("kfr")
                .userName("신아록")
                .role(UserRole.USER)
                .build();
        when(userConverter.requestToEntity(userRequestDto)).thenReturn(user1);
        when(userService.userSave(user1)).thenReturn(user1);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Get User by ID API Test")
    void getUserTest() throws Exception {
        Long userId = 1L;
        var user1 = UserEntity.builder()
                .id(1L)
                .userAge(10)
                .userEmail("infran@email.com")
                .userPassword("jjredic")
                .companyName("kfr")
                .userName("신아록")
                .role(UserRole.USER)
                .build();

        UserResponseDTO userResponseDto = UserResponseDTO.builder()
                .id(1L) // ID 값을 설정
                .userName("신아록")
                .userAge(30)
                .userEmail("infran@email.com")
                .companyName("kfr")
                .build();

        String token = "Bearer " + jwtUtil.createJwt("access",user1.getUserName(), user1.getRole().name(), 1000L * 60 * 60); // 1시간 유효

        when(userService.getUserById(userId)).thenReturn(user1);
        when(userConverter.EntityToResponse(user1)).thenReturn(userResponseDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{userId}", userId)
                .header("Authorization", token)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userId))
                .andDo(print());
    }
}