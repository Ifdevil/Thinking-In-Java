package thinkingInJava.chapter18;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 新IO 利用FileChannel实现文件读取写入
 */
public class ChannelCopy {

    private static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {
        String[] arg = {"src\\thinkingInJava\\chapter18\\ChannelCopy.java","test.txt"};
        FileChannel
                in = new FileInputStream(arg[0]).getChannel(),
                out = new FileOutputStream(arg[1]).getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        while (in.read(buff) != -1){
            buff.flip();
            out.write(buff);
            buff.clear();
        }
    }
}
