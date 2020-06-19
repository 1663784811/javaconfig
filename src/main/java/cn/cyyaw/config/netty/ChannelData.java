package cn.cyyaw.config.netty;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChannelData {
    private ChannelData (){}
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
}
