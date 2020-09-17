package com.stu.my.chat.server.handler.message;


import com.stu.rmi.common.chat.domain.Message;
import com.stu.rmi.common.chat.domain.Task;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by SinjinSong on 2017/5/23.
 */
public abstract class MessageHandler {
    
    public static final String SYSTEM_SENDER = "系统提示";
    abstract public void handle(Message message, Selector server, SelectionKey client,
                                BlockingQueue<Task> queue, AtomicInteger onlineUsers) throws InterruptedException;
    
    protected void broadcast(byte[] data, Selector server) throws IOException {
        for (SelectionKey selectionKey : server.keys()) {
            Channel channel = selectionKey.channel();
            if (channel instanceof SocketChannel) {
                SocketChannel dest = (SocketChannel) channel;
                if (dest.isConnected()) {
                    dest.write(ByteBuffer.wrap(data));
                }
            }
        }
    }
}
