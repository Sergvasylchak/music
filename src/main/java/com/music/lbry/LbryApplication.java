package com.music.lbry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.music.lbry.repository")
@ComponentScan("com.music.lbry")
public class LbryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LbryApplication.class, args);
    }
}
