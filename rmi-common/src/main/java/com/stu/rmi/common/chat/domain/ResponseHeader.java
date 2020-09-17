package com.stu.rmi.common.chat.domain;


import com.stu.rmi.common.chat.enumeration.ResponseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseHeader {
    private String sender;
    private ResponseType type;
    private Integer responseCode;
    private Long timestamp;
}
