package com.kfr.admin.domain.mysql.service;

import com.kfr.admin.domain.mysql.entity.MysqlTestEntity;
import com.kfr.admin.infrastructure.mysql.MysqlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MysqlTestService {

    private final MysqlRepository mysqlRepository;

    public MysqlTestEntity getMysql(Long id) {
        return mysqlRepository.findById(id).orElse(null);
    }

}
