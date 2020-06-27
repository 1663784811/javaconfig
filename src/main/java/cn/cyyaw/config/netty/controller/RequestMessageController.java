package cn.cyyaw.config.netty.controller;


import cn.cyyaw.common.util.StringUtilWHY;
import cn.cyyaw.config.netty.config.ChannelData;
import cn.cyyaw.config.netty.entity.ChannelObject;
import cn.cyyaw.config.netty.entity.MessageEntity;
import cn.cyyaw.config.table.table.dao.PrMessageDao;
import cn.cyyaw.config.table.table.entity.PrMessage;
import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

/**
 * 接收消息处理
 */
@Slf4j
@Component
public class RequestMessageController {

    @Autowired
    private PrMessageDao prMessageDao;

    @Autowired
    private AdminMessageController adminMessage;

    /**
     * 处理发过来的消息
     *
     * @param ctx
     * @param messageEntity
     */
    public void handleMessage(ChannelHandlerContext ctx, MessageEntity messageEntity) {
        Channel channel = ctx.channel();
        String lid = channel.id().asLongText();
        ChannelObject channelObject = ChannelData.allChannel.get(lid);
        String fromid = channelObject.getTid();
        String to = messageEntity.getTo();
        boolean issend = false;
        Integer type = messageEntity.getType();
        // 第一步：保存数据库
        PrMessage pm = new PrMessage();
        pm.setContent(messageEntity.getMessage());
        pm.setTid(StringUtilWHY.getUUID());
        pm.setType(null!=type ? type : 0);
        pm.setCreatetime(new Date());
        pm.setDel(0);
        pm.setStatus(0);
        pm.setTopruserid(to == null ? "": to);
        pm.setPruserid(fromid);
        PrMessage pms = prMessageDao.save(pm);
        if(!StringUtilWHY.isEmpty(to)){
            // 第二步：发送数据
            for (String key : ChannelData.allChannel.keySet()) {
                ChannelObject obj = ChannelData.allChannel.get(key);
                String tid = obj.getTid();
                if(null != tid && tid.equals(to)){
                    Channel ch = obj.getChannel();
                    HashMap<String, Object> omp = new HashMap<>();
                    omp.put("responseType",1);
                    omp.put("message",messageEntity.getMessage());
                    omp.put("type",messageEntity.getType());
                    omp.put("from",messageEntity.getFrom());
                    omp.put("to",messageEntity.getTo());
                    ch.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(omp)));
                    issend=true;
                }
            }
        }else {
            // 广播消息
            adminMessage.broadcast(ctx, messageEntity);
        }
        // 第三步：修改状态
        if(issend){
            pms.setStatus(1);
            prMessageDao.save(pms);
        }else{
            // 广播消息
            adminMessage.broadcast(ctx, messageEntity);
        }
    }

    /**
     * 接听用户消息
     */
    public void answerMessage(ChannelHandlerContext ctx, MessageEntity me) {
        String from = me.getFrom();
        String to = me.getTo();
        boolean issend = false; // 是否接听成功
        for (String key : ChannelData.allChannel.keySet()) {
            ChannelObject obj = ChannelData.allChannel.get(key);
            String tid = obj.getTid();
            Integer type = obj.getType();
            Channel ch = obj.getChannel();
            HashMap<String, Object> omp = new HashMap<>();
            if(null != tid && tid.equals(to)){
                omp.put("responseType",2);
                omp.put("message","接听用户消息");
                omp.put("from",from);
                omp.put("to",to);
                ch.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(omp)));
                issend=true;
            }else if(null != type && type==2 && null != tid && !tid.equals(from)){
                omp.put("responseType",3);
                omp.put("message","广播");
                omp.put("from",from);
                omp.put("to",to);
                omp.put("type",to);
                ch.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(omp)));
            }
        }
        if(issend){
            Channel ch = ctx.channel();
            HashMap<String, Object> omp = new HashMap<>();
            omp.put("responseType",2);
            omp.put("message","接听成功");
            omp.put("from",to);
            omp.put("to",from);
            ch.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(omp)));
        }
    }
}
