package cn.cyyaw.config.netty.controller;


import cn.cyyaw.common.util.StringUtilWHY;
import cn.cyyaw.config.netty.config.ChannelData;
import cn.cyyaw.config.netty.entity.ChannelObject;
import cn.cyyaw.config.netty.entity.MessageEntity;
import cn.cyyaw.config.table.table.dao.user.UGroupDao;
import cn.cyyaw.config.table.table.dao.user.UGroupUserDao;
import cn.cyyaw.config.table.table.dao.user.UUserDao;
import cn.cyyaw.config.table.table.entity.user.UGroup;
import cn.cyyaw.config.table.table.entity.user.UGroupUser;
import cn.cyyaw.config.table.table.entity.user.UUser;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * 注册身份
 */
@Slf4j
@Component
public class ChatLoginController {

    @Autowired
    private UUserDao uUserDao;

    @Autowired
    private UGroupDao uGroupDao;

    @Autowired
    private UGroupUserDao uGroupUserDao;
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
            gjs.put("responseType", 500);
            Map<String, ChannelObject> allChannel = ChannelData.allChannel;
            ChannelObject ch = allChannel.get(channel.id().asLongText());
            ch.setTid(users.getTid());
        }else {
            gjs.put("message", "登录失败");
            gjs.put("responseType", 400);
        }
        channel.writeAndFlush(new TextWebSocketFrame(gjs.toJSONString()));
    }


    /**
     * 联系客服
     */
    public void linkServer(Channel channel, MessageEntity msg) {
        String userid = msg.getFrom();  // 用户ID
        String groupid = msg.getTo();  // 群ID
        //查询群消息
        if(StringUtilWHY.isEmpty(userid)){
            userid = StringUtilWHY.getUUID();
        }
        UGroup uGroup = uGroupDao.findByTid(groupid);
        if(null == uGroup){
            groupid = StringUtilWHY.getUUID();
            // 创建群
            UGroup  group= new UGroup();
            group.setCreatetime(new Date());
            group.setDel(0);
            group.setTid(groupid);
            group.setIntroduce("联系客服时创建");
            group.setName(userid);
            group.setType(1);
            group.setNote("客服群");
            uGroupDao.save(group);
            Map<String, ChannelObject> allChannel = ChannelData.allChannel;
            // 拉客服
            for (UUser uUser : uUserDao.findByType(1)) {
                String tid = uUser.getTid();
                UGroupUser ur = new UGroupUser();
                ur.setTid(StringUtilWHY.getUUID());
                ur.setCreatetime(new Date());
                ur.setDel(0);
                ur.setGrade(0);
                ur.setGroupid(groupid);
                ur.setNote("客服群");
                ur.setUserid(tid);
                ur.setType(2);
                UGroupUser gr = uGroupUserDao.save(ur);
                // 给客服发送消息
                for(String key : allChannel.keySet()){
                    ChannelObject ch = allChannel.get(key);
                    if(tid.equals(ch.getTid())){
                        Channel chan = ch.getChannel();
                        JSONObject js = new JSONObject();
                        js.put("message", gr);
                        js.put("from", groupid);
                        js.put("to", tid);
                        js.put("responseType", 702);
                        chan.writeAndFlush(new TextWebSocketFrame(js.toJSONString()));
                    }
                }
            }
            UGroupUser ur = new UGroupUser();
            ur.setTid(StringUtilWHY.getUUID());
            ur.setCreatetime(new Date());
            ur.setDel(0);
            ur.setGrade(0);
            ur.setGroupid(groupid);
            ur.setNote("客服群");
            ur.setUserid(userid);
            ur.setType(1);
            uGroupUserDao.save(ur);
            //====== 设置
            ChannelObject ch = allChannel.get(channel.id().asLongText());
            ch.setId(userid);
            ch.setType(1);
        }
        JSONObject gjs = new JSONObject();
        gjs.put("message", "联系成功");
        gjs.put("from", groupid);
        gjs.put("to", userid);
        gjs.put("responseType", 100);
        channel.writeAndFlush(new TextWebSocketFrame(gjs.toJSONString()));
    }
}
