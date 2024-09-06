package com.kfr.admin.infrastructure.refresh;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tc_adminUserRefreshToken",catalog = "WEBDB",schema = "dbo")
//@Table(name = "refresh_token")
public class RefreshEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_id")
    private Long id;

    @Column(name = "user_name")
    private String username;
    @Column(name = "refresh")
    private String refresh;

    @Column(name = "expiration")
    private String expiration;


}
