package com.stu.my.chat.server.exception.handler;


import com.stu.my.chat.server.property.PromptMsgProperty;
import com.stu.rmi.common.chat.domain.Message;
import com.stu.rmi.common.chat.domain.Response;
import com.stu.rmi.common.chat.domain.ResponseHeader;
import com.stu.rmi.common.chat.enumeration.ResponseType;
import com.stu.rmi.common.chat.util.ProtoStuffUtil;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by SinjinSong on 2017/5/25.
 */
@Component("interruptedExceptionHandler")
public class InterruptedExceptionHandler {
    
    public void handle(SocketChannel channel, Message message) {
        try {
            byte[] response = ProtoStuffUtil.serialize(
                    new Response(
                            ResponseHeader.builder()
                                    .type(ResponseType.PROMPT)
                                    .sender(message.getHeader().getSender())
                                    .timestamp(message.getHeader().getTimestamp()).build(),
                            PromptMsgProperty.SERVER_ERROR.getBytes(PromptMsgProperty.charset)));
            channel.write(ByteBuffer.wrap(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
