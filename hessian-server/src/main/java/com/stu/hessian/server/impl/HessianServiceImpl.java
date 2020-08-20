package com.stu.hessian.server.impl;

import com.stu.rmi.common.facade.HessianService;
import org.springframework.stereotype.Service;

/**
 * @author chenxiangwu
 * @title: HessianServiceImpl
 * @projectName my-rmi
 * @description: TODO
 * @date 2020/8/20 11:28
 */
@Service
public class HessianServiceImpl implements HessianService {
    @Override
    public String sayHello(String name) {
        return "Hello World!, " + name;
    }
}
