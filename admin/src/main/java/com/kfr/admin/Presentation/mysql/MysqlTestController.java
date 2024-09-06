package com.kfr.admin.Presentation.mysql;

import com.kfr.admin.domain.mysql.entity.MysqlTestEntity;
import com.kfr.admin.domain.mysql.service.MysqlTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/mysql")
public class MysqlTestController {

    private final MysqlTestService mysqlService;

    @GetMapping("/{mysqlId}")
    public ResponseEntity<MysqlTestEntity> getUser(@PathVariable(name = "mysqlId") Long userId) {
        var mysql =  mysqlService.getMysql(userId);
        return new ResponseEntity<>(mysql, HttpStatusCode.valueOf(200));
    }

}
