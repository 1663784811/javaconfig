package cn.cyyaw.config.netty.config;

import cn.cyyaw.config.netty.entity.ChannelObject;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;
import java.util.Map;

public class ChannelData {
    private ChannelData (){}

    public static Map<String, ChannelObject> allChannel= new HashMap<>();

    /**
     * 登录组
     */
    public static ChannelGroup loginChannelGroup = new DefaultChannelGroup("login", GlobalEventExecutor.INSTANCE);
    /**
     * 登录组
     */
    public static ChannelGroup adminLoginChannelGroup = new DefaultChannelGroup("adminLogin", GlobalEventExecutor.INSTANCE);
    /**
     * 聊天组
     */
    public static ChannelGroup chatChannelGroup = new DefaultChannelGroup("chat", GlobalEventExecutor.INSTANCE);
    /**
     * 客服组
     */
    public static ChannelGroup serverChannelGroup = new DefaultChannelGroup("server", GlobalEventExecutor.INSTANCE);
    /**
     * 进入
     */
    public static ChannelGroup channelGroup = new DefaultChannelGroup("channelNum", GlobalEventExecutor.INSTANCE);
}
