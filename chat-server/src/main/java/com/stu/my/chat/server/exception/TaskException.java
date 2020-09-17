package com.stu.my.chat.server.exception;


import com.stu.rmi.common.chat.domain.Task;
import lombok.Data;

/**
 * Created by SinjinSong on 2017/5/24.
 */
@Data
public class TaskException extends RuntimeException{
    private static final long serialVersionUID = 5900606558887296536L;
    private Task info;
    public TaskException(Task info){
        super(info.getDesc()+"任务执行失败");
        this.info = info;
    }
}
