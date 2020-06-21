package cn.cyyaw.config.netty.config;
import cn.cyyaw.config.netty.config.HandlerState;
import cn.cyyaw.config.netty.controller.HandlerWebSocketText;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitializerServer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private HandlerWebSocketText handlerWebSocketText;

    @Autowired
    private HandlerState handlerState;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //=========  netty 自带
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        pipeline.addLast("chunkedWriteHandler", new ChunkedWriteHandler());
        pipeline.addLast("httpObjectAggregator", new HttpObjectAggregator(8192));
        pipeline.addLast("idleStateHandler", new IdleStateHandler(30, 30, 30));
        pipeline.addLast("webSocketServerProtocolHandler", new WebSocketServerProtocolHandler("/chat"));//它处理websocket握手以及控制帧的处理(关闭，Ping, Pong)。

        //========= 自定义
        pipeline.addLast("handlerState", handlerState);     //状态
        pipeline.addLast("handlerWebSocketText", handlerWebSocketText); //webSocket

    }
}
