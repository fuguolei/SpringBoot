package com.igalaxy.boot.conf;

import com.igalaxy.boot.exception.CustomErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fuguolei on 2017/7/14.
 */
@Configuration
public class ErrorConfiguration {
    @Bean
    public CustomErrorAttributes errorAttributes() {
        return new CustomErrorAttributes();
    }
}