package BinaryTree;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LeastCostGoldChain {
    
    public static int getLeastCost(int[] arr){

        if(arr.length == 0){
            return 0;
        }
        if(arr.length == 1){
            return arr[0];
        }
        int cost1,cost2,allCost = 0;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        Arrays.stream(arr).forEach((element)->priorityQueue.add(element));
        while(priorityQueue.size() != 1){
            cost1 = priorityQueue.poll();
            cost2 = priorityQueue.poll();
            allCost += cost1 + cost2;
            priorityQueue.add(cost1+cost2);
        }
        return allCost;
    }
}
