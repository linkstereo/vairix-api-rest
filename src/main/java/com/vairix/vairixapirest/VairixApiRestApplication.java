package com.vairix.vairixapirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.vairix.marveladapter"})
public class VairixApiRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(VairixApiRestApplication.class, args);
    }

}
