package com.kfr.admin.Presentation.user.converter;

import com.kfr.admin.Presentation.user.model.UserRequestDTO;
import com.kfr.admin.Presentation.user.model.UserResponseDTO;
import com.kfr.admin.domain.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public  UserEntity requestToEntity(UserRequestDTO userRequestDto){
        return UserEntity.builder()
                .userName(userRequestDto.getUserName())
                .userAge(userRequestDto.getUserAge())
                .userEmail(userRequestDto.getUserEmail())
                .userPassword(userRequestDto.getUserPassword())
                .companyName(userRequestDto.getCompanyName())
                .build();
    }


    public UserResponseDTO EntityToResponse(UserEntity userEntity){
        return UserResponseDTO.builder()
                .id(userEntity.getId())
                .userName(userEntity.getUserName())
                .userAge(userEntity.getUserAge())
                .userEmail(userEntity.getUserEmail())
                .companyName(userEntity.getCompanyName())
                .build();
    }
}
