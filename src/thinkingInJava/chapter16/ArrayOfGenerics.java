package thinkingInJava.chapter16;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组与泛型
 */
public class ArrayOfGenerics {

    public static void main(String[] args) {
        List<String>[] ls;
        List[] la = new List[10];
        ls = (List<String>[]) la;
        ls[0] = new ArrayList<String>();
        //ls[1] = new ArrayList<Integer>();
        Object[] objs = ls;
        objs[1] = new ArrayList<Integer>();

        List<Integer>[] li = (List<Integer>[]) new ArrayList[10];
    }

}
