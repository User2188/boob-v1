package com.example.boobuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BoobUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoobUserApplication.class, args);
    }

}
