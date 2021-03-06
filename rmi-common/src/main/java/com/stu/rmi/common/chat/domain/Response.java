package com.stu.rmi.common.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private ResponseHeader header;
    private byte[] body;
}
