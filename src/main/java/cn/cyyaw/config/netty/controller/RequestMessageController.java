package cn.cyyaw.config.netty.controller;


import cn.cyyaw.config.netty.entity.MessageEntity;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 接收消息处理
 */
@Slf4j
@Component
public class RequestMessageController {


    /**
     * 处理发过来的消息
     * @param ctx
     * @param messageEntity
     */
    public void handleMessage(ChannelHandlerContext ctx, MessageEntity messageEntity) {
        Channel channel = ctx.channel();

    }
}
