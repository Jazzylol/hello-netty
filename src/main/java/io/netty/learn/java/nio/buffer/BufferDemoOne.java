package io.netty.learn.java.nio.buffer;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * create by Jazzylol at  2017/9/29
 * <p>
 * Description:
 */
public class BufferDemoOne {

    public static void main(String[] args)throws Exception {
        testBuffer();
    }

    private static void testBuffer() throws Exception{
        RandomAccessFile file = new RandomAccessFile("/Users/Macx/Documents/NIO源码分析之Buffer.md", "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = channel.read(buffer);
        while (read != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char)buffer.get());
            }
            buffer.clear();
            read = channel.read(buffer);
        }
        file.close();
    }
}
