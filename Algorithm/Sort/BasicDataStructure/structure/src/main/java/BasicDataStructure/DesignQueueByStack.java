package BasicDataStructure;

import java.util.Stack;

public class DesignQueueByStack {
    private Stack<Integer> pushStack;
    private Stack<Integer> popStack;

    public DesignQueueByStack(){
        pushStack = new Stack<>();
        popStack = new Stack<>();
    }
    public void offer(Integer element){
        pushStack.push(element);
    }
    public int poll(){
        if(pushStack.isEmpty()){
            throw new RuntimeException("Queue is Empty!");
        }
        while(!pushStack.isEmpty()){
            popStack.push(pushStack.pop());
        }
        int result = popStack.pop();
        while(!popStack.isEmpty()){
            pushStack.push(popStack.pop());
        }
        return result;
    }
}
