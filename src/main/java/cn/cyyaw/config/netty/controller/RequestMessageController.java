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
        PrMessage pm = new PrMessage();
        pm.setContent(messageEntity.getMessage());
        pm.setTid(StringUtilWHY.getUUID());
        Integer type = messageEntity.getType();
        pm.setType(null!=type ? type : 0);
        pm.setCreatetime(new Date());
        pm.setDel(0);
        pm.setStatus(0);
        pm.setTopruserid(messageEntity.getTo());
        pm.setPruserid(fromid);
        PrMessage pms = prMessageDao.save(pm);
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
        if(issend){
            pms.setStatus(1);
            prMessageDao.save(pms);
        }
    }
}
