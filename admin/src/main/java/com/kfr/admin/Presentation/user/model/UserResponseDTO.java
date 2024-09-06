package com.kfr.admin.Presentation.user.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private Long id;

    private String userName;

    private int userAge;

    private String userEmail;

    private String companyName;
}
