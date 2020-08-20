package com.stu.hessian.server;

import com.stu.rmi.common.facade.HessianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.remoting.caucho.HessianServiceExporter;

@SpringBootApplication
public class HessianServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(HessianServerApplication.class, args);
        System.out.println("================================");
        System.out.println("======= Hessian服务端启动成功! =======");
        System.out.println("================================");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HessianServerApplication.class);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
        c.setIgnoreUnresolvablePlaceholders(true);
        return c;
    }

}
