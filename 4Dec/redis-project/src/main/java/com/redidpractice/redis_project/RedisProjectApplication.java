package com.redidpractice.redis_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisProjectApplication.class, args);
    }

}
