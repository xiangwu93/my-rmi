package com.stu.rmi.common.chat.enumeration;

/**
 * @author chenxiangwu
 * @title: MessageType
 * @projectName my-rmi
 * @description: TODO
 * @date 2020/8/26 13:41
 */
public enum MessageType {
    LOGIN(1, "登录"),
    LOGOUT(1, "登录"),
    NORMAL(1, "登录"),
    BROADCAST(1, "登录"),
    TASK(1, "登录");
    private int code;
    private String desc;

    MessageType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
