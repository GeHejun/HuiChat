package com.ghj.common.netty;

import com.ghj.common.base.Constant;
import com.ghj.protocol.MessageProto;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.util.List;

import static io.netty.buffer.Unpooled.wrappedBuffer;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/8/27 13:40
 */
public class WebSocketChannelInitializer extends ChannelInitializer {

    SimpleChannelInboundHandler handler;

    public WebSocketChannelInitializer(SimpleChannelInboundHandler t) {
        this.handler = t;
    }

    @Override
    protected void initChannel(Channel channel) {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(Constant.MAX_AGGREGATED_CONTENT_LENGTH));
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new WebSocketServerProtocolHandler("/"));

        pipeline.addLast(new MessageToMessageDecoder<WebSocketFrame>() {
            @Override
            protected void decode(ChannelHandlerContext ctx, WebSocketFrame frame, List<Object> objs) {
                ByteBuf buf = frame.content();
                objs.add(buf);
                buf.retain();
            }
        });
        pipeline.addLast(new MessageToMessageEncoder<MessageLiteOrBuilder>() {
            @Override
            protected void encode(ChannelHandlerContext ctx, MessageLiteOrBuilder msg, List<Object> out) {
                ByteBuf result = null;
                if (msg instanceof MessageLite) {
                    result = wrappedBuffer(((MessageLite) msg).toByteArray());
                }
                if (msg instanceof MessageLite.Builder) {
                    result = wrappedBuffer(((MessageLite.Builder) msg).build().toByteArray());
                }

                WebSocketFrame frame = new BinaryWebSocketFrame(result);
                out.add(frame);
            }
        });
        pipeline.addLast(new ProtobufDecoder(MessageProto.Message.getDefaultInstance()));
        pipeline.addLast(handler);
    }
}
