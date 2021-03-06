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
    /**
     * 消息类型 {0:文字,1:图片,2:视频}
     */
    private Integer type;
    /**
     * 用户类型{0:普通用户,1:管理员}
     */
    private Integer usertype;
}
