package com.stu.rmi.common.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenxiangwu
 * @title: Message
 * @projectName my-rmi
 * @description:
 * @date 2020/8/26 11:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private MessageHeader header;
    private byte[] body;
}
