package com.stu.my.chat.server.handler.task.file;

import com.stu.my.chat.server.exception.TaskException;
import com.stu.my.chat.server.handler.BaseTaskHandler;
import com.stu.my.chat.server.task.TaskManagerThread;
import com.stu.rmi.common.chat.domain.MessageHeader;
import com.stu.rmi.common.chat.domain.Response;
import com.stu.rmi.common.chat.domain.ResponseHeader;
import com.stu.rmi.common.chat.enumeration.ResponseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component("BaseTaskHandler.file")
@Scope("prototype")
@Slf4j
public class FileTaskHandler extends BaseTaskHandler {
    
    @Override
    protected Response process() throws IOException {
        MessageHeader header = info.getMessage().getHeader();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.out.println(info);
        if (!manager.copyStream(info.getDesc(), baos)) {
            throw new TaskException(info);
        }
        log.info("下载图片成功");
        byte[] bytes = baos.toByteArray();
        baos.close();
        return new Response(ResponseHeader.builder()
                        .type(ResponseType.FILE)
                        .sender(header.getSender())
                        .timestamp(header.getTimestamp())
                        .build(),
                        bytes);
    }

    @Override
    protected void init(TaskManagerThread parentThread) {
        //不需要其他数据
    }


}
