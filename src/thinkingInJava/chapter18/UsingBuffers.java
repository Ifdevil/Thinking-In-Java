package thinkingInJava.chapter18;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * 用缓冲器操作数据
 */
public class UsingBuffers {

    private static void symmetricScramble(CharBuffer charBuffer){
        while (charBuffer.hasRemaining()){
            charBuffer.mark();
            char c1 = charBuffer.get();
            char c2 = charBuffer.get();
            charBuffer.reset();
            charBuffer.put(c2).put(c1);
        }
    }

    public static void main(String[] args) {
        char[] data = "UsingBuffers".toCharArray();
        ByteBuffer buff = ByteBuffer.allocate(data.length*2);
        CharBuffer cb = buff.asCharBuffer();
        cb.put(data);
        System.out.println(cb.rewind());

    }
}
