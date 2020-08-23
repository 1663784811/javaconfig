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

import java.util.List;


/**
 * 好友信息
 */
@Slf4j
@Component
public class ChatUserController {

    @Autowired
    private UUserDao uUserDao;


    public void getUserInfo(Channel channel, MessageEntity msg) {
        String userid = msg.getMessage();
        List<UUser> uUsers = uUserDao.findByUserid(userid);
        JSONObject gjs = new JSONObject();
        gjs.put("message", uUsers);
        gjs.put("responseType", 600);
        channel.writeAndFlush(new TextWebSocketFrame(gjs.toJSONString()));
    }
}
