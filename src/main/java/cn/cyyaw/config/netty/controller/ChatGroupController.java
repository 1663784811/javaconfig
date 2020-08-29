package cn.cyyaw.config.netty.controller;

import cn.cyyaw.common.util.StringUtilWHY;
import cn.cyyaw.config.netty.config.ChannelData;
import cn.cyyaw.config.netty.entity.ChannelObject;
import cn.cyyaw.config.netty.entity.MessageEntity;
import cn.cyyaw.config.table.table.dao.user.UGroupDao;
import cn.cyyaw.config.table.table.dao.user.UGroupMessageDao;
import cn.cyyaw.config.table.table.dao.user.UGroupUserDao;
import cn.cyyaw.config.table.table.entity.user.UGroup;
import cn.cyyaw.config.table.table.entity.user.UGroupMessage;
import cn.cyyaw.config.table.table.entity.user.UGroupUser;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
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
    private UGroupUserDao uGroupUserDao;

    @Autowired
    private UGroupMessageDao uGroupMessageDao;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取群信息
     *
     * @param channel
     * @param msg
     */
    public void getGroupInfo(Channel channel, MessageEntity msg) {
        String userid = msg.getFrom();
        List<UGroup> uGroups = uGroupDao.findByUserid(userid);
        JSONObject gjs = new JSONObject();
        gjs.put("message", uGroups);
        gjs.put("responseType", 700);
        channel.writeAndFlush(new TextWebSocketFrame(gjs.toJSONString()));
    }

    /**
     * 发送群消息
     *
     * @param channel
     * @param msg
     */
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
        List<UGroupUser> uGroupUsers = uGroupUserDao.findByGroupid(to);
        if (null != uGroupUsers) {
            for (int i = 0; i < uGroupUsers.size(); i++) {
                UGroupUser u = uGroupUsers.get(i);
                String tid = u.getUserid();
                if (!tid.equals(from)) {
                    // 给客服发送消息
                    for (String key : allChannel.keySet()) {
                        ChannelObject ch = allChannel.get(key);
                        String cid = ch.getTid();
                        if (tid.equals(cid)) {
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
        }
        JSONObject gjs = new JSONObject();
        gjs.put("message", "发送成功");
        gjs.put("responseType", 200);
        channel.writeAndFlush(new TextWebSocketFrame(gjs.toJSONString()));
    }

    /**
     * 获取历史记录
     */
    public void getHistory(Channel channel, MessageEntity msg) {
        String to = msg.getTo();
        String from = msg.getFrom();
        Integer type = msg.getType();
        String message = msg.getMessage();
        if (!StringUtilWHY.isEmpty(message)) {
            JSONArray objArr = JSONArray.parseArray(message);
            for (int i = 0; i < objArr.size(); i++) {
                JSONObject obj = objArr.getJSONObject(i);
                String groupid = obj.getString("id");
                Date createtime = obj.getDate("time");
                //查询数据
                Pageable pageable = PageRequest.of(0, 10, Sort.by("createtime").descending());
                List<UGroupMessage> messages = uGroupMessageDao.findByGroupidAndCreatetime(groupid, createtime, pageable);
                //发送数据
                JSONObject gjs = new JSONObject();
                gjs.put("message", messages);
                gjs.put("responseType", 705);
                channel.writeAndFlush(new TextWebSocketFrame(gjs.toJSONString()));
            }
        }
    }

    /**
     * 获取群用户数据
     */
    public void getGroupUser(Channel channel, MessageEntity msg) {
        String from = msg.getFrom();
        if (!StringUtilWHY.isEmpty(from)) {
            String sql = "select * from u_group_user ugu where ugu.groupid in (select ug.groupid from u_group_user ug LEFT JOIN u_user u ON ug.userid = u.tid where ug.userid =?)";
            List<Map<String, Object>> messages = jdbcTemplate.queryForList(sql, from);
            if (null != messages) {
                Map<String, ChannelObject> allChannel = ChannelData.allChannel;
                for (int i = 0; i < messages.size(); i += 1) {
                    String tid = (String) messages.get(i).get("userid");
                    boolean b = false;
                    for(String key : allChannel.keySet()){
                        ChannelObject chano = allChannel.get(key);
                        if(tid.equals(chano.getTid())){
                            b=true;
                            break;
                        }
                    }
                    messages.get(i).put("isonline", b);
                }
            }
            JSONObject gjs = new JSONObject();
            gjs.put("message", messages);
            gjs.put("responseType", 706);
            channel.writeAndFlush(new TextWebSocketFrame(gjs.toJSONString()));
        }
    }
}
































