package cn.cyyaw.config.netty.controller;


import cn.cyyaw.config.netty.config.ChannelData;
import cn.cyyaw.config.netty.entity.ChannelObject;
import cn.cyyaw.config.netty.entity.MessageEntity;
import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理员消息控制器
 */
@Slf4j
@Component
public class AdminMessageController {

    /**
     * 广播管理员消息
     */
    public void broadcast(ChannelHandlerContext channel, MessageEntity messageEntity){
        log.info("广播管理员消息:{}", messageEntity.toString());
        Map<String, ChannelObject> ac = ChannelData.allChannel;
        for (String key: ac.keySet()) {
            ChannelObject co = ac.get(key);
            Integer type = co.getType();
            if(null != type && type == 2){
                Channel ch = co.getChannel();
                HashMap<String, Object> omp = new HashMap<>();
                omp.put("responseType",3); // 广播
                omp.put("message", messageEntity.getMessage()); // 来自类型
                omp.put("from", messageEntity.getFrom()); // 来自用户
                ch.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(omp)));
            }
        }
    }

    /**
     * 接听消息
     */
    public void answerMessage(ChannelHandlerContext channel, MessageEntity messageEntity){
        Map<String, ChannelObject> ac = ChannelData.allChannel;
        for (String key: ac.keySet()) {
            ChannelObject co = ac.get(key);
            Integer type = co.getType();
            if(null != type && type == 2){
                Channel ch = co.getChannel();
                HashMap<String, Object> omp = new HashMap<>();
                omp.put("","ddddd");
                ch.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(omp)));
            }
        }
    }



}
