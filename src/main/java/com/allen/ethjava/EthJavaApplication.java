package com.allen.ethjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class EthJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EthJavaApplication.class, args);
    }

}
