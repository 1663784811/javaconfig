package cn.cyyaw.config.netty.controller;


import cn.cyyaw.common.util.StringUtilWHY;
import cn.cyyaw.config.netty.config.ChannelData;
import cn.cyyaw.config.netty.entity.ChannelObject;
import cn.cyyaw.config.netty.entity.MessageEntity;
import cn.cyyaw.config.table.table.dao.PrUserDao;
import cn.cyyaw.config.table.table.entity.PrUser;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 注册身份
 */
@Slf4j
@Component
public class ChatLoginController {

    @Autowired
    private PrUserDao prUserDao;


    public String loginFn(Channel channel, MessageEntity messageEntity) {
        PrUser pr = new PrUser();
        String message = messageEntity.getMessage();
        if (StringUtilWHY.isEmpty(message)) {
            pr = createPrUser();
        } else {
            pr.setTid(message);
            List<PrUser> prUsers = prUserDao.findAll(Example.of(pr));
            if(null != prUsers && prUsers.size()>0){
                pr = prUsers.get(0);
                pr.setLastlogintime(new Date());
                pr = prUserDao.save(pr);
            }else{
                pr = createPrUser();
            }
        }
        String tid = pr.getTid();
        String lid = channel.id().asLongText();
        ChannelObject channelObject = ChannelData.allChannel.get(lid);
        if(null != channelObject){
            channelObject.setId(tid);
        }
        return tid;
    }

    private PrUser createPrUser(){
        PrUser pr = new PrUser();
        pr.setCreatetime(new Date());
        pr.setTid(StringUtilWHY.getUUID());
        pr.setDel(0);
        pr.setLastlogintime(new Date());
        pr.setNote("游客");
        pr = prUserDao.save(pr);
        return pr;
    }


}
