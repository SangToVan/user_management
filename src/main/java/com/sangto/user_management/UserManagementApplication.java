package com.sangto.user_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserManagementApplication {

    public static void main(String[] args) {

        SpringApplication.run(UserManagementApplication.class, args);
        System.out.println("http://localhost:8080/swagger-ui/index.html");
    }

}
