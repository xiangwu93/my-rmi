package com.stu.hessian.client.config;

import com.stu.rmi.common.facade.HessianService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 * @author chenxiangwu
 * @title: HessianClientConfig
 * @projectName my-rmi
 * @description: TODO
 * @date 2020/8/20 11:35
 */
@Configuration
public class HessianClientConfig {
    @Bean
    public HessianProxyFactoryBean helloClient(){
        HessianProxyFactoryBean hessianProxyFactoryBean = new HessianProxyFactoryBean();
        hessianProxyFactoryBean.setServiceUrl("http://localhost:8090/HessianService");
        hessianProxyFactoryBean.setServiceInterface(HessianService.class);

        return hessianProxyFactoryBean;
    }
}
