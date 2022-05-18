package ru.vitaliy.petrov.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories(basePackages = "ru.vitaliy.petrov.server.repositories")
@EntityScan(basePackages = "ru.vitaliy.petrov.server.models")
public class SpeedupOfRegistrationServer2022Application {

    public static void main(String[] args) {
        SpringApplication.run(SpeedupOfRegistrationServer2022Application.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
