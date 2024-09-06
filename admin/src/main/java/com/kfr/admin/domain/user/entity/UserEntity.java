package com.kfr.admin.domain.user.entity;

import com.kfr.admin.domain.common.BaseEntity;
import com.kfr.admin.domain.user.entity.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tc_adminUserMaster", catalog = "WEBDB",schema = "dbo")
//@Table(name = "user_table")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true)
    private  Long id;

    @Column(name = "user_name",nullable = true)
    private String userName;

    @Column(name = "user_age", nullable = true)
    private int userAge;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "company_name",nullable = true)
    private String companyName;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role",nullable = false)
    private UserRole role;



    public void encodeUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public UserRole checkRole(String userEmail){
        if(userEmail.equals("admin@kfr.com")){
            return UserRole.ADMIN;
        }
        return UserRole.USER;
    }

    public void setUserRole(UserRole userRole){
        this.role = userRole;
    }

}
