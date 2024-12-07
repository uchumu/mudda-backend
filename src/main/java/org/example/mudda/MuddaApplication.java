package org.example.mudda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MuddaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuddaApplication.class, args);
    }

}
