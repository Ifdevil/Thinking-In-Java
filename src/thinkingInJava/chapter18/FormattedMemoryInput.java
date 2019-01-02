package thinkingInJava.chapter18;

import java.io.*;

/**
 * 格式化的内存输入
 */
public class FormattedMemoryInput {

    public static void main(String[] args) throws IOException {



        DataInputStream in = new DataInputStream(new ByteArrayInputStream
                            (BufferedInputFile.read("FormattedMemoryInput.java").getBytes()));
//        DataInputStream input = new DataInputStream(new BufferedInputStream (
//                new FileInputStream("FormattedMemoryInput.java")));
        while(true){
            System.out.println((char)in.readByte());
        }


    }
}
