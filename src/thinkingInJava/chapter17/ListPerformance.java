package thinkingInJava.chapter17;

import thinkingInJava.chapter16.CountingGenerateor;
import thinkingInJava.chapter16.Generated;

import java.util.*;

/**
 *  List性能测试
 */
public class ListPerformance {

    static Random random = new Random();
    static int reps = 1000;
    static List<TestContainer<List<Integer>>> tests = new ArrayList<TestContainer<List<Integer>>>();
    static List<TestContainer<LinkedList<Integer>>> qTests = new ArrayList<TestContainer<LinkedList<Integer>>>();

    static {
        tests.add(new TestContainer<List<Integer>>("add") {
            @Override
            int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int listsize = tp.size;
                for (int i = 0; i < loops ; i++) {
                    list.clear();
                    for (int j = 0; j < listsize ; j++) {
                        list.add(j);
                    }
                }
                return loops*listsize;
            }
        });
        tests.add(new TestContainer<List<Integer>>("get") {
            @Override
            int test(List<Integer> container, TestParam tp) {
                int loops = tp.loops*reps;
                int listsize = container.size();
                for (int i = 0; i < loops ; i++) {
                    container.get(random.nextInt(listsize));
                }
                return loops;
            }
        });
        tests.add(new TestContainer<List<Integer>>("set") {
            @Override
            int test(List<Integer> container, TestParam tp) {
                int loops = tp.loops*reps;
                int listsize = container.size();
                for (int i = 0; i < loops; i++) {
                    container.set(random.nextInt(listsize),47);
                }
                return loops;
            }
        });
        tests.add(new TestContainer<List<Integer>>("iteradd") {
            @Override
            int test(List<Integer> container, TestParam tp) {
                int loops = 100000;
                int listsize = container.size()/2;
                ListIterator it = container.listIterator();
                for (int i = 0; i < loops; i++) {
                    it.add(47);
                }
                return loops;
            }
        });
        tests.add(new TestContainer<List<Integer>>("insert") {
            @Override
            int test(List<Integer> container, TestParam tp) {
                int loops = tp.loops;
                for (int i = 0; i < loops; i++) {
                    container.add(5,47);
                }
                return loops;
            }
        });
        tests.add(new TestContainer<List<Integer>>("remove") {
            @Override
            int test(List<Integer> container, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    container.clear();
                    container.addAll(new CountingIntegerList(size));
                    while (container.size()>5){
                        container.remove(5);
                    }
                }
                return loops*size;
            }
        });
        qTests.add(new TestContainer<LinkedList<Integer>>("addFirst") {
            @Override
            int test(LinkedList<Integer> container, TestParam tp) {
                int loops = tp.loops;
                int listsize = tp.size;
                for (int i = 0; i < loops ; i++) {
                    container.clear();
                    for (int j = 0; j < listsize ; j++) {
                        container.addFirst(47);
                    }
                }
                return loops*listsize;
            }
        });
        qTests.add(new TestContainer<LinkedList<Integer>>("addLast") {
            @Override
            int test(LinkedList<Integer> container, TestParam tp) {
                int loops = tp.loops;
                int listsize = tp.size;
                for (int i = 0; i < loops ; i++) {
                    container.clear();
                    for (int j = 0; j < listsize ; j++) {
                        container.addLast(47);
                    }
                }
                return loops*listsize;
            }
        });
        qTests.add(new TestContainer<LinkedList<Integer>>("rmFirst") {
            @Override
            int test(LinkedList<Integer> container, TestParam tp) {
                int loops = tp.loops;
                int listsize = tp.size;
                for (int i = 0; i < loops ; i++) {
                    container.clear();
                    container.addAll(new CountingIntegerList(listsize));
                    for (int j = 0; j < listsize ; j++) {
                        container.removeFirst();
                    }
                }
                return loops*listsize;
            }
        });
        qTests.add(new TestContainer<LinkedList<Integer>>("rmLast") {
            @Override
            int test(LinkedList<Integer> container, TestParam tp) {
                int loops = tp.loops;
                int listsize = tp.size;
                for (int i = 0; i < loops ; i++) {
                    container.clear();
                    container.addAll(new CountingIntegerList(listsize));
                    for (int j = 0; j < listsize ; j++) {
                        container.removeLast();
                    }
                }
                return loops*listsize;
            }
        });

    }

    static class ListTester extends Tester<List<Integer>>{

        public ListTester(List<Integer> container, List<TestContainer<List<Integer>>> tests) {
            super(container, tests);
        }
        @Override
        protected List<Integer> initialize(int size){
            container.clear();
            container.addAll(new CountingIntegerList(size));
            return container;
        }
        public static void run(List<Integer> list,List<TestContainer<List<Integer>>> tests){
            new ListTester(list,tests).timedTest();
        }
    }

    public static void main(String[] args) {

        if(args.length>0){
            Tester.defaultParams = TestParam.array(args);
        }
        Tester<List<Integer>> arrayTest = new Tester<List<Integer>>(null,tests.subList(1,3)){
            @Override
            protected List<Integer> initialize(int size){
                Integer[] ia = Generated.array(Integer.class,new CountingGenerateor.Integer(),size);
                return Arrays.asList(ia);
            }
        };

        arrayTest.setHeadline("Array as List");
        arrayTest.timedTest();
        Tester.defaultParams = TestParam.array(10,5000,100,5000,1000,5000,10000,500);
        if(args.length>0){
            Tester.defaultParams = TestParam.array(args);
        }
//        ListTester.run(new ArrayList<>(),tests);
//        ListTester.run(new LinkedList<>(),tests);
//        ListTester.run(new Vector<>(),tests);
        Tester.fieldWidth = 12;
        Tester<LinkedList<Integer>> qTest = new Tester<LinkedList<Integer>>(new LinkedList<>(),qTests);
        qTest.setHeadline("Queue tests");
        qTest.timedTest();

    }
}
