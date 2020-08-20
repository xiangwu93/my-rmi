package com.stu.hessian.client.Controller;

import com.stu.rmi.common.facade.HessianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenxiangwu
 * @title: TestController
 * @projectName my-rmi
 * @description: TODO
 * @date 2020/8/20 11:41
 */
@RestController
public class TestController {
    @Autowired
    private HessianService hessianService;
    @RequestMapping("/test")
    public String test(){
        return hessianService.sayHello("this is a test.");
    }
}
