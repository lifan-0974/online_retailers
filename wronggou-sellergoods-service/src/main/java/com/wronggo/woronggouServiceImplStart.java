package com.wronggo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("com/wronggo/mapper")
public class woronggouServiceImplStart {

    public static void main(String[] args) {
        SpringApplication.run(woronggouServiceImplStart.class, args);
    }

}
