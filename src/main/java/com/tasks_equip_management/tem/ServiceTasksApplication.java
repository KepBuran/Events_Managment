package com.tasks_equip_management.tem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ServiceTasksApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTasksApplication.class, args);
    }

}
