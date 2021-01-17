package com.wronggo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SpringBootApplication(scanBasePackages = "com.wronggo")

public class SpringbootshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootshopApplication.class, args);
    }
   @Bean
    public  static NoOpPasswordEncoder passwordEncoder(){
        return  (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
   }
}
