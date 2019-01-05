package thinkingInJava.chapter19;

import thinkingInJava.chapter16.Generator;

public class VendingMachine {

    private static State state = State.RESTING;
    private static int amount = 0;
    private static Input selection = null;
    enum StateDuration{TRANSIENT}
    enum State{
        RESTING{
            void next(Input input){
                switch (Category.categories(input)){
                    case MONEY:
                        amount+=input.amount();
                        state = ADDING_MONEY;
                }
            }
        },
        ADDING_MONEY{
            void next(Input input){
                switch (Category.categories(input)){
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        if(amount<selection.amount()){
                            System.out.println("Insufficient money for "+selection);
                        }else{
                            state = DISPENSING;
                        }
                        break;
                    case QUIT_TRANSACTION:
                        state = GIVING_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        DISPENSING(StateDuration.TRANSIENT){
            void next(){
                System.out.println("here is your "+selection);
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        },
        GIVING_CHANGE(StateDuration.TRANSIENT){
            void next(){
                if(amount>0){
                    System.out.println("Your change: "+amount);
                    amount = 0;
                }
                state = RESTING;
            }
        },
        TERMINAL{
            void output(){
                System.out.println("Halted");
            }
        }
        ;
        private boolean isIransient =false;
        State(){};
        State(StateDuration trans){
            isIransient = true;
        }
        void next(Input input){
            throw new RuntimeException("Only call next(Input input) for non-transient states");
        }
        void next(){
            throw new RuntimeException("Only call next for StateDuration.TRANSIENT states");
        }
        void output(){
            System.out.println(amount);
        }
    }
    static void run(Generator<Input> gen){
        while (state != State.TERMINAL){
            state.next(gen.next());
            while (state.isIransient){
                state.next();
            }
            state.output();
        }
    }

    public static void main(String[] args) {
        Generator<Input> gen = new RandomInputGenerator();
        run(gen);
    }

}
class RandomInputGenerator implements Generator<Input>{

    @Override
    public Input next() {
        return Input.randomSelection();
    }
}

