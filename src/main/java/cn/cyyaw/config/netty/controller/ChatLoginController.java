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

    /**
     * 聊天登录
     */
    public String loginFn(Channel channel, MessageEntity messageEntity) {
        PrUser pr = new PrUser();
        String message = messageEntity.getMessage();
        if (StringUtilWHY.isEmpty(message)) {
            PrUser prUser = new PrUser();
            prUser.setUsertype(messageEntity.getUsertype());
            pr = createPrUser(prUser);
        } else {
            pr.setTid(message);
            List<PrUser> prUsers = prUserDao.findAll(Example.of(pr));
            if(null != prUsers && prUsers.size()>0){
                pr = prUsers.get(0);
                pr.setLastlogintime(new Date());
                pr.setUsertype(messageEntity.getUsertype());
                pr.setUsertype(messageEntity.getUsertype());
                pr = prUserDao.save(pr);
            }else{
                PrUser prUser = new PrUser();
                prUser.setUsertype(messageEntity.getUsertype());
                pr = createPrUser(prUser);
            }
        }
        String tid = pr.getTid();
        String lid = channel.id().asLongText();
        ChannelObject channelObject = ChannelData.allChannel.get(lid);
        if(null != channelObject){
            channelObject.setTid(tid);
        }
        return tid;
    }

    private PrUser createPrUser(PrUser pr){
        pr.setCreatetime(new Date());
        pr.setTid(StringUtilWHY.getUUID());
        pr.setDel(0);
        pr.setLastlogintime(new Date());
        Integer usertype = pr.getUsertype();
        pr.setNote((usertype != null || usertype==1)  ? "管理员":"游客" );
        pr = prUserDao.save(pr);
        return pr;
    }


}
