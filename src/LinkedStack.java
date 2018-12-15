public class LinkedStack<T> {

    private static class Node<U>{
        U item;
        Node<U> next;
        Node(){
            this.item = null;this.next=null;
        }
        Node(U item,Node<U> next) {
            this.item = item;
            this.next = next;
        }

        boolean end(){
            return item == null && next == null;
        }
    }

    private Node<T> top = new Node<T>();

    public void push(T item){
        top = new Node(item,top);
    }

    public T pop(){
        T result = top.item;
        if(!top.end()){
            top = top.next;
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<>();
        for(String s:"Hi Hello World !".split(" ")){
            stack.push(s);
        }
        String s;
        while ( (s = stack.pop()) !=null){
            System.out.println(s);
        }
    }
}
