package com.stu.dubbo.provider.service.impl;

import com.stu.rmi.common.entity.DubboUser;
import com.stu.rmi.common.facade.GetUserService;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenxiangwu
 * @title: GetUserServiceImpl
 * @projectName my-rmi
 * @description: TODO
 * @date 2020/8/20 16:50
 */
@Service
public class GetUserServiceImpl implements GetUserService {
    @Override
    public List<DubboUser> getUserList(String name) {
        System.out.println("This is input name: " + name);
        List<DubboUser> list = new ArrayList<>();
        list.add(new DubboUser("xiaoming",22));
        list.add(new DubboUser("zhangsan",26));
        list.add(new DubboUser("lisi",34));
        list.add(new DubboUser("laowang",27));
        return list;
    }
}
