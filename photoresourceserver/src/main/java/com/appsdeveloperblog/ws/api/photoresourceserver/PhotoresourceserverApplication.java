package com.appsdeveloperblog.ws.api.photoresourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class PhotoresourceserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoresourceserverApplication.class, args);
    }

}
