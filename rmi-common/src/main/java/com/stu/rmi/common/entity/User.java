package com.stu.rmi.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * @author chenxiangwu
 * @title: User
 * @projectName my-rmi
 * @description: TODO
 * @date 2020/8/19 17:25
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable,Cloneable {

    private static final long serialVersionUID = -2063787808055518218L;

    private String userName;

    private String userSex;

    private Integer userAge;

}


