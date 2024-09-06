package com.kfr.admin.infrastructure.mysql;

import com.kfr.admin.domain.mysql.entity.MysqlTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlRepository extends JpaRepository<MysqlTestEntity,Long> {
}
