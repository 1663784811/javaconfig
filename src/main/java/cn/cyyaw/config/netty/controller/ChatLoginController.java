package cn.cyyaw.config.netty.controller;


import cn.cyyaw.config.netty.entity.MessageEntity;
import cn.cyyaw.config.table.table.dao.user.UUserDao;
import cn.cyyaw.config.table.table.entity.user.UUser;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 注册身份
 */
@Slf4j
@Component
public class ChatLoginController {



    @Autowired
    private UUserDao uUserDao;

    /**
     * 聊天登录
     * {
     *     usertype : 2, //客服，
     * }
     */
    public void loginFn(Channel channel, MessageEntity msg) {
        String userid = msg.getFrom();
        UUser users = uUserDao.findFirstByTid(userid);
        JSONObject gjs = new JSONObject();
        if(null != users){
            gjs.put("message", users);
            gjs.put("responseType", 100);
            channel.writeAndFlush(new TextWebSocketFrame(gjs.toJSONString()));
        }else {
            gjs.put("message", "登录失败");
            gjs.put("responseType", 400);
        }
        channel.writeAndFlush(new TextWebSocketFrame(gjs.toJSONString()));
    }


}
