package cn.cyyaw.config.netty.controller;

import cn.cyyaw.common.util.StringUtilWHY;
import cn.cyyaw.config.netty.entity.MessageEntity;
import cn.cyyaw.config.table.table.dao.user.UGroupDao;
import cn.cyyaw.config.table.table.dao.user.UGroupMessageDao;
import cn.cyyaw.config.table.table.entity.user.UGroup;
import cn.cyyaw.config.table.table.entity.user.UGroupMessage;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


/**
 * 群信息
 */
@Slf4j
@Component
public class ChatGroupController {


    @Autowired
    private UGroupDao uGroupDao;

    @Autowired
    private UGroupMessageDao uGroupMessageDao;

    // 获取群信息
    public void getGroupInfo(Channel channel, MessageEntity msg) {
        String userid = msg.getFrom();
        List<UGroup> uGroups = uGroupDao.findByUserid(userid);
        JSONObject gjs = new JSONObject();
        gjs.put("message", uGroups);
        gjs.put("responseType", 700);
        channel.writeAndFlush(new TextWebSocketFrame(gjs.toJSONString()));
    }

    //发送群消息
    public void sendGroupMessage(Channel channel, MessageEntity msg) {
        String to = msg.getTo();
        String from = msg.getFrom();
        Integer type = msg.getType();

        // 保存消息
        UGroupMessage m = new UGroupMessage();
        m.setTid(StringUtilWHY.getUUID());
        m.setCreatetime(new Date());
        m.setDel(0);
        m.setGroupid(to);
        m.setContent(msg.getMessage());
        m.setFace(null);
        m.setUserid(from);
        m.setUsername("");
        m.setType(type == null ? 0 : type);
        uGroupMessageDao.save(m);
        //发送给群成员

//        String lid = channel.id().asLongText();
//        ChannelObject channelObject = ChannelData.allChannel.get(lid);
//        if(null != channelObject){
//            channelObject.setId(tid);
//            channelObject.setTid(tid);
//        }


        JSONObject gjs = new JSONObject();
        gjs.put("message", "发送成功");
        gjs.put("responseType", 701);
        channel.writeAndFlush(new TextWebSocketFrame(gjs.toJSONString()));
    }
}
