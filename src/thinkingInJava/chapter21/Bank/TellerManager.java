package thinkingInJava.chapter21.Bank;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class TellerManager implements Runnable {
    private ExecutorService exec;
    private CustomerLine customers;
    private PriorityQueue<Teller> workingTeller = new PriorityQueue<Teller>();
    private Queue<Teller> tellersDoingOtherThings = new LinkedList<Teller>();
    private int adjustmentPeriod;
    private static Random random = new Random(47);
    public TellerManager(ExecutorService e,CustomerLine customerLine,int adjustPeriod){
        exec = e;
        customers = customerLine;
        adjustmentPeriod = adjustPeriod;
        Teller teller = new Teller(customers);
        exec.execute(teller);
        workingTeller.add(teller);
    }
    public void adjustTellerNumber(){
        if(customers.size() / workingTeller.size() > 2){
            if(tellersDoingOtherThings.size()>0){
                Teller teller = tellersDoingOtherThings.remove();
                teller.serveCustomerLine();
                workingTeller.offer(teller);
                return;
            }
            Teller teller = new Teller(customers);
            exec.execute(teller);
            workingTeller.add(teller);
            return;
        }
        if(workingTeller.size() > 1 && customers.size()/workingTeller.size() < 2){
            reassignOneTeller();
        }
        if(customers.size()==0){
            while (workingTeller.size() > 1){
                reassignOneTeller();
            }
        }
    }
    public void reassignOneTeller(){
        Teller teller = workingTeller.poll();
        teller.doSomethingElse();
        tellersDoingOtherThings.offer(teller);
    }
    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();
                System.out.print(customers+" { ");
                for (Teller teller:workingTeller){
                    System.out.print(teller.shortString() + " ");
                }
                System.out.println("}");
            }
        }catch (InterruptedException e){
            System.out.println(this + "interrupted");
        }
        System.out.println(this+"terminating");
    }
    public String toString(){
        return "TellerManager ";
    }
}
