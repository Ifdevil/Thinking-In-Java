package thinkingInJava.chapter18;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 内存映射文件
 */
public class LargeMappedFiles {

    static int length = 0x8FFFFFF;

    public static void main(String[] args) throws IOException {

        MappedByteBuffer out = new RandomAccessFile("test.dat","rw")
                                    .getChannel().map(FileChannel.MapMode.READ_WRITE,0,length);
        for (int i = 0; i < length; i++) {
            out.put((byte)'x');
        }
        System.out.println("Finishing Write");
        for (int i = length/2; i < length/2 + 6; i++) {
            System.out.print((char)out.get(i));
        }
    }
}
