package io.netty.learn.java.nio.channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo_1 {


    public static void main(String[] args) throws Exception {


        testOne();


    }

    private static void testOne() throws Exception{


        RandomAccessFile randomAccessFile = new RandomAccessFile("./src/main/resource/channel_demo.txt","rw");


        FileChannel channel = randomAccessFile.getChannel();

        ByteBuffer dest = ByteBuffer.allocate(1024);

        int read = channel.read(dest);

        while (read != -1) {

            dest.flip();

            while (dest.hasRemaining()) {
                System.out.print((char)dest.get());
            }
            System.out.println();
            dest.clear();
            read = channel.read(dest);
        }

        channel.close();
    }


}
