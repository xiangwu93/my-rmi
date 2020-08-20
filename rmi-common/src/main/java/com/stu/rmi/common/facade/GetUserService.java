package com.stu.rmi.common.facade;

import com.stu.rmi.common.entity.DubboUser;

import java.util.List;

/**
 * @author chenxiangwu
 * @title: GetUserService
 * @projectName my-rmi
 * @description: TODO
 * @date 2020/8/20 16:49
 */
public interface GetUserService {
    List<DubboUser> getUserList(String name);
}
