package thinkingInJava.chapter20;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 注解处理器
 */
public class UserCaseTracker{

    public static void trackUserCases(List<Integer> userCases,Class<?> cl){
        for (Method method:cl.getDeclaredMethods()){
            UserCase uc = method.getAnnotation(UserCase.class);
            if(uc!=null){
                System.out.println("Found Use case"+uc.id()+" "+uc.description());
                userCases.remove(new Integer(uc.id()));
            }
        }
        for (int i:userCases){
            System.out.println("Warning: Missing use case "+ i);
        }
    }

    public static void main(String[] args) {
        List<Integer> userCases = new ArrayList<>();
        Collections.addAll(userCases,1,2,3,4);
        trackUserCases(userCases,PsswordUtils.class);
    }
}
