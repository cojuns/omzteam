package com.class302.omzteam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class OmzteamApplication {

    public static void main(String[] args) {
        SpringApplication.run(OmzteamApplication.class, args);
    }

}
