package com.kfr.admin.infrastructure.user.mybatis;


import com.kfr.admin.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository

public class UserMybatisRepository {

    @Autowired
     SqlSession session;

    public UserEntity findUserById(Long id) {
        return session.selectOne("test.findUserById", id);
    }

    public UserEntity findUserByEmail(String email) {
        return session.selectOne("test.findUserByEmail", email);
    }
}
