public class Main {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enQueue(11);
        queue.enQueue(22);
        queue.enQueue(33);
        queue.enQueue(44);
        while(!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }

        CircleQueue<Integer> c = new CircleQueue<>();
        for (int i = 0; i < 10; i++) {
            c.enQueue(i);
        } 
        System.out.println(c);
        for (int i = 0; i < 3; i++) {
            c.deQueue();
        }
        System.out.println(c);
    }
}