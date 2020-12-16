package BasicDataStructure;

import java.util.LinkedList;
import java.util.Queue;

public class DesignStackByQueue {
    
    private Queue<Integer> queue;
    private Queue<Integer> queueTemp;

    public DesignStackByQueue(){
        queue = new LinkedList<>();
        queueTemp = new LinkedList<>();
    }

    public int peek(){

        if (queue.isEmpty()) {
            throw new RuntimeException("Stack is Empty");
        }
        while (queue.size() != 1) {
            queueTemp.offer(queue.poll());
        }
        int result = queue.poll();
        queueTemp.offer(result);
        swap();
        return result;
    }

    public void push(Integer element){
        queue.offer(element);
    }

    public int pop(){
        if(queue.isEmpty()){
            throw new RuntimeException("Stack is Empty");
        }
        while(queue.size()!=1){
            queueTemp.offer(queue.poll());
        }
        int result = queue.poll();
        swap();
        return result;
    }

    private void swap(){
        Queue<Integer> temp = queueTemp;
        queueTemp = queue;
        queue = temp;
    }
}
