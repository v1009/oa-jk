package com.ht.oa.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.TimeZone;

@SpringBootApplication
@MapperScan("com.ht.oa.manage.dao")
@EnableTransactionManagement
public class ManageApplication {

    public static void main(String[] args) {
        //设置时区
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        SpringApplication.run(ManageApplication.class, args);
    }

}
