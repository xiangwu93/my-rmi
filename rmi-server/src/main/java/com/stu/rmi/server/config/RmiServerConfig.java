package com.stu.rmi.server.config;

import com.stu.rmi.common.facade.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * @author chenxiangwu
 * @title: RmiServerConfig
 * @projectName my-rmi
 * @description: rmi 服务端
 * @date 2020/8/19 18:58
 */
@Configuration
public class RmiServerConfig {

    @Bean
    public RmiServiceExporter registerServer(UserFacade userFacade) {
        RmiServiceExporter rmiServiceExporter = null;
        try {
            rmiServiceExporter = new RmiServiceExporter();
            rmiServiceExporter.setServiceName("UserInfo1");
            rmiServiceExporter.setService(userFacade);
            rmiServiceExporter.setServiceInterface(UserFacade.class);
            rmiServiceExporter.setServicePort(1105);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rmiServiceExporter;
    }
}
