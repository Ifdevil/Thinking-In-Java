package thinkingInJava.chapter18;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BufferedInputStreamTest {
    public void readFromFile(String filename) {
        BufferedInputStream bufferedInput = null;
        byte[] buffer = new byte[1024];

        try {

            //创建BufferedInputStream 对象
            bufferedInput = new BufferedInputStream(new FileInputStream(filename));

            int bytesRead = 0;

            //从文件中按字节读取内容，到文件尾部时read方法将返回-1
            while ((bytesRead = bufferedInput.read(buffer)) != -1) {

                //将读取的字节转为字符串对象
                String chunk = new String(buffer, 0, bytesRead);
                System.out.print(chunk);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //关闭 BufferedInputStream
            try {
                if (bufferedInput != null)
                    bufferedInput.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
