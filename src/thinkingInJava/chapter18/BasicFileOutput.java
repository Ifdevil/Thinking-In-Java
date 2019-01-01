package thinkingInJava.chapter18;

import java.io.*;

/**
 * 基本的文件读取写入 BufferedRead PrintWriter
 */
public class BasicFileOutput {

    static String file = "BasicFileOutput.out";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(
                            new StringReader(BufferedInputFile.read("src\\thinkingInJava\\chapter18\\BasicFileOutput.java")));
        PrintWriter pw = new PrintWriter(file);
        int lineCount = 0;
        String s ;
        while ((s = in.readLine())!= null){
            pw.println(lineCount++ +":"+s);
        }
        pw.close();
        System.out.println(BufferedInputFile.read(file));
    }
}
