package thinkingInJava.chapter17;

import java.util.*;

/**
 * Map 性能测试
 */
public class SetPerformance {

    static List<TestContainer<Set<Integer>>> tests = new ArrayList<TestContainer<Set<Integer>>>();

    static {
        tests.add(new TestContainer<Set<Integer>>("add") {
            @Override
            int test(Set<Integer> container, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    container.clear();
                    for (int j = 0; j < size; j++) {
                        container.add(j);
                    }
                }
                return loops * size;
            }
        });
        tests.add(new TestContainer<Set<Integer>>("contains") {
            @Override
            int test(Set<Integer> container, TestParam tp) {
                int loops = tp.loops;
                int span = tp.size * 2;
                for (int i = 0; i < loops; i++) {
                    for (int j = 0; j < span; j++) {
                        container.contains(j);
                    }
                }
                return loops * span;
            }
        });
        tests.add(new TestContainer<Set<Integer>>("iterate") {
            @Override
            int test(Set<Integer> container, TestParam tp) {
                int loops = tp.loops * 10;
                for (int i = 0; i < loops; i++) {
                    Iterator it = container.iterator();
                    while (it.hasNext()){
                        it.next();
                    }
                }
                return loops * container.size();
            }
        });
    }

    public static void main(String[] args) {
        Tester.fieldWidth = 12;
        Tester.run(new TreeSet<>(),tests);
        Tester.run(new HashSet<>(),tests);
    }
}
