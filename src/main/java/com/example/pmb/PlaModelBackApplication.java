package com.example.pmb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PlaModelBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaModelBackApplication.class, args);
    }

}
