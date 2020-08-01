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

}
