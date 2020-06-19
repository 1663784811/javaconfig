package cn.cyyaw.config.netty;


import cn.cyyaw.common.util.StringUtilWHY;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 消息处理
 */
@Slf4j
@Component
public class MessageController {

    /**
     * 登录组
     */
    private static ChannelGroup loginChannelGroup = new DefaultChannelGroup("login", GlobalEventExecutor.INSTANCE);
    /**
     * 登录组
     */
    private static ChannelGroup adminLoginChannelGroup = new DefaultChannelGroup("adminLogin", GlobalEventExecutor.INSTANCE);

    /**
     * 聊天组
     */
    private static ChannelGroup chatChannelGroup = new DefaultChannelGroup("chat", GlobalEventExecutor.INSTANCE);

    /**
     * 客服组
     */
    private static ChannelGroup serverChannelGroup = new DefaultChannelGroup("server", GlobalEventExecutor.INSTANCE);


    /**
     * 消息集中处理
     *
     * @param ctx
     * @param msg
     */
    public void messageHandle(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws IOException {
        log.info("接收到消息：" + msg.text());
        String text = msg.text();
        if (StringUtilWHY.isEmpty(text)) return;
        MessageEntity messageEntity = string2Object(text);
        Channel channel = ctx.channel();
        //=== 请求
        if (messageEntity.getRequestType() != null) {
            switch (messageEntity.getRequestType()) {
                case 0:  //心跳
                    ctx.writeAndFlush(new TextWebSocketFrame("{\"responseType\":0,\"message\":\"pong\"}"));
                    break;
                case 1:  //消息
                    sendMessage(ctx, messageEntity.getFrom(), messageEntity.getTo(), messageEntity.getMessage(), messageEntity.getResponseType());
                    break;
                case 2:  //接听

                    break;
                case 3:  //广播


                    break;
                case 4:  //注册


                    break;
                case 5:  //登录
                    loginChannelGroup.add(channel);
                    ctx.writeAndFlush(new TextWebSocketFrame("{\"responseType\":5,\"message\":\"" + channel.id().asLongText() + "\"}"));
                    break;

                case 6:  //扫码成功
                    scanSuccess(ctx, messageEntity.getFrom(), messageEntity.getTo());
                    break;
                case 7:  //点击登录
                    confirmLogin(ctx, messageEntity.getFrom(), messageEntity.getTo());
                    break;

                case 8:  //点击取消
                    cancelLogin(ctx, messageEntity.getFrom(), messageEntity.getTo());
                    break;


                case 105:  //后台登录
                    adminLoginChannelGroup.add(channel);
                    ctx.writeAndFlush(new TextWebSocketFrame("{\"responseType\":105,\"message\":\"" + channel.id().asLongText() + "\"}"));
                    break;

                case 106:  //后台登录
                    adminScanSuccess(ctx, messageEntity.getFrom(), messageEntity.getTo());
                    break;
                case 107:  //点击登录
                    adminConfirmLogin(ctx, messageEntity.getFrom(), messageEntity.getTo());
                    break;
                case 108:  //点击取消
                    adminCancelLogin(ctx, messageEntity.getFrom(), messageEntity.getTo());
                    break;
            }

        }


        //==== 响应
        if (messageEntity.getResponseType() != null) {
            switch (messageEntity.getRequestType()) {
                case 1:  //消息


                    break;
                case 2:  //接听


                    break;
                case 3:  //广播


                    break;
                case 4:  //注册


                    break;
                case 5:  //登录

                    break;
            }
        }
    }


    /**
     * 解释json字符串
     */
    private MessageEntity string2Object(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        MessageEntity messageEntity = mapper.readValue(json, MessageEntity.class);
        return messageEntity;
    }


    /**
     * 扫码成功
     */
    private void scanSuccess(ChannelHandlerContext ctx, String from, String to) {
        //TODO 验证 from  是否登录 from-> 微信登录token
        //TODO 没登录-->  ctx.writeAndFlush( new TextWebSocketFrame("{\"responseType\":6,\"message\":\"您还没登录\"}"))   return ;
        //TODO 登录--> 则向下
        for (Channel channel : loginChannelGroup) {
            String asLongText = channel.id().asLongText();
            if (asLongText.equals(to)) {
                channel.writeAndFlush(new TextWebSocketFrame("{\"responseType\":6,\"message\":\"扫码成功\"}"));
            }
        }
    }

    /**
     * 扫码成功
     */
    private void adminScanSuccess(ChannelHandlerContext ctx, String from, String to) {
        //TODO 验证 from  是否登录 from-> 微信登录token
        //TODO 没登录-->  ctx.writeAndFlush( new TextWebSocketFrame("{\"responseType\":6,\"message\":\"您还没登录\"}"))   return ;
        //TODO 登录--> 则向下
        for (Channel channel : adminLoginChannelGroup) {
            String asLongText = channel.id().asLongText();
            if (asLongText.equals(to)) {
                channel.writeAndFlush(new TextWebSocketFrame("{\"responseType\":6,\"message\":\"扫码成功\"}"));
            }
        }
    }

    /**
     * 点登录
     *
     * @param ctx
     * @param from
     * @param to
     */
    private void confirmLogin(ChannelHandlerContext ctx, String from, String to) {
        //TODO 验证 from  是否登录 from-> 微信登录token
        //TODO 没登录-->  ctx.writeAndFlush( new TextWebSocketFrame("{\"responseType\":6,\"message\":\"您还没登录\"}"))   return ;
        //TODO 登录--> 则向下

        for (Channel channel : loginChannelGroup) {
            String asLongText = channel.id().asLongText();
            if (asLongText.equals(to)) {
                channel.writeAndFlush(new TextWebSocketFrame("{\"responseType\":7,\"message\":\"" + from + "\"}"));
                ctx.writeAndFlush(new TextWebSocketFrame("{\"responseType\":7,\"message\":\"登录成功\"}"));
                return;
            }
        }
        ctx.writeAndFlush(new TextWebSocketFrame("{\"responseType\":500,\"message\":\"登录失败\"}"));
    }

    /**
     * 点登录
     *
     * @param ctx
     * @param from
     * @param to
     */
    private void adminConfirmLogin(ChannelHandlerContext ctx, String from, String to) {
        //TODO 验证 from  是否登录 from-> 微信登录token
        //TODO 没登录-->  ctx.writeAndFlush( new TextWebSocketFrame("{\"responseType\":6,\"message\":\"您还没登录\"}"))   return ;
        //TODO 登录--> 则向下
        for (Channel channel : adminLoginChannelGroup) {
            String asLongText = channel.id().asLongText();
            if (asLongText.equals(to)) {
                channel.writeAndFlush(new TextWebSocketFrame("{\"responseType\":107,\"message\":\"" + from + "\"}"));
                ctx.writeAndFlush(new TextWebSocketFrame("{\"responseType\":107,\"message\":\"登录成功\"}"));
                return;
            }
        }
        ctx.writeAndFlush(new TextWebSocketFrame("{\"responseType\":500,\"message\":\"登录失败\"}"));
    }

    /**
     * 取消登录
     *
     * @param ctx
     * @param from
     * @param to
     */
    private void cancelLogin(ChannelHandlerContext ctx, String from, String to) {
        for (Channel channel : loginChannelGroup) {
            String asLongText = channel.id().asLongText();
            if (asLongText.equals(to)) {
                channel.writeAndFlush(new TextWebSocketFrame("{\"responseType\":8,\"message\":\"取消登录\"}"));
            }
        }
        ctx.writeAndFlush(new TextWebSocketFrame("{\"responseType\":8,\"message\":\"取消登录\"}"));
    }

    /**
     * 取消登录
     *
     * @param ctx
     * @param from
     * @param to
     */
    private void adminCancelLogin(ChannelHandlerContext ctx, String from, String to) {
        for (Channel channel : adminLoginChannelGroup) {
            String asLongText = channel.id().asLongText();
            if (asLongText.equals(to)) {
                channel.writeAndFlush(new TextWebSocketFrame("{\"responseType\":8,\"message\":\"取消登录\"}"));
            }
        }
        ctx.writeAndFlush(new TextWebSocketFrame("{\"responseType\":8,\"message\":\"取消登录\"}"));
    }

    /**
     * 发送消息
     *
     * @param ctx
     * @param from
     * @param to
     * @param message
     * @param messageType
     */
    private void sendMessage(ChannelHandlerContext ctx, String from, String to, String message, Integer messageType) {

    }


}
