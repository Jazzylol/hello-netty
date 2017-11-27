package io.netty.learn.netty.demo.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandlerWithException extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel active Server");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server开始读取数据");
        System.out.println("server,String type:" + (msg instanceof String));
        System.out.println("server,ByteBuf type:" + (msg instanceof ByteBuf));
        System.out.println("server msg:" + msg);

        String result = "hello Im server msg is 李四";
        ctx.channel().writeAndFlush(result);
    }


}
