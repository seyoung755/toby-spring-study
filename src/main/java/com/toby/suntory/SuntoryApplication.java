package com.toby.suntory;

import com.toby.suntory.user.dao.UserDao;
import com.toby.suntory.user.domain.User;
import com.toby.suntory.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SuntoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuntoryApplication.class, args);
    }

    @Bean
    CommandLineRunner onStartUp(UserService userService, UserDao userDao) {
        return args -> {
            try {
                userService.createUserListWithTrans();
            } catch (Exception e) {

            }

            System.out.println(userDao.getCount());
        };
    }
}
