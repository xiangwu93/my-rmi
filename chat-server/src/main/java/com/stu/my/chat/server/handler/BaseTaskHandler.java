package com.stu.my.chat.server.handler;

import com.stu.my.chat.server.exception.TaskException;
import com.stu.my.chat.server.http.HttpConnectionManager;
import com.stu.my.chat.server.task.TaskManagerThread;
import com.stu.rmi.common.chat.domain.Response;
import com.stu.rmi.common.chat.domain.Task;
import com.stu.rmi.common.chat.util.ProtoStuffUtil;

import java.io.IOException;
import java.nio.ByteBuffer;

public abstract class BaseTaskHandler implements Runnable {
    protected Task info;
    protected HttpConnectionManager manager;


    abstract protected Response process() throws IOException, InterruptedException;

    abstract protected void init(TaskManagerThread parentThread);

    @Override
    public final void run() {
        try {
            info.getReceiver().write(ByteBuffer.wrap(ProtoStuffUtil.serialize(process())));
        } catch (IOException e) {
            e.printStackTrace();
            throw new TaskException(info);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new TaskException(info);
        }
    }

    public void init(Task info, HttpConnectionManager manager, TaskManagerThread parentThread) {
        this.info = info;
        this.manager = manager;
        init(parentThread);
    }

}
