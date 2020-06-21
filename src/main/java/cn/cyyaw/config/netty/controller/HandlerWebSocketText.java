package cn.cyyaw.config.netty.controller;

import cn.cyyaw.config.netty.config.ChannelData;
import cn.cyyaw.config.netty.controller.MessageController;
import cn.cyyaw.config.netty.entity.ChannelObject;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * WebSocket 处理
 */
@Component
@Slf4j
@ChannelHandler.Sharable
public class HandlerWebSocketText extends SimpleChannelInboundHandler<TextWebSocketFrame> {



    @Autowired
    private MessageController messageController;

    /**
     * 消息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        messageController.messageHandle(ctx, msg);
    }

    /**
     * 聊天被移除
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx){
        String lid = ctx.channel().id().asLongText();
        log.info("================handlerRemoved 聊天被移除===========" + lid);
        ChannelData.allChannel.remove(lid);
    }


    /**
     * 出现异常
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        String lid = ctx.channel().id().asLongText();
        log.info("================ exceptionCaught:出现异常===========" + lid);
        ctx.close();
        ChannelData.allChannel.remove(lid);
    }


    /**
     * 用户聊天 ====》注册
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        String lid = ctx.channel().id().asLongText();
        log.info("====exceptionCaught:用户聊天注册====" + lid);
        Map<String, ChannelObject> allChannel = ChannelData.allChannel;
        ChannelObject ch = new ChannelObject();
        ch.setId(lid);
        ch.setChannel(ctx.channel());
        allChannel.put(lid, ch);
    }

}
