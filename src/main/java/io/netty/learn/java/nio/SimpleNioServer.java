package io.netty.learn.java.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.logging.Logger;

/**
 * create by Jazzylol at  2017/9/10
 * <p>
 * Description:
 */
public class SimpleNioServer implements Runnable {

    private static final String className = "SimpleNioServer";
    private static final Logger log = Logger.getLogger(className);


    private static final String host = "127.0.0.1";
    private static final int port = 10211;
    private static boolean notStopping = true;


    public static void main(String[] args) {
        SimpleNioServer nioServer = new SimpleNioServer();
        new Thread(nioServer).start();
    }

    public void run() {

        Selector selector;
        ServerSocketChannel channel = null;
        try {
            selector = Selector.open();
            channel = ServerSocketChannel.open();
            channel.socket().bind(new InetSocketAddress(host, port));
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_ACCEPT);
            while (notStopping) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey selectionKey : selectionKeys) {
                    if (selectionKey.isAcceptable()) {
                        fireAcceptableEvent(selector, selectionKey);
                    } else if (selectionKey.isReadable()) {
                        fireReadableEvent(selector, selectionKey);
                    } else if (selectionKey.isWritable()) {
                        fireWriteableEvent(selector, selectionKey);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * fire readable evnet
     * @param selector
     * @param selectionKey
     * @throws IOException
     */
    private void fireReadableEvent(Selector selector, SelectionKey selectionKey) throws IOException {
        System.out.println("fireReadableEvent begin to read data ...");
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = channel.read(buffer);
        while (read > 0) {
            buffer.flip();
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);
            System.out.println(new String(data, 0, data.length, Charset.defaultCharset()));
        }
    }



    /**
     * 获取channel注册 read 和 write事件
     * @param selector
     * @param selectionKey
     * @throws IOException
     */
    private void fireAcceptableEvent(Selector selector, SelectionKey selectionKey) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
        SocketChannel channel = serverSocketChannel.accept();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
    }

    /**
     * fire writeable event
     * @param selector
     * @param selectionKey
     * @throws IOException
     */
    private void fireWriteableEvent(Selector selector, SelectionKey selectionKey) throws IOException {
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        channel.write(ByteBuffer.wrap(new String("server has received and reply: hello client").getBytes()));
    }
}
