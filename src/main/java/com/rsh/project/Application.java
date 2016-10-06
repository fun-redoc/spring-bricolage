package com.rsh.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;


@SpringBootApplication
//@EnableTransactionManagement
//@ComponentScan(value = {"com.rsh.project", "com.rsh.project.security"})
//@EnableAutoConfiguration
//@Configuration
//@EnableJpaRepositories(basePackages = {"com.rsh.project", "com.rsh.project.security"})
public class Application {

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

//        String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for( String name : beanNames ){
//            System.out.println("BEAN->" + name);
//        }
    }
}
