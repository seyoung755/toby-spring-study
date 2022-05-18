package com.toby.suntory;

import com.toby.suntory.user.dao.UserDao;
import com.toby.suntory.user.service.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SuntoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuntoryApplication.class, args);
    }
}
