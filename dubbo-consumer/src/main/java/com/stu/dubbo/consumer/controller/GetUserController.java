package com.stu.dubbo.consumer.controller;

import com.stu.rmi.common.entity.DubboUser;
import com.stu.rmi.common.entity.User;
import com.stu.rmi.common.facade.GetUserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chenxiangwu
 * @title: GetUserController
 * @projectName my-rmi
 * @description: TODO
 * @date 2020/8/20 17:18
 */
@RestController
public class GetUserController {
    @Reference
    GetUserService getUserService;

    @RequestMapping
    public List<DubboUser> getUserList(@RequestParam("name")String name){
        return getUserService.getUserList(name);
    }
}
