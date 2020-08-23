package cn.cyyaw.config.netty.controller;


import cn.cyyaw.common.util.StringUtilWHY;
import cn.cyyaw.config.netty.config.ChannelData;
import cn.cyyaw.config.netty.entity.MessageEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 消息处理
 */
@Slf4j
@Component
public class MessageController {

    @Autowired
    private ChatLoginController chatLoginController;

    @Autowired
    private RequestMessageController requestMessageController;

    @Autowired
    private ChatGroupController chatGroupController;

    @Autowired
    private ChatUserController chatUserController;


    /**
     * 消息集中处理
     *
     * @param ctx
     * @param msg
     */
    public void messageHandle(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        String text = msg.text();
        if (StringUtilWHY.isEmpty(text)) return;
        MessageEntity messageEntity = string2Object(text);
        Channel channel = ctx.channel();
        //=== 请求
        if (messageEntity.getRequestType() != null) {
            HashMap<String, Object> map = new HashMap<>();
            if(messageEntity.getRequestType() != 0){
                log.info("接收到消息：" + messageEntity.toString());
            }
            switch (messageEntity.getRequestType()) {
                case 0:  //心跳
                    map.put("responseType",0);
                    map.put("message","pong");
                    ctx.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(map)));
                    break;
                case 1:  //消息
                    requestMessageController.handleMessage(ctx, messageEntity);
                    break;
                case 2:  //接听
                    requestMessageController.answerMessage(ctx, messageEntity);
                    break;
                case 3:  //广播
                    break;
                case 100:  // 临时聊天注册
                    chatLoginController.linkServer(channel, messageEntity);
                    break;
                case 500:  //登录返回登录信息
                    chatLoginController.loginFn(channel, messageEntity);
                    break;
                case 600:  //获取好友信息
                    chatUserController.getUserInfo(channel, messageEntity);
                    break;
                case 700:  //获取群信息
                    chatGroupController.getGroupInfo(channel, messageEntity);
                    break;
                case 701:  //发送群消息
                    chatGroupController.sendGroupMessage(channel, messageEntity);
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
                    ChannelData.adminLoginChannelGroup.add(channel);
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
    }

    /**
     * 登录消息处理
     */
    private String loginFn(){

        return "";
    }

    /**
     * 解释json字符串
     */
    private MessageEntity string2Object(String json) {
        MessageEntity messageEntity = JSONObject.parseObject(json, MessageEntity.class);
        return messageEntity;
    }


    /**
     * 扫码成功
     */
    private void scanSuccess(ChannelHandlerContext ctx, String from, String to) {
        //TODO 验证 from  是否登录 from-> 微信登录token
        //TODO 没登录-->  ctx.writeAndFlush( new TextWebSocketFrame("{\"responseType\":6,\"message\":\"您还没登录\"}"))   return ;
        //TODO 登录--> 则向下
        for (Channel channel : ChannelData.loginChannelGroup) {
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
        for (Channel channel : ChannelData.adminLoginChannelGroup) {
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

        for (Channel channel : ChannelData.loginChannelGroup) {
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
        for (Channel channel : ChannelData.adminLoginChannelGroup) {
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
        for (Channel channel : ChannelData.loginChannelGroup) {
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
        for (Channel channel : ChannelData.adminLoginChannelGroup) {
            String asLongText = channel.id().asLongText();
            if (asLongText.equals(to)) {
                channel.writeAndFlush(new TextWebSocketFrame("{\"responseType\":8,\"message\":\"取消登录\"}"));
            }
        }
        ctx.writeAndFlush(new TextWebSocketFrame("{\"responseType\":8,\"message\":\"取消登录\"}"));
    }
}
