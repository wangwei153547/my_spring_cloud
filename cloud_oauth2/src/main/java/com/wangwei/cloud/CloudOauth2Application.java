package com.wangwei.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.wangwei.cloud.mapper")
public class CloudOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudOauth2Application.class, args);
    }

}
