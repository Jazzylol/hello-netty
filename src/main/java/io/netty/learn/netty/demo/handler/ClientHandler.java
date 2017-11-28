package io.netty.learn.netty.demo.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

import java.nio.charset.Charset;

public class ClientHandler extends ChannelInboundHandlerAdapter {


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


        if (msg instanceof ByteBuf) {

            ByteBuf data = (ByteBuf) msg;

            System.out.println(data.toString(Charset.defaultCharset()));

        }


        ctx.channel().attr(AttributeKey.valueOf("age")).set("10086" + ((ByteBuf) msg).toString(Charset.defaultCharset()));
        ctx.channel().close();
    }




}
