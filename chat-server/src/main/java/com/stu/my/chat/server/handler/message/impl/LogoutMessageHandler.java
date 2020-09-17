package com.stu.my.chat.server.handler.message.impl;


import com.stu.my.chat.server.handler.message.MessageHandler;
import com.stu.my.chat.server.property.PromptMsgProperty;
import com.stu.my.chat.server.user.UserManager;
import com.stu.rmi.common.chat.domain.Message;
import com.stu.rmi.common.chat.domain.Response;
import com.stu.rmi.common.chat.domain.ResponseHeader;
import com.stu.rmi.common.chat.domain.Task;
import com.stu.rmi.common.chat.enumeration.ResponseCode;
import com.stu.rmi.common.chat.enumeration.ResponseType;
import com.stu.rmi.common.chat.util.ProtoStuffUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by SinjinSong on 2017/5/23.
 */
@Component("MessageHandler.logout")
@Slf4j
public class LogoutMessageHandler extends MessageHandler {
    @Autowired
    private UserManager userManager;

    @Override
    public void handle(Message message, Selector server, SelectionKey client, BlockingQueue<Task> queue, AtomicInteger onlineUsers) {
        try {
            SocketChannel clientChannel = (SocketChannel) client.channel();
            userManager.logout(clientChannel);
            byte[] response = ProtoStuffUtil.serialize(
                    new Response(ResponseHeader.builder()
                            .type(ResponseType.PROMPT)
                            .responseCode(ResponseCode.LOGOUT_SUCCESS.getCode())
                            .sender(message.getHeader().getSender())
                            .timestamp(message.getHeader().getTimestamp()).build(),
                            PromptMsgProperty.LOGOUT_SUCCESS.getBytes(PromptMsgProperty.charset)));
            clientChannel.write(ByteBuffer.wrap(response));
            onlineUsers.decrementAndGet();
            //下线广播
            byte[] logoutBroadcast = ProtoStuffUtil.serialize(
                    new Response(
                            ResponseHeader.builder()
                                    .type(ResponseType.NORMAL)
                                    .sender(SYSTEM_SENDER)
                                    .timestamp(message.getHeader().getTimestamp()).build(),
                            String.format(PromptMsgProperty.LOGOUT_BROADCAST, message.getHeader().getSender()).getBytes(PromptMsgProperty.charset)));
            super.broadcast(logoutBroadcast, server);
            log.info("客户端退出");
            //必须要cancel，否则无法从keys从去除该客户端
            client.cancel();
            clientChannel.close();
            clientChannel.socket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
