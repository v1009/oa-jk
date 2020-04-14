package com.ht.oa.jk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.TimeZone;

@SpringBootApplication
@MapperScan("com.ht.oa.jk.dao")
@EnableTransactionManagement
public class JkApplication {

    public static void main(String[] args) {
        //设置时区
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        SpringApplication.run(JkApplication.class, args);
    }

}
