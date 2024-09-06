package com.kfr.admin.domain.mssql7.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "JANGO", catalog = "WEBDB",schema = "dbo")
//@Table(name = "user_table")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mssql7 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
