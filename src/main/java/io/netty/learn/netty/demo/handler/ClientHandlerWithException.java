package io.netty.learn.netty.demo.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandlerWithException extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client channel active");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client begin to read data");
        System.out.println("client, String type :" +(msg instanceof String));
        System.out.println("client, ByteBuf type : " + (msg instanceof ByteBuf));
        System.out.println("client msg:" + msg);
    }




}
