package io.netty.learn.netty.demo;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.learn.netty.demo.handler.ClientHandler;

import java.nio.charset.Charset;

/**
 * create by Jazzylol at  2017/9/10
 * <p>
 * Description:
 */
public class SimpleNettyClient {


    public static void main(String[] args) throws InterruptedException {


        init();



    }

    private static void init() throws InterruptedException {


        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_BACKLOG, 128);
            bootstrap.group(group);
            bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {

                protected void initChannel(NioSocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ClientHandler());
                    ch.pipeline().addLast(new StringEncoder());
                }
            });


            ChannelFuture future = bootstrap.connect("localhost", 8080).sync();

            String person = "张三";
            future.channel().writeAndFlush(person);
//            ByteBuf buf = PooledByteBufAllocator.DEFAULT.buffer();
//            buf.writeBytes(person.getBytes(Charset.defaultCharset()));
//            future.channel().writeAndFlush(buf);
            future.channel().closeFuture();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }


    }


}
