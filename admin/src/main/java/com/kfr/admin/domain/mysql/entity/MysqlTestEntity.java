package com.kfr.admin.domain.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="mysql")
//@Table(name = "user_table")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MysqlTestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
