package com.zplus.Artist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.TimeZone;
@SpringBootApplication(scanBasePackages = "com.zplus.Artist")
@EnableSwagger2
@EnableScheduling
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);

        builder.headless(false);
        SpringApplication.run(Application.class,args);


    }
}
