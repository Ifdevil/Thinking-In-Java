package thinkingInJava.chapter18;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 利用TranserTo和TranseferFrom 实现将一个通道和另一个通道相连
 */
public class TransferTo {

    public static void main(String[] args) throws IOException {

        String[] arg = {"src\\thinkingInJava\\chapter18\\ChannelCopy.java","test.txt"};
        FileChannel
                in = new FileInputStream(arg[0]).getChannel(),
                out = new FileOutputStream(arg[1]).getChannel();
        in.transferTo(0,in.size(),out);
        //Or
        out.transferFrom(in,0,in.size());
    }
}
