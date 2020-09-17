package com.stu.my.chat.server.handler.message.impl;

import com.stu.my.chat.server.handler.message.MessageHandler;
import com.stu.rmi.common.chat.domain.Message;
import com.stu.rmi.common.chat.domain.Response;
import com.stu.rmi.common.chat.domain.ResponseHeader;
import com.stu.rmi.common.chat.domain.Task;
import com.stu.rmi.common.chat.enumeration.ResponseType;
import com.stu.rmi.common.chat.util.ProtoStuffUtil;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by SinjinSong on 2017/5/23.
 */
@Component("MessageHandler.broadcast")
public class BroadcastMessageHandler extends MessageHandler {
    @Override
    public void handle(Message message, Selector server, SelectionKey client, BlockingQueue<Task> queue, AtomicInteger onlineUsers) {
        try {
            byte[] response = ProtoStuffUtil.serialize(
                    new Response(
                            ResponseHeader.builder()
                                    .type(ResponseType.NORMAL)
                                    .sender(message.getHeader().getSender())
                                    .timestamp(message.getHeader().getTimestamp()).build(),
                                    message.getBody()));
            super.broadcast(response,server);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
