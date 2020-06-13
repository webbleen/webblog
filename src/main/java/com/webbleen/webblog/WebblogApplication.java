package com.webbleen.webblog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：webbleen
 * @date ：Created in 2020-06-13 10:54
 * @description：
 */

@SpringBootApplication
public class WebblogApplication {

    private static Logger logger = LoggerFactory.getLogger(WebblogApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebblogApplication.class, args);
        logger.info("Spring Boot启动成功!!!!!!!!!!!!!!!!!!!!");
    }

}
