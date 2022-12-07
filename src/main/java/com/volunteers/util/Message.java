package com.volunteers.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    //0表示成功;-1表示失败
    int status;
    //向前端返回的内容
    String massage;

}

