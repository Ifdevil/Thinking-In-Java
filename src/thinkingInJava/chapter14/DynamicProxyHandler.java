package thinkingInJava.chapter14;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理：反射实现
 */
public class DynamicProxyHandler implements InvocationHandler{

    private Object proxied;

    public DynamicProxyHandler(Object proxied){
        this.proxied = proxied;
    }

    public Object invoke(Object proxy, Method method,Object[] args) throws Throwable {
        System.out.println("*** proxy: "+proxy.getClass()+", method: "+method+ ", args: "+args);
        if(args!=null){
            for (Object arg:args){
                System.out.println(""+arg);
            }
        }
        return method.invoke(proxied,args);
    }
}
class SimpleDynamicProxy{
    public static void consumer(Interfacle iface){
        iface.doSomething();
        iface.somethingElse("nanana");
    }

    public static void main(String[] args) {
        RealObject real = new RealObject();
        consumer(real);
        Interfacle proxy = (Interfacle) Proxy.newProxyInstance(Interfacle.class.getClassLoader(),new Class[]{ Interfacle.class}, new DynamicProxyHandler(real));
        consumer(proxy);
    }
}
