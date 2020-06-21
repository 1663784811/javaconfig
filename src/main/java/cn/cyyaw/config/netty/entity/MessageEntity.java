package cn.cyyaw.config.netty.entity;

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


    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }

    public void setResponseType(Integer responseType) {
        this.responseType = responseType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public String getMessage() {
        return message;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getTime() {
        return time;
    }

    public Integer getRequestType() {
        return requestType;
    }

    public Integer getResponseType() {
        return responseType;
    }
}
