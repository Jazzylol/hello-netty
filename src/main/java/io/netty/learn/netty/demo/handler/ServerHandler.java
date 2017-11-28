package io.netty.learn.netty.demo.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledHeapByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class ServerHandler extends ChannelInboundHandlerAdapter {


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

        if (msg instanceof ByteBuf) {

            ByteBuf data = (ByteBuf) msg;

            System.out.println(data.toString(Charset.defaultCharset()));

        }


        String result = "hello Im server msg is 李四\r\n";
        System.out.println("begin to send msg to client msg is :" + result);
        ctx.channel().writeAndFlush(result);
    }


}
