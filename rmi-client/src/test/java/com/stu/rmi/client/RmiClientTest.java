package com.stu.rmi.client;

import com.stu.rmi.common.entity.User;
import com.stu.rmi.common.facade.UserFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.rmi.RemoteException;

/**
 * @author chenxiangwu
 * @title: RmiClientTest
 * @projectName my-rmi
 * @description: TODO
 * @date 2020/8/20 8:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RmiClientApplication.class)
public class RmiClientTest {
    @Autowired
    private UserFacade userFacade;

    @Test
    public void userByNameTest(){
        try {
            User user = userFacade.getUserByName("ZhenJin");
            System.out.println("=======> " + user + " <=======");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
