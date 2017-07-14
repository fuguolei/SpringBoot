package com.igalaxy.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by fuguolei on 2017/7/8.
 */
@SpringBootApplication
public class IGalaxyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(IGalaxyApplication.class, args);
    }

}