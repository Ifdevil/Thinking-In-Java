package thinkingInJava.chapter17;

import java.util.BitSet;
import java.util.Random;

public class Bits {

    public static void printBitSet(BitSet b){
        System.out.println("bits:"+ b);
        StringBuilder bbits = new StringBuilder();
        for (int i = 0; i < b.size(); i++) {
            bbits.append(b.get(i)?"1":"0");
        }
        System.out.println("bit patter: "+ bbits);
    }
    public static void main(String[] args) {
        Random  random = new Random(47);
        byte bt = (byte) random.nextInt();
        BitSet bb = new BitSet();
        for (int i = 7; i >= 0; i--){
            if((( 1 << i) & bt ) != 0){
                bb.set(i);
            }else{
                bb.clear(i);
            }
        }
        System.out.println("byte value :" + bt );
        printBitSet(bb);

        short st = (short) random.nextInt();
        BitSet bs = new BitSet();
        for (int i = 15; i >= 0; i--){
            if((( 1 << i) & st ) != 0){
                bs.set(i);
            }else{
                bs.clear(i);
            }
        }
        System.out.println("short value :" + st );
        printBitSet(bs);

        int it = random.nextInt();
        BitSet bi = new BitSet();
        for (int i = 31; i >= 0; i--){
            if((( 1 << i) & it ) != 0){
                bi.set(i);
            }else{
                bi.clear(i);
            }
        }
        System.out.println("int value :" + it );
        printBitSet(bi);
    }
}
