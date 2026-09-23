package com.rikkei.b4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude = {
    org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration.class,
    org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration.class
})
@EnableCaching
public class AntiCacheStampedeApplication {
    public static void main(String[] args) {
        SpringApplication.run(AntiCacheStampedeApplication.class, args);
    }
}
