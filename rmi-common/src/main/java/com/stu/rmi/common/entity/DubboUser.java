package com.stu.rmi.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author chenxiangwu
 * @title: DubboUser
 * @projectName my-rmi
 * @description: TODO
 * @date 2020/8/20 16:39
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class DubboUser implements Serializable {
    private static final long serialVersionUID = 7349811030707904673L;
    private String name;
    private Integer age;

}
