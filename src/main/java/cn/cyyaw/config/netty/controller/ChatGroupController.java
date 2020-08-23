package cn.cyyaw.config.netty.controller;

import cn.cyyaw.config.netty.entity.MessageEntity;
import cn.cyyaw.config.table.table.dao.user.UGroupDao;
import cn.cyyaw.config.table.table.entity.user.UGroup;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 群信息
 */
@Slf4j
@Component
public class ChatGroupController {


    @Autowired
    private UGroupDao uGroupDao;

    // 获取群信息
    public void getGroupInfo(Channel channel, MessageEntity msg) {
        String userid = msg.getMessage();
        List<UGroup> uGroups = uGroupDao.findByUserid(userid);
        JSONObject gjs = new JSONObject();
        gjs.put("message", uGroups);
        gjs.put("responseType", 700);
        channel.writeAndFlush(new TextWebSocketFrame(gjs.toJSONString()));
    }


}
