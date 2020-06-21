package cn.cyyaw.config.netty.controller;

import cn.cyyaw.config.netty.config.ChannelData;
import cn.cyyaw.config.netty.controller.MessageController;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("================handlerRemoved 聊天被移除===========" + ctx.channel().id().asLongText());
    }


    /**
     * 出现异常
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("================ exceptionCaught:出现异常===========" + ctx.channel().id().asLongText());
        ctx.close();
    }


    /**
     * 用户聊天 ====》注册
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("================ exceptionCaught:用户聊天注册===========" + ctx.channel().id().asLongText());
        ChannelData.channelGroup.add(ctx.channel());
    }

}
