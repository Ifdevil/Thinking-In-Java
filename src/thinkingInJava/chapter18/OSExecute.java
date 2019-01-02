package thinkingInJava.chapter18;


import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * 进程控制
 */
public class OSExecute {

    public static void commond(String command){
        boolean err = false;
        try {
            Process process = new ProcessBuilder(command.split(" ")).start();
            BufferedReader result = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s ;
            while ((s = result.readLine())!=null){
                System.out.println(s);
            }
            BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((s = errors.readLine())!=null){
                System.err.println(s);
                err = true;
            }
        }catch (Exception e){
            if(!command.startsWith("CMD /C")){
                commond("CMD /C "+command);
            }else{
                throw new RuntimeException();
            }
        }
        if(err){
            throw new OSExecuteException("Errors executing " + command);
        }
    }


}

