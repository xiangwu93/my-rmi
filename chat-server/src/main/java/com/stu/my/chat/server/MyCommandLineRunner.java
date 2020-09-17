package com.stu.my.chat.server;

import org.springframework.boot.CommandLineRunner;

import java.util.Scanner;

/**
 * @author chenxiangwu
 * @title: MyCommandLineRunner
 * @projectName Chat
 * @description: TODO
 * @date 2020/8/26 17:00
 */
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ChatServer chatServer = new ChatServer();
        chatServer.launch();
        Scanner scanner = new Scanner(System.in, "UTF-8");
        while (scanner.hasNext()) {
            String next = scanner.next();
            if (next.equalsIgnoreCase("QUIT")) {
                System.out.println("服务器准备关闭");
                chatServer.shutdownServer();
                System.out.println("服务器已关闭");
            }
        }
    }
}
