package cn.cyyaw.config.netty.controller;

import cn.cyyaw.common.util.StringUtilWHY;
import cn.cyyaw.config.netty.config.ChannelData;
import cn.cyyaw.config.netty.entity.ChannelObject;
import cn.cyyaw.config.netty.entity.MessageEntity;
import cn.cyyaw.config.table.table.dao.user.UGroupDao;
import cn.cyyaw.config.table.table.dao.user.UGroupMessageDao;
import cn.cyyaw.config.table.table.dao.user.UUserDao;
import cn.cyyaw.config.table.table.entity.user.UGroup;
import cn.cyyaw.config.table.table.entity.user.UGroupMessage;
import cn.cyyaw.config.table.table.entity.user.UUser;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 群信息
 */
@Slf4j
@Component
public class ChatGroupController {


    @Autowired
    private UGroupDao uGroupDao;

    @Autowired
    private UUserDao uUserDao;

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
        String message = msg.getMessage();

        // 保存消息
        UGroupMessage m = new UGroupMessage();
        m.setTid(StringUtilWHY.getUUID());
        m.setCreatetime(new Date());
        m.setDel(0);
        m.setGroupid(to);
        m.setContent(message);
        m.setFace(null);
        m.setUserid(from);
        m.setUsername("");
        m.setType(type == null ? 0 : type);
        uGroupMessageDao.save(m);
        //发送给群成员
        Map<String, ChannelObject> allChannel = ChannelData.allChannel;
        List<UUser> users = uUserDao.findByGroup(to);
        if(null != users){
            for(int i=0;i<users.size();i++){
                UUser u = users.get(i);
                String tid = u.getTid();
                // 给客服发送消息
                for(String key : allChannel.keySet()){
                    ChannelObject ch = allChannel.get(key);
                    if(tid.equals(ch.getTid()) && !tid.equals(from)){
                        Channel chan = ch.getChannel();
                        JSONObject js = new JSONObject();
                        js.put("message", message);
                        js.put("from", m.getGroupid());
                        js.put("to", tid);
                        js.put("responseType", 701);
                        chan.writeAndFlush(new TextWebSocketFrame(js.toJSONString()));
                    }
                }
            }
        }
        JSONObject gjs = new JSONObject();
        gjs.put("message", "发送成功");
        gjs.put("responseType", 701);
        channel.writeAndFlush(new TextWebSocketFrame(gjs.toJSONString()));
    }
}
