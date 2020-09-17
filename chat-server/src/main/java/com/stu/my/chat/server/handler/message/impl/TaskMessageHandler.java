package com.stu.my.chat.server.handler.message.impl;

import com.stu.my.chat.server.handler.message.MessageHandler;
import com.stu.rmi.common.chat.domain.Message;
import com.stu.rmi.common.chat.domain.Task;
import com.stu.rmi.common.chat.domain.TaskDescription;
import com.stu.rmi.common.chat.util.ProtoStuffUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by SinjinSong on 2017/5/23.
 * 生产者
 * 注意所有的InterruptedException，要么抛给上层，要么自己处理
 */
@Component("MessageHandler.task")
@Slf4j
public class TaskMessageHandler extends MessageHandler {
    
    @Override
    public void handle(Message message, Selector server, SelectionKey client, BlockingQueue<Task> queue, AtomicInteger onlineUsers) throws InterruptedException {
        TaskDescription taskDescription = ProtoStuffUtil.deserialize(message.getBody(), TaskDescription.class);
        Task task = new Task((SocketChannel) client.channel(), taskDescription.getType(), taskDescription.getDesc(), message);
        try {
            queue.put(task);
            log.info("{}已放入阻塞队列",task.getReceiver().getRemoteAddress());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
