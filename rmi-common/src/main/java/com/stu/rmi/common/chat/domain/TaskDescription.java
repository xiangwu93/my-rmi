package com.stu.rmi.common.chat.domain;


import com.stu.rmi.common.chat.enumeration.TaskType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDescription {
    private TaskType type;
    private String desc;
}
