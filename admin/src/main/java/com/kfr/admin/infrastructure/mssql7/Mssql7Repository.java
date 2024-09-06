package com.kfr.admin.infrastructure.mssql7;

import com.kfr.admin.domain.mssql7.entity.Mssql7;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Mssql7Repository extends JpaRepository<Mssql7,Long> {
}
