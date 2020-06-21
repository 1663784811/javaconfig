package cn.cyyaw.config.netty.entity;


import io.netty.channel.Channel;
import lombok.Data;

@Data
public class ChannelObject {

    /**
     * 管道
     */
    private Channel channel;

    /**
     * 管理标识
     */
    private String id ;

    /**
     * 数据库标识
     */
    private String tid;

    /**
     * 类型：{1:用户,2:客服}
     */
    private Integer type;

}
