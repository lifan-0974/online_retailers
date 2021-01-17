package com.wronggou;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@EnableDubbo
public class SpringseachserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringseachserviceApplication.class, args);
    }

}
