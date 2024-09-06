package com.kfr.admin.Presentation.user.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {

    private String userName;

    private int userAge;

    private String userPassword;

    private String userEmail;

    private String companyName;
}
