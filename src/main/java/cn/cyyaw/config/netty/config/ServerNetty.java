package cn.cyyaw.config.netty.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServerNetty implements CommandLineRunner {

    @Value("${netty.port}")
    private int port;

    @Autowired
    InitializerServer initializerServer;

    private EventLoopGroup boosGroup = new NioEventLoopGroup();
    private EventLoopGroup workerGroup = new NioEventLoopGroup();


    @Override
    public void run(String... args) {
        new Thread() {
            @Override
            public void run() {
                try {
                    startNetty(port);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 开启netty
     *
     * @param port
     * @throws InterruptedException
     */
    public void startNetty(int port) throws InterruptedException {
        log.info("正在开启聊天服务，端口:" + port);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup, workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            //======添加handler
            serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
            serverBootstrap.childHandler(initializerServer);
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            destroy();
        }
    }

    /**
     * 关闭netty
     */
    public void destroy() {
        log.info("关闭聊天服务");
        if (!boosGroup.isShutdown()) {
            boosGroup.shutdownGracefully();
        }
        if (!workerGroup.isShutdown()) {
            workerGroup.shutdownGracefully();
        }
    }


}
