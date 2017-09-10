package io.netty.learn.java.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

/**
 * create by Jazzylol at  2017/9/10
 * <p>
 * Description:
 */
public class SimpleNioServer {

    private static final String host = "127.0.0.1";
    private static final int port = 10211;
    private static boolean notStopping = false;


    public static void main(String[] args) {
        start();
    }

    private static void start() {

        Selector selector;
        ServerSocketChannel server;
        try {
            selector = Selector.open();
            server = ServerSocketChannel.open();
            server.socket().bind(new InetSocketAddress(host, port));
            server.configureBlocking(false);
            server.register(selector, SelectionKey.OP_ACCEPT);

            while (notStopping) {

                int select = selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                for (SelectionKey selectionKey : selectionKeys) {









                }



            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }


    }


}
