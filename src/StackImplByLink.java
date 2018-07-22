import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

public class StackImplByLink {
    private Node top=null;
    private int size=0;


    public StackImplByLink(){
        top=null;
    }


    //入栈函数
    public void push(Object data){
        //判断是不是空栈哈
        if(top==null)
        {
            top=new Node();
            top.setData(data);
            top.setNext(null);
            size++;
        }else{
            //不是空栈的时候
            //转移顶部哈
            Node current=new Node(data,top);
            top=current;
            size++;
        }
    }
    //出栈函数
    public Object pop()
    {
        if(top==null)
        {
            throw new NullPointerException("栈为空！");
        }

        //获取此时的top的data值哈
        Object outData=top.getData();

        //转移top,top下移一个哈
        Node current =top.getNext();
        top=current;
        size--;
        return outData;
    }

    //获取栈的长度哈
    public int getSize()
    {
        return size;
    }


    public static void main(String[] args) {

        StackImplByLink test=new StackImplByLink();
        test.push(123);
        test.push("wang");
        test.push("test1");
        test.push("test2");
        test.push("test3");
        test.push("test4");
        test.push("test5");
        test.push("test6");
        test.push("test7");
        test.push("test8");
        test.push("test9");
        test.push("test10");
        test.push("test11");

        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
    }

}
