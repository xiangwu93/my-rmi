package com.stu.rmi.common.facade;

import com.stu.rmi.common.entity.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author chenxiangwu
 * @title: UserFacade
 * @projectName my-rmi
 * @description: rmi 服务接口
 * @date 2020/8/19 17:34
 */
public interface UserFacade extends Remote {

    User getUserByName(String name) throws RemoteException;

    List<User> getUserBySex(String sex) throws RemoteException;
}
