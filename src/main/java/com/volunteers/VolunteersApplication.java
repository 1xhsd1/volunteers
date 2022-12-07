package com.volunteers;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.volunteers.dao")
public class VolunteersApplication {

    public static void main(String[] args) {
        SpringApplication.run(VolunteersApplication.class, args);
    }

}
