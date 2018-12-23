package thinkingInJava.chapter15;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * 实现参数化接口
 * @param <T>
 */
public interface Payable<T> {
}
class Employee implements Payable<Employee>{
}
//class Hourly extends Employee implements Payable<Hourly>{
//
//}


//class Employee implements Payable{
//
//}
//class Hourly extends Employee implements Payable{}