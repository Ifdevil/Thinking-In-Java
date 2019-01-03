package thinkingInJava.chapter18;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 性能测试  Stream 和 MappedByteBuffer
 */
public class MappedIO {

    private static int numOfInts = 4000000;
    private static int numOfUbuffInts = 200000;
    private abstract static class Tester{
        private String name;
        public Tester(String name){
            this.name = name;
        }
        public void runTest(){
            System.out.print(name+": ");
            try {
                long star = System.nanoTime();
                test();
                double duration = System.nanoTime()-star;
                System.out.format("%.2f\n",duration/1.0e9);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        public abstract void test() throws IOException;
    }

    private static Tester[] tests = new Tester[]{
            new Tester("Stream Write") {
                @Override
                public void test() throws IOException {
                    DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("tmp.tmp")));
                    for (int i = 0; i < numOfInts; i++) {
                        dos.writeInt(i);
                    }
                    dos.close();
                }
            },
            new Tester("Mapped Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new RandomAccessFile("tmp.tmp","rw").getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE,0,fc.size()).asIntBuffer();
                    for (int i = 0; i < numOfInts; i++) {
                        ib.put(i);
                    }
                    fc.close();
                }
            },
            new Tester("Sream Read") {
                @Override
                public void test() throws IOException {
                    DataInputStream dos = new DataInputStream(new BufferedInputStream(new FileInputStream("tmp.tmp")));
                    for (int i = 0; i < numOfInts; i++) {
                        dos.readInt();
                    }
                    dos.close();
                }
            },
            new Tester("Mapped Read") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new RandomAccessFile("tmp.tmp","r").getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY,0,fc.size()).asIntBuffer();
                    while (ib.hasRemaining()){
                        ib.get();
                    }
                    fc.close();
                }
            },
            new Tester("Sream Read/Write") {
                @Override
                public void test() throws IOException {
                    RandomAccessFile raf = new RandomAccessFile(new File("tmp.tmp"),"rw");
                    raf.write(1);
                    for (int i = 0; i < numOfUbuffInts; i++) {
                        raf.seek(raf.length()-4);
                        raf.write(raf.readInt());
                    }
                }
            },
            new Tester("Mapped Read/Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new RandomAccessFile(new File("tmp.tmp"),"rw").getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE,0,fc.size()).asIntBuffer();
                    ib.put(0);
                    for (int i = 1; i < numOfUbuffInts; i++) {
                        ib.put(ib.get(i-1));
                    }
                    fc.close();
                }
            }
    };
    public static void main(String[] args) throws IOException {
        for (Tester test:tests){
            test.runTest();
        }
    }
}
