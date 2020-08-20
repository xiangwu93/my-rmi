package com.stu.rmi.server.impl;

import com.stu.rmi.common.entity.User;
import com.stu.rmi.common.facade.UserFacade;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author chenxiangwu
 * @title: UserFacadeImpl
 * @projectName my-rmi
 * @description: RMI服务接口实现
 * @date 2020/8/19 19:03
 */
@Service
public class UserFacadeImpl implements UserFacade {
    private List<User> userList;

    {
        //模拟一个数据库
        User jane = new User("Jane", "女", 16);
        User jack = new User("jack", "男", 17);
        User zhenJin = new User("ZhenJin", "男", 18);
        userList = Arrays.asList(jane, jack, zhenJin);
    }


    @Override
    public User getUserByName(String userName) {
        //根据用户名从数据库取出对应的用户信息
        Supplier<NullPointerException> exception = () -> new NullPointerException("找不到" + userName + ",这个用户的信息!");
        return userList.stream().filter(t -> t.getUserName().equals(userName)).findFirst().orElseThrow(exception);
    }


    @Override
    public List<User> getUserBySex(String userSex) {
        //根据用户名从数据库取出对应的用户信息
        return userList.stream().filter(t -> t.getUserSex().equals(userSex)).collect(Collectors.toList());
    }

}
