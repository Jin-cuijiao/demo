package com.ssm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class demoAppRun {
    public static void main(String[] args){
        SpringApplication.run(demoAppRun.class);
        System.out.println("启动成功");
    }
}
