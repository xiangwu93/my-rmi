package com.stu.rmi.common.chat.domain;

import com.stu.rmi.common.chat.enumeration.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenxiangwu
 * @title: MessageHeader
 * @projectName my-rmi
 * @description: TODO
 * @date 2020/8/26 11:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageHeader {
    private String sender;
    private String receiver;
    private MessageType type;
    private Long timestamp;

}
