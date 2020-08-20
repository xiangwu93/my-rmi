package com.stu.rmi.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chenxiangwu
 */
@SpringBootApplication
public class RmiServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(RmiServerApplication.class, args);
        System.out.println("================================");
        System.out.println("======= 启动RMI服务成功! =======");
        System.out.println("================================");
    }

}
