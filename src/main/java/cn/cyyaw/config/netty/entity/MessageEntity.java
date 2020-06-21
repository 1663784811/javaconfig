package cn.cyyaw.config.netty.entity;

import lombok.Data;

@Data
public class MessageEntity {
    /**
     * 请求类型
     */
    private Integer requestType;
    /**
     * 响应类型
     */
    private Integer responseType;
    /**
     * 消息类型
     */
    private Integer messageType;
    /**
     * 消息内容
     */
    private String message;
    /**
     * 给谁发送消息
     */
    private String to;
    /**
     * 来自认谁的消息
     */
    private String from;
    /**
     * 服务器接收到的时间
     */
    private String time;
}
