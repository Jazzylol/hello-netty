package io.netty.learn.netty.demo.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {





    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("用户端开始读取数据");
        System.out.println("client:" + msg instanceof String);
        System.out.println("client:" + (msg instanceof ByteBuf));
        System.out.println("client msg:" + msg);
    }




}
