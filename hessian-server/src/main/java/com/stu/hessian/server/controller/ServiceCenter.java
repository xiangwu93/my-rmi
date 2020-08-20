package com.stu.hessian.server.controller;

import com.stu.rmi.common.facade.HessianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenxiangwu
 * @title: ServiceCenter
 * @projectName my-rmi
 * @description: TODO
 * @date 2020/8/20 11:45
 */
@RestController
public class ServiceCenter {
    @Autowired
    private HessianService hessianService;

    @Bean("/HessianService")
    public HessianServiceExporter accountService(){
        HessianServiceExporter hessianServiceExporter = new HessianServiceExporter();
        hessianServiceExporter.setService(hessianService);
        hessianServiceExporter.setServiceInterface(HessianService.class);
        return  hessianServiceExporter;
    }
}
