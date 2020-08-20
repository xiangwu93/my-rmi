package com.stu.rmi.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chenxiangwu
 */
@SpringBootApplication
public class RmiClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RmiClientApplication.class, args);
        System.out.println("================================");
        System.out.println("======= RMI客户端启动成功! =======");
        System.out.println("================================");
    }

}
