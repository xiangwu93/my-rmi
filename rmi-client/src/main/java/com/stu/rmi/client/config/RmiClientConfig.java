package com.stu.rmi.client.config;

import com.stu.rmi.common.facade.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.RemoteLookupFailureException;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
 * @author chenxiangwu
 * @title: RmiClientConfig
 * @projectName my-rmi
 * @description: rmi 客户端配置
 * @date 2020/8/19 17:21
 */
@Configuration
public class RmiClientConfig {

    @Bean
    public UserFacade userInfo() {

        RmiProxyFactoryBean rmiProxyFactoryBean = null;
        try {
            rmiProxyFactoryBean = new RmiProxyFactoryBean();
            rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1105/UserInfo1");
            rmiProxyFactoryBean.setServiceInterface(UserFacade.class);
            rmiProxyFactoryBean.afterPropertiesSet();
        }catch (RemoteLookupFailureException e){
            throw new RuntimeException("服务端请求失败", e);
        }

        return (UserFacade) rmiProxyFactoryBean.getObject();
    }
}
