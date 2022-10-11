package com.appsdeveloperblog.ws.api.albumresourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AlbumresourceserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlbumresourceserverApplication.class, args);
    }

}
