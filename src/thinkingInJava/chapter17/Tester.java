package thinkingInJava.chapter17;

import java.util.ArrayList;
import java.util.List;

/**
 * 容器性能测试
 * @param <C>
 */
public class Tester<C> {
    // 格式化输出每条记录的占位长度
    public static int fieldWidth = 12;
    //测试参数数组
    public static TestParam[] defaultParams = TestParam.array(10,5000,100,5000,1000,5000,10000,500);
    //测试容器
    protected C container;
    //初始化容器
    protected C initialize(int size){return container;}
    //头部分割线
    private String headline = "";
    //测试的容器集合
    private List<TestContainer<C>> tests;
    //字符格式化
    private static String stringField(){
        return "%"+fieldWidth+"s";
    }
    //数字格式化
    private static String numberField(){
        return "%"+fieldWidth+"d";
    }
    //容器大小
    private static int sizeWidth = 5;
    private static String sizeField = "%"+sizeWidth+"s";
    private TestParam[] paramList = defaultParams;
    public Tester(C container,List<TestContainer<C>> tests){
        this.container = container;
        this.tests = tests;
        if(container!=null){
            headline = container.getClass().getSimpleName();
        }
    }
    public Tester(C container,List<TestContainer<C>> tests,TestParam[] paramList){
        this(container,tests);
        this.paramList = paramList;
    }
    public void setHeadline(String newHeadline){
        this.headline = newHeadline;
    }
    public static <C> void run(C cntnr,List<TestContainer<C>> tests){
        new Tester<C>(cntnr,tests).timedTest();
    }
    public static <C> void run(C cntnr,List<TestContainer<C>> tests,TestParam[] paramList){
        new Tester<C>(cntnr,tests,paramList).timedTest();
    }

    /**
     * 头部说明
     */
    private void displayHeader(){
        int width = fieldWidth * tests.size()+sizeWidth;
        int dashLength = width-headline.length()-1;
        StringBuilder head = new StringBuilder(width);
        for (int i=0;i<dashLength/2;i++){
            head.append("-");
        }
        head.append("");
        head.append(headline);
        head.append("");
        for (int i =0;i<dashLength/2;i++){
            head.append("-");
        }
        System.out.println(head);
        System.out.format(sizeField,"size");
        for (TestContainer test:tests){
            System.out.format(stringField(),test.name);
        }
        System.out.println();
    }

    /**
     * 容器性能测试--计算执行纳秒时间差
     */
    public void timedTest(){
        displayHeader();
        for (TestParam param:paramList){
            System.out.format(sizeField,param.size);
            for (TestContainer<C> test:tests){
                C container = initialize(param.size);
                long start = System.nanoTime();
                int reps = test.test(container,param);
                long duration = System.nanoTime() - start;
                long timePerRep = duration/reps;
                System.out.format(numberField(),timePerRep);
            }
            System.out.println();
        }
    }
}
