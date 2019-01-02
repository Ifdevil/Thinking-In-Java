package thinkingInJava.chapter18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Echo {

    public static void main(String[] args) throws IOException {

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ( (str = stdin.readLine())!=null){
            System.out.println(str);
        }
    }
}
